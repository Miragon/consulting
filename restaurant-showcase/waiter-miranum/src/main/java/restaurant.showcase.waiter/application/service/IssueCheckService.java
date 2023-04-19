package restaurant.showcase.waiter.application.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import restaurant.showcase.waiter.application.port.in.issueCheck.IssueCheckInCommand;
import restaurant.showcase.waiter.application.port.in.issueCheck.IssueCheckUseCase;
import restaurant.showcase.waiter.application.port.out.issueCheck.IssueCheckOutCommand;
import restaurant.showcase.waiter.application.port.out.issueCheck.IssueCheckOutPort;
import restaurant.showcase.waiter.domain.Order;
import restaurant.showcase.waiter.domain.events.CheckIssuedEvent;

@Slf4j
@Service
@AllArgsConstructor
public class IssueCheckService implements IssueCheckUseCase {

    private final IssueCheckOutPort issueCheckPort;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void issueCheck(IssueCheckInCommand issueCheckInCommand) {
        var order = new Order(issueCheckInCommand);
        log.info("[{}] Sending order data to payment service.", order.getOrderId());
        String response = issueCheckPort.issueCheck(new IssueCheckOutCommand(order));
        log.info("[{}] Payment responded: {}", order.getOrderId(), response);
        applicationEventPublisher.publishEvent(new CheckIssuedEvent(order));
    }
}
