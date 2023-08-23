# Deploy your Process Artifacts

The current version of **Miranum IDE** (= 0.5.3) does not support the deployment of process artifacts to **Miranum Platform**.
Therefore, we use [Postman](https://identity.getpostman.com/login) to make the necessary POST requests.

## Import Collection

Under [resources/postman_collection.json](../resources/postman_collection.json) you will find all required POST requests for this showcase.
To import them into your *Postman Workspace* follow these steps:

1. Login/Sign up for Postman
2. Go to *My Workspaces* or create a new Workspace

   <img src="../images/postman-workspace.png" alt="Navigate to workspace" width="300">

3. Import the collection

   <img src="../images/postman-import.png" alt="Import collection" width="300">

4. Select the pre-configured collection ([resources/postman_collection.json](../resources/postman_collection.json))

## Generate Access Token

Before you can deploy your artifacts, you have to generate an Access Token.
You can also use *Postman* to do that:

1. Select the imported collection and navigate to `Authorization`  
   <img src="../images/postman-access-token.png" alt="Navigate to authorization" width="300">
2. Scroll to the bottom and click on `Get New Access Token`
3. Proceed and click on `Use Token`

## Deploy BPMN or DMN Files

1. Navigate to the respective POST request an choose `Body`
2. Click on `Select Files` and select your .bpmn or .dmn file you want to deploy

   <img src="../images/postman-deploy-bpmn.png" alt="Deploy BPMN or DMN" width="300">

## Deploy Forms and Configs

To deploy the forms and configs, you can just send the respective requests.
If you make changes to one of these files, you have to copy the JSON and paste it into the body of the POST request.

**The next steps are only required if you make changes.**

### Deploy Configs

1. Open the config file
2. Copy the complete JSON
3. Paste it into the request body

   ```json
   {
      "tags": [
         "latest",
         "1.0.1"
      ],
      "jsonNode": {
         // Paste here
      }
   }
   ```

### Deploy Forms

1. Open the form you want to deploy and click on the text editor icon ![text-editor-icon](../images/deploy-text-editor-icon.png) in the top right corner
2. Copy the schema

   ```json
   {
      "formKey": "myKey",
      "schema": {
         // Copy this
      }
   }
   ```

3. Paste it into the request body

   ```json
   {
      "tags": [
         "latest",
         "1.0.1"
      ],
      "jsonNode": {
         // Paste here
      }
   }
   ```

## What's next?

With all artifacts deployed, the [next steps](./run-application.md) shows how to run the process in **Miranum Platform**.
