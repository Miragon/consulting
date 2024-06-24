package io.miragon.order.adapter.out.process;

import io.miragon.miranum.connect.process.api.ProcessApi;
import io.miragon.miranum.connect.process.api.StartProcessCommand;
import io.miragon.order.application.port.out.StartProcessPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class StartProcessAdapter implements StartProcessPort
{
    private final ProcessApi processApi;

    @Override
    public void startProcess(String orderId, double orderValue)
    {
        String processKey = "OrderProcess";
        Map<String, Object> variables = Map.of("orderId", orderId, "orderValue", orderValue);
        var startProcessCommand = new StartProcessCommand(processKey, variables);
        processApi.startProcess(startProcessCommand);
    }
}
