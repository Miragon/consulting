package io.miragon.orderExample;

import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.scenario.ProcessScenario;
import org.camunda.bpm.scenario.Scenario;
import org.camunda.bpm.scenario.delegate.TaskDelegate;
import org.camunda.community.process_test_coverage.junit5.platform7.ProcessEngineCoverageExtension;
import static org.camunda.bpm.engine.variable.Variables.createVariables;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.withVariables;

@Deployment(resources = {"order-example.bpmn", "choose-distributor.dmn"})
public class OrderExampleTest
{
    public static final String PROCESS_KEY = "order-example";

    // BPMN Elements
    public static final String TASK_CHECK_ORDER = "Task_CheckOrder";
    public static final String TASK_CALCULATE_VALUE_OF_ITEMS = "Task_CalculateValueOfItems";
    public static final String TASK_SEND_ITEM = "Task_SendItem";
    public static final String END_EVENT_ORDER_PROCESSED = "EndEvent_OrderProcessed";
    public static final String END_EVENT_ORDER_DECLINED = "EndEvent_OrderDeclined";

    // Process Variables
    public static final String VAR_CUSTOMER_NAME = "customerName";
    public static final String VAR_CUSTOMER_EMAIL = "customerEmail";
    public static final String VAR_STREET = "street";
    public static final String VAR_ZIP_CODE = "zipCode";
    public static final String VAR_CITY = "city";
    public static final String VAR_ITEMS = "items";
    public static final String VAR_IS_VALID = "isValid";
    public static final String VAR_VALUE_OF_ITEMS = "valueOfItems";

    @RegisterExtension
    public static ProcessEngineCoverageExtension extension = ProcessEngineCoverageExtension
            .builder().assertClassCoverageAtLeast(0.9).build();

    @Mock
    private ProcessScenario scenario;

    private AutoCloseable closeable;

    private Map<String, Object> variables;

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
        when(scenario.waitsAtServiceTask(TASK_CALCULATE_VALUE_OF_ITEMS)).thenReturn(task -> task.complete(withVariables(VAR_VALUE_OF_ITEMS, 0)));

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
        when(scenario.waitsAtUserTask(TASK_CHECK_ORDER)).thenReturn(task -> task.complete(withVariables(VAR_IS_VALID, true)));

        Scenario.run(scenario)
                .startByKey(PROCESS_KEY, this.variables)
                .execute();

        verify(scenario)
                .hasFinished(END_EVENT_ORDER_PROCESSED);
    }

    @Test
    public void shouldEndWithOrderDeclined()
    {
        when(scenario.waitsAtUserTask(TASK_CHECK_ORDER)).thenReturn(task -> task.complete(withVariables(VAR_IS_VALID, false)));

        Scenario.run(scenario)
                .startByKey(PROCESS_KEY, this.variables)
                .execute();

        verify(scenario)
                .hasFinished(END_EVENT_ORDER_DECLINED);
    }
}
