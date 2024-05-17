package io.miragon.order.application.port.out;

public interface CompleteOrderCheckPort
{
    boolean completeCheck(String processInstanceId);
}
