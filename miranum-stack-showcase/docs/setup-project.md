# Setup Project and Development Environment

> We only use **Visual Studio Code** (>= 1.76.0) as our IDE.
> Click [here](https://code.visualstudio.com/Download) to install it if you don't already have it.

## 1. Create the Directory and Project Structure

Since our process will have a service task,
and we want to benefit from the technology neutrality offered by the **Miranum Connect Library**,
we can use two GitHub repository templates to initiate the new project.

* [Miranum Connect Basic Template](https://github.com/Miragon/miranum-connect-basic-template)
* [Miranum Connect Hexagonal Template](https://github.com/Miragon/miranum-connect-hexagonal-template)

To do so, navigate to the GitHub Repository with the Template and click on **Use this template** and follow the instructions.
Or use the command line:

```shell
gh repo create --template <repository> --private
```

Now all you need to do is clone your new repository with `git clone <your-repository>`.

**In this showcase, we will use the Hexagonal Architecture Template.**
> Note: If you'd like to find out more about *Hexagonal Architecture* and how it can benefit you, click [here](https://www.miranum.io/docs/guides/best-practices/hexagonal-architecture).

## 2. Install VS Code Plugins

After initiating the project, we have to set up our IDE.
As mentioned above, we will use VS Code.
To get VS Code ready for development, we need to install some plugins:

* For developing with Java:
  * [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
  * [Spring Boot Dashboard](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-spring-boot-dashboard)
* For developing our process and its artifacts
  * [Miranum IDE](https://marketplace.visualstudio.com/items?itemName=miragon-gmbh.miranum-ide)

## 3. Enable the Maven Wrapper

The template comes with a Maven script (mvnw / mvnw.cmd).
This will not work out of the box in VS Code.
We have to enable the Maven Wrapper first.
To do that open the *Terminal* (`View > Terminal`) and enter the following command:

```shell
mvn wrapper:wrapper
```

## 4. Rename/Remove Modules, Packages, and Java Files

You will probably want to rename the modules, packages, and Java files that come with the templates you have used.
Also make sure to rename the artifactID, name and modules inside the pom.xml.

Depending on your project, you may want to remove dependencies and classes that are not in use.
For instance, in this showcase, we only use **Miranum-Workers**.
That's why we can remove the dependencies to **Miranum-Message** and **Miranum-Process** in the respective pom.xml and some unused packages and Java files.

## What's next?

Having set up the project and the IDE, the [next step](./create-worker.md) is to create a `Worker`.
