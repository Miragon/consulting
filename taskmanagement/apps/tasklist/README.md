# Tasklist

# Usage (Development)

1. `yarn install`
2. `yarn client:generate`
   > :warning: You have to define the path to the openapi.json of
   > the [taskmanager](https://github.com/miragon/ma-zeebe-taskmanagement).
3. Setup Postman *Mock-Server*
   > ℹ️ The `Postman-Collection` is located in the `resource` folder.
4. Add the `URL` to the *Mock-Server* to the `.env.development` file.
5. `yarn dev`
