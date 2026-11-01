# camunda-cockpit-sso

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Java 21](https://img.shields.io/badge/Java-21-blue.svg)](https://adoptium.net/)
[![Camunda 7.24](https://img.shields.io/badge/Camunda-7.24-orange.svg)](https://docs.camunda.org/manual/7.24/)
[![Spring Boot 3.5](https://img.shields.io/badge/Spring%20Boot-3.5-green.svg)](https://spring.io/projects/spring-boot)

A **reference implementation** for adding **Single Sign-On (OAuth2 / OIDC)** to the
**Camunda 7 web applications** (Cockpit, Tasklist, Admin) — replacing Camunda's
built-in form login with an OAuth2/OIDC flow against an external identity provider
such as **Keycloak**.

It is a blueprint to learn from and adapt, **not** a published drop-in library.
See [Using this in your own project](#using-this-in-your-own-project) to reuse it.

## What it demonstrates

- **OAuth2/OIDC login for the web apps** instead of Camunda's form login,
  configured through standard Spring `spring.security.oauth2.*` properties.
- **Role-gated access.** Only users carrying a configurable client role
  (`application.web-app-role`) may reach the web apps.
- **Engine integration.** Bridges the OAuth2 principal into Camunda's
  `ContainerBasedAuthenticationFilter` and adds a read-only identity provider, so
  the engine sees the authenticated user without maintaining a separate user store.
- **Reverse-proxy aware.** Honours `X-Forwarded-*` headers so OAuth2 redirect URLs
  are built correctly behind HTTPS termination (e.g. an OpenShift/Ingress route).

## How it works

The `cockpit-sso-starter` module configures a Spring Security filter chain that:

1. Redirects unauthenticated web-app requests to the identity provider (`oauth2Login`)
   and validates bearer tokens on the REST API (`oauth2ResourceServer`).
2. Requires the `application.web-app-role` role for `/app/**`, `/api/**`, `/assets/**`
   and `/lib/**`.
3. Maps the token's roles into Spring `GrantedAuthorities` and hands the user to
   Camunda through a container-based authentication provider.

See [`CamundaWebAppsSecurityConfiguration`](cockpit-sso-starter/src/main/java/io/miragon/camunda/sso/config/CamundaWebAppsSecurityConfiguration.java)
for the full filter chain.

## Requirements

- Java 21
- An OAuth2/OIDC identity provider (Keycloak in the examples)
- Docker / Podman + Docker Compose (for the local development stack)

> Maven itself does **not** need to be installed — the repository ships the
> [Maven Wrapper](https://maven.apache.org/wrapper/) (`./mvnw`), which downloads
> the pinned Maven version on first use.

## Modules

| Module | Description |
|---|---|
| [`cockpit-sso-starter`](cockpit-sso-starter) | The SSO configuration — the Spring Security filter chain and Camunda identity glue. Despite the name, it is **not** an auto-configured Spring Boot starter; its `@Configuration` beans have to be picked up by component scanning (see [below](#using-this-in-your-own-project)). |
| [`cockpit-sso-service`](cockpit-sso-service) | A runnable example application that wires in the configuration module. Handy as a reference and for local testing. |
| [`sso-stack`](sso-stack) | A local Keycloak + PostgreSQL development environment (dev only, **not** for production). |

## Quickstart (local development)

The example service (`cockpit-sso-service`, port `8082`) plus the local `sso-stack`
gives you a working SSO-protected Camunda in a few steps.

> **Host alias required.** The stack references Keycloak by the hostname `keycloak`,
> so add it to your hosts file — `/etc/hosts` on macOS/Linux,
> `C:\Windows\System32\Drivers\etc\hosts` on Windows — so the issuer URL resolves both
> inside Docker and on your machine:
> ```
> 127.0.0.1 localhost keycloak
> ```

1. **Start the identity provider** — Keycloak, PostgreSQL, and a migration job that
   seeds the `testrealm`, an `engine` client and a `johndoe` test user:

   ```bash
   docker compose -f sso-stack/docker-compose.yml up -d
   ```

2. **Run the example service** with the matching SSO settings. The first `install`
   publishes `cockpit-sso-starter` to your local Maven repo (rerun only after
   changing the starter):

   ```bash
   set -a && source sso-stack/local-docker.env && set +a
   ./mvnw install -DskipTests
   ./mvnw -f cockpit-sso-service/pom.xml spring-boot:run
   ```

3. **Open the web apps** at <http://localhost:8082/app/welcome/default/> and log in
   through Keycloak as `johndoe` / `test`. (The security config only serves the apps
   under `/app/**`; the bare `/` is not exposed.)

4. **Smoke-test the stack** with the [Bruno](https://www.usebruno.com/) collection in
   [`bruno/`](bruno) — open it in Bruno, or run `npx @usebruno/cli run bruno --env Local`
   from the repo root — to check Keycloak, log `johndoe` in, and call the Camunda REST
   API through the SSO-protected service.

## Using this in your own project

This repository is **not published to a Maven repository** and is **not an
auto-configured Spring Boot starter** — adding `cockpit-sso-starter` as a
dependency on its own will not wire anything up. There are two realistic ways to
reuse it:

**Option A — copy the configuration (recommended).** Copy the classes under
[`cockpit-sso-starter/.../config`](cockpit-sso-starter/src/main/java/io/miragon/camunda/sso/config)
into your own Camunda Spring Boot web-app project and adapt them to your needs.

**Option B — build and depend on it locally.** Run `./mvnw install` to publish the
module to your local Maven repository (`~/.m2`), then add the dependency:

```xml
<dependency>
    <groupId>io.miragon.camunda</groupId>
    <artifactId>cockpit-sso-starter</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

Because there is no auto-configuration, make sure Spring actually picks up the
configuration beans — either place your application in the `io.miragon.camunda.sso`
package (so component scanning finds them) or import them explicitly:

```java
@SpringBootApplication
@ComponentScan(basePackages = {"com.your.app", "io.miragon.camunda.sso.config"})
public class YourApplication { }
```

In either case, configure your identity provider with the standard Spring Security
OAuth2 properties plus the two project-specific properties:

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: ${SSO_BASE_URL}/realms/${SSO_REALM}
      client:
        provider:
          keycloak:
            issuer-uri: ${SSO_BASE_URL}/realms/${SSO_REALM}
            user-name-attribute: sub
        registration:
          keycloak:
            provider: keycloak
            client-id: ${SSO_ENGINE_CLIENT_ID}
            client-secret: ${SSO_ENGINE_CLIENT_SECRET}
            redirect-uri: "{baseUrl}/app/{action}/oauth2/code/{registrationId}"

application:
  # <client-id>:<role> that a user must hold to access the web apps
  web-app-role: "${SSO_ENGINE_CLIENT_ID}:${WEBAPP_REQUIRED_ROLE}"
  # id of the spring.security.oauth2.client.registration entry used for login
  registration: keycloak
```

### Configuration reference

| Property | Description | Example |
|---|---|---|
| `application.web-app-role` | Client role required to access the web apps, as `<client-id>:<role>`. | `engine:webapp-user` |
| `application.registration` | The OAuth2 client registration id used for the login redirect. | `keycloak` |
| `spring.security.oauth2.client.*` | Standard Spring Security OAuth2 client configuration (login). | — |
| `spring.security.oauth2.resourceserver.jwt.issuer-uri` | Issuer used to validate bearer tokens on the REST API. | — |

## Contributing

Contributions are welcome! Please read [CONTRIBUTING.md](CONTRIBUTING.md) for the
development setup, build/run commands and the pull-request process.

## License

Distributed under the MIT License. See [LICENSE](LICENSE) for details.
