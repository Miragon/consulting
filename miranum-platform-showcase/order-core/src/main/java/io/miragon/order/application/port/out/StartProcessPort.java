package io.miragon.order.application.port.out;

public interface StartProcessPort
{
    void startProcess(String orderId, double orderValue);
}
