# Setup **Miranum Platform**

For the development of this Showcase, we copied the `platform-stack` folder from our [Miranum Platform Repository](https://github.com/Miragon/miranum-platform)

```shell
git clone https://github.com/Miragon/miranum-platform.git
cd miranum-platform
git checkout 39a3342037628f36e3b252697d55a01da68f069f
```

If you copied it, you can read the [README](../platform-stack/README.md) on how to set up **Miranum Platform**.

## **(Optional)** Working with Docker in VS Code

1. Install the [Docker Plugin](https://marketplace.visualstudio.com/items?itemName=ms-azuretools.vscode-docker)
2. Open the *Command Palette* (`View > Command Palette`)
3. Enter `Docker: Compose Up - Select Services`
4. Select `miranum-stack-showcase` from the displayed list
5. Select `Profiles`
6. Select `engine` (if not already selected) and click on `OK`
7. Switch to the `Docker View` to manage the container

    <img src="../images/docker-view-icon.png" alt="docker-view-icon" width=300>

### Best Practice

If you need to run `docker compose` frequently, we recommend overriding the default command or adding custom tasks.

#### Add Custom Tasks

1. Add [tasks.json](../.vscode/tasks.json) to the `.vscode` folder
2. Paste the following JSON into that file:

    ```json
    {
        "version": "2.0.0",
        "tasks": [
            {
                "label": "Run docker-compose up",
                "type": "docker-compose",
                "dockerCompose": {
                    "up": {
                        "detached": true,
                        "profiles": [
                            "engine"
                        ]
                    },
                    "files": [
                        "${workspaceFolder}/platform-stack/docker-compose.yml"
                    ]
                },
                "problemMatcher": []
            },
            {
                "label": "Run docker-compose down",
                "type": "docker-compose",
                "dockerCompose": {
                    "down": {
                        "customOptions": "--remove-orphans"
                    },
                    "files": [
                        "${workspaceFolder}/platform-stack/docker-compose.yml"
                    ]
                },
                "problemMatcher": []
            }
        ]
    }
    ```

3. Open the *Command Palette* (`View > Command Palette`)
4. Enter `Tasks: Run Task`
5. Enter `docker-compose`
6. Select the task you want to run

#### Override Default Command

1. Save your current Workspace (`File > Save Workspace As...`)
2. Open the [*Folder Settings*](../.vscode/settings.json)
    * Open the *Command Palette* (`View > Command Palette`)
    * Enter `Preferences: Open Folder Settings (JSON)`
3. Add the following:

    ```json
    {
        // ...
        "settings": {
            "docker.commands.composeUp": [
                {
                    "label": "Compose Up",
                    "template": "docker compose ${configurationFile} ${profileList} up ${detached}"
                }
            ],
            "docker.commands.composeDown": [
                {
                    "label": "Compose Down",
                    "template": "docker compose ${configurationFile} down --remove-orphans"
                }
            ]
        }
        // ...
    }
    ```

4. Right-Click on `/platform-stack/docker-compose.yml` and select `Compose Up`

> Note: If you don't want to get asked for the profile every time you can replace `${profileList}` form the JSON above with `engine`.

#### Add *Miranum Platform Repository* to the Workspace

Instead of copying the `platform-stack` folder you can also add the repository to your workspace (`File > Add Folder to Workspace...`).
If you prefer this, you have to pay attention to the following:

* After adding `miranum-platform` to your workspace save it (`File > Save Workspace As...`)
* [tasks.json](#add-custom-tasks) must be placed into the `miranum-platform/.vscode` folder
* Add the [settings](#override-default-command) to your **Workspace Settings** instead of the *Folder Settings*
  * Open the *Command Palette* (`View > Command Palette`)
  * Enter `Preferences: Open Workspace Settings (JSON)`

## What's next?

Now that **Miranum Platform** is up and running,
we are ready to deploy our Process Artifacts in the [next step](./deploy-artifacts.md).
