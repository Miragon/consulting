# Running Forms-Flow-AI

## Windows:

- Make sure to have Docker installed
- Run `install.bat`. Docker should start all required containers

## MacOS:

- Make sure Docker is installed
- Get your ip address by running `ipconfig getifaddr en0`
- Change ip addresses in [sample.env](./docker-compose/sample.env)
- Rename to [sample.env](./docker-compose/sample.env) to .env
- Change ip addresses in the [config.js](./docker-compose/configuration/config.js)
- Run `docker-compose up`

### For M1 Machines:

- Follow the upper guide and run `docker-compose -f docker-compose-arm64.yml up`

## Accessing Forms-Flow

- Camunda: http://localhost:8000/camunda
- Forms Flow REST API: http://localhost:5000
- Keycloak:  http://localhost:8080
- Forms Flow Web App:  http://localhost:3000

### Pre-defined user:

- **Designer:** formsflow-designer PW: changeme
- **Client:** formsflow-client PW: changeme
- **Reviewer:**    formsflow-reviewer PW: changeme
- **Clerk:**    formsflow-clerk PW: changeme
- **Approver:**    formsflow-approver PW: changeme	
