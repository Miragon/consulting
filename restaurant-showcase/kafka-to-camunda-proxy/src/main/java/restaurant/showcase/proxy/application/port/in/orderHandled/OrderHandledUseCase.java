package restaurant.showcase.proxy.application.port.in.orderHandled;

public interface OrderHandledUseCase {
        void onOrderHandled(OrderHandledInCommand orderHandledInCommand);
}
