# Test the Process

We will follow this Blogpost about [Testing Entire Process Paths](https://camunda.com/blog/2020/10/testing-entire-process-paths/).
To generate a report about the code coverage we use [camunda-process-test-coverage](https://github.com/camunda-community-hub/camunda-process-test-coverage).
Have a look at the [pom.xml](../order-example/order-example-camunda7/pom.xml) for the complete list of dependencies.

## 1. Add Config files

To make the Test Coverage work, we have to use the *ProcessCoverageInMemProcessEngineConfiguration*.
Therefore, add [camunda.cfg.xml](../order-example/order-example-camunda7/src/test/resources/camunda.cfg.xml) to the resource folder of the test.
For a more verbose log, you can also add [logback-test.xml](../order-example/order-example-camunda7/src/test/resources/logback-test.xml) to the folder.

## 2. Create the Test

 **Miranum Platform** uses a special object with the name *process* to set the status of the running process instance.
For our test to work we have to mock this object.
Therefore, we create a class with an empty *setStatus()* method.

```java
public class ProcessMock
{
    public void setStatus(String status) {}
}
```

[ProcessMock](../order-example/order-example-camunda7/src/test/java/io/miragon/orderExample/ProcessMock.java)

Now we can create our actual test.
For more information and examples of how to create a unit test for a process, have a look at the above-mentioned links.

```java
@Deployment(resources = {"order-example.bpmn", "choose-distributor.dmn"})
public class OrderExampleTest
{
    public static final String PROCESS_KEY = "order-example";

    // ...

    @RegisterExtension
    public static ProcessEngineCoverageExtension extension = ProcessEngineCoverageExtension
            .builder().assertClassCoverageAtLeast(0.9).build();

    // ...

    @BeforeEach
    public void defaultScenario()
    {
        this.closeable = MockitoAnnotations.openMocks(this);
        Mocks.register("process", new ProcessMock());

        this.variables = createVariables()
                .putValue(VAR_CUSTOMER_NAME, "John Doe")
                .putValue(VAR_CUSTOMER_EMAIL, "john.doe@gmail.com")
                .putValue(VAR_STREET, "Unter den Linden 1")
                .putValue(VAR_ZIP_CODE, "10117")
                .putValue(VAR_CITY, "Berlin")
                .putValue(VAR_ITEMS, new Object[0]);


        //Happy-Path
        when(scenario.waitsAtServiceTask(TASK_CALCULATE_VALUE_OF_ITEMS)).thenReturn(task -> {
                task.complete(withVariables(VAR_VALUE_OF_ITEMS, 0));
        });

        when(scenario.waitsAtUserTask(TASK_SEND_ITEM))
                .thenReturn(TaskDelegate::complete);
    }

    @AfterEach
    public void releaseMocks() throws Exception
    {
        this.closeable.close();
    }

    @Test
    public void shouldExecuteHappyPath()
    {
        when(scenario.waitsAtUserTask(TASK_CHECK_ORDER)).thenReturn(task -> {
                task.complete(withVariables(VAR_IS_VALID, true));
        });

        Scenario.run(scenario)
                .startByKey(PROCESS_KEY, this.variables)
                .execute();

        verify(scenario)
                .hasFinished(END_EVENT_ORDER_PROCESSED);
    }

    @Test
    public void shouldEndWithOrderDeclined()
    {
        when(scenario.waitsAtUserTask(TASK_CHECK_ORDER)).thenReturn(task -> {
                task.complete(withVariables(VAR_IS_VALID, false));
        });

        Scenario.run(scenario)
                .startByKey(PROCESS_KEY, this.variables)
                .execute();

        verify(scenario)
                .hasFinished(END_EVENT_ORDER_DECLINED);
    }
}
```

[OrderExampleTest](../order-example/order-example-camunda7/src/test/java/io/miragon/orderExample/OrderExampleTest.java)

## 3. Execute the Test and Display the Code Coverage

After executing the test [camunda-process-test-coverage](https://github.com/camunda-community-hub/camunda-process-test-coverage) will create a report under `/target/process-test-coverage/io.miragon.orderExample.OrderExampleTest`.

You can display the report with the [Live Preview Plugin](https://marketplace.visualstudio.com/items?itemName=ms-vscode.live-server) inside VS Code.

1. Install the Plugin
2. Change the default Port
    > Note: This is necessary because we use the default port 3000 for *Miranum Platform**
    * Open the settings (Code > Preferences > Settings)
    * Search for "Live Preview: Port Number"
    * Change the port to 4000
3. Open the generated `report.html` and click on the preview icon ![live-preview-icon](../images/live-preview-icon.png) in the top right corner.

## What's next?

Once we are confident that our process is working as intended, the [next step](./setup-miranum-platform.md) is to set up the **Miranum Platform**.
