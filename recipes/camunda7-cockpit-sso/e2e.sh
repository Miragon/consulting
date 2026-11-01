#!/usr/bin/env bash
set -euo pipefail

wait_for() {
  for _ in $(seq 1 60); do
    if curl -s -o /dev/null "$2" ${1:+-f}; then return 0; fi
    sleep 3
  done
  echo "Timed out waiting for $2"
  return 1
}

docker compose -f sso-stack/docker-compose.yml up -d
wait_for strict http://keycloak:8080/auth/realms/testrealm/.well-known/openid-configuration

set -a && source sso-stack/local-docker.env && set +a
./mvnw -B install -DskipTests
./mvnw -B -f cockpit-sso-service/pom.xml spring-boot:run > app.log 2>&1 &
wait_for "" http://localhost:8082/ || { tail -n 200 app.log; exit 1; }

cd bruno
npx --yes @usebruno/cli@4.0.0 run . --env Local -r
