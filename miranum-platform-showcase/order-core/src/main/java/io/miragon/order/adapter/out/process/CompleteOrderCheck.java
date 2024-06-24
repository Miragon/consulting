package io.miragon.order.adapter.out.process;

import io.miragon.order.application.port.out.CompleteOrderCheckPort;

public class CompleteOrderCheck implements CompleteOrderCheckPort
{
    @Override
    public boolean completeCheck(String processInstanceId)
    {
        // TODO: get task id
        //  * /task
        //    * processInstanceId
        //    * taskDefinitionKey

        return false;
    }
}
