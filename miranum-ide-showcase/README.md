# Showcase-Miranum-IDE

> A little showcase to demonstrate the capabilities of the Miranum IDE.
>
> For more Information on miranum, visit [miranum.com](https://miranum.com) or [https://github.com/Miragon/miranum-ide](https://github.com/Miragon/miranum-ide)

## Getting started

```bash
# Install dependencies
npm install

# Deploy to local dev environment
npm run deploy:local

# Deployment to development stage
npm run deploy:dev

# Deployment to test stage
npm run deploy:test

# Deployment to production stage
npm run deploy:prod
```

You may also want to install the [miranum-ide](https://marketplace.visualstudio.com/items?itemName=miragon-gmbh.miranum-ide) vscode extensions to get the best experience.

**Deployment in VSCode**

1. Open the command palette (Ctrl+Shift+P)
2. Search for `deploy:local` and run it

## Showcase

1. Generate project with Miranum Console
    - Alternatively, you can use the `miranum` cli to generate a new project
    - `npx @miragon/miranum-cli generate --name my-showcase --path my-showcase`
2. Open the project in Miranum IDE
3. Checkout the project structure and VS-Code Extensions
    - miranum.json
    - Bpmn processes
    - Forms
    - Element Templates
    - Configs
4. Deploy the project to local dev environment
    - Use the command palette (Ctrl+Shift+P) and run `deploy:local`
    - `npm run deploy:local`
5. Edit the process showcase-Miranum-IDE.bpmn
    - Add a new task
    - Add a new form
    - Call the Email Integration using an element template
6. Deploy the project to development stage
    - Use the command palette (Ctrl+Shift+P) and run `deploy:dev`
    - `npm run deploy:dev`
7. Have a look at Miranum-Deployment
    - Open the documentation at [https://miranum.com/docs/components/miranum-ide/miranum-deployment](https://miranum.com/docs/components/miranum-ide/miranum-deployment)
