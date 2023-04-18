# Fit-Connect FormsFlow example

*This example polls fit-connect submissions from a specified destination and starts a process with the submission
attachment data using miranum-connect.*

## Make it run

1. First of all you need to create a fit-connect sender and subscriber client and a destination. \
   For information on how to create the clients and the destination,
   see [the fit-connect video tutorial](https://docs.fitko.de/fit-connect/docs/receiving/overview) (German).

2. Make sure you have a running instance of
   the [Camunda 8 Platform engine](../../../stack/camunda-8/docker-compose.yml).
3. Add the following properties to your application. You can add those to a `credentials.properties` file, which is not
   tracked by git and imported by your main `application.properties`.

```properties
spring.security.oauth2.client.registration.fitconnect.authorization-grant-type=client_credentials
spring.security.oauth2.client.registration.fitconnect.client-id=<client-id>
spring.security.oauth2.client.registration.fitconnect.client-secret=<client-secret>
spring.security.oauth2.client.provider.fitconnect.token-uri=<token-uri>
# This property specifies which process should be started for submissions received by a specific destination ID.
# You can add multiple key value pairs to listen for submissions of multiple destinations.
fitconnect.subscriber.process-destination-map.<process-key>=<destination-id>
fitconnect.subscriber.privateKey-decryption-path=<path-to-private-decryption-key-json>
fitconnect.subscriber.privateKey-signing-path=<path-to-private-signing-key-json>
```

The token uri for the testing environment is https://auth-testing.fit-connect.fitko.dev/token.

To create and submit submissions you can use
the [fit-connect online-service client](../fit-connect-online-service-client/README.md).

*For more information, please refer to the [fit-connect documentation](https://docs.fitko.de/fit-connect/docs).*

Add keycloak info
