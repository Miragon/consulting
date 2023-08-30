# Run the Application

If you follow the steps on [how to set up the development environment](setup-project.md#2-install-vs-code-plugins) you have VS Code with the *Spring Boot Dashboard* as a plugin installed.
If not, please do so now.

## Configure the Application

We need to configure the URLs to the endpoint of the Workflow-Engine and the Keycloak-Server.

```yaml
# Camunda Platform 7
camunda:
  bpm:
    client:
      base-url: http://localhost:8090/engine-rest
  autoDeploy:
    enabled: false
  disable-backoff-strategy: true

miranum:
  c7:
    auth.server.url: http://keycloak:9090/auth/realms/miranum/protocol/openid-connect/token
    client:
      id: miranum-worker
      secret: s3creT
```

[application.yml](../order-example/order-example-camunda7/src/main/resources/application.yaml)

## Execute the Run Script

With the plugin installed, it is easy to run the application.

1. Switch to the *Spring Boot Dashboard View*
2. Run `order-example-camunda7`

    <img src="../images/run-application.png" alt="Run Spring Boot Application" width=300>

## What's next?

Now everything is up and running.
Now you can go [back](../README.md#getting-started) and follow the instruction in step 8.
