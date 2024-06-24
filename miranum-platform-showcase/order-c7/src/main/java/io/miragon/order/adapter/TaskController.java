package io.miragon.order.adapter;

import lombok.AllArgsConstructor;
import org.camunda.community.rest.client.api.ProcessInstanceApi;
import org.camunda.community.rest.client.api.TaskApi;
import org.camunda.community.rest.client.dto.CompleteTaskDto;
import org.camunda.community.rest.client.dto.TaskDto;
import org.camunda.community.rest.client.dto.TaskQueryDto;
import org.camunda.community.rest.client.dto.VariableValueDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/task")
@AllArgsConstructor
public class TaskController
{
    private final TaskApi taskApi;

    private final ProcessInstanceApi processInstanceApi;

    @GetMapping()
    public ResponseEntity<Map<String, Map<String, Object>>> getTask()
    {
        TaskQueryDto taskQueryDto = new TaskQueryDto()
                .taskDefinitionKey("Task_CheckOrderSales");

        try {
            List<TaskDto> tasks = taskApi.queryTasks(0, 10, taskQueryDto);
            Map<String, Map<String, Object>> taskVariables = new HashMap<>();

            for (TaskDto task : tasks) {
                Map<String, VariableValueDto> variables =
                        processInstanceApi.getProcessInstanceVariables(task.getProcessInstanceId(), false);

                Map<String, Object> variablesMap = variables
                        .entrySet()
                        .stream()
                        .map(entry -> Map.entry(entry.getKey(), entry.getValue().getValue()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                taskVariables.put(task.getId(), variablesMap);
            }

            return new ResponseEntity<>(taskVariables, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PostMapping("/complete/{taskId}")
    public ResponseEntity<Boolean> completeTask(@PathVariable String taskId, @RequestBody boolean isAccepted)
    {
        try {
            CompleteTaskDto completeTaskDto = new CompleteTaskDto();
            completeTaskDto.setVariables(Map.of("isAccepted", new VariableValueDto().value(isAccepted)));

            taskApi.complete(taskId, completeTaskDto);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
