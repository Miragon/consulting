package restaurant.showcase.proxy.application.port.out.orderHandled;

public interface OrderHandledOutPort {

    void onOrderHandled(OrderHandledOutCommand orderHandledOutCommand);
}
