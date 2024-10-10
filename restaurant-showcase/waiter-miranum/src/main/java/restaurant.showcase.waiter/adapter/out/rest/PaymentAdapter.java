package restaurant.showcase.waiter.adapter.out.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClientRequest;
import restaurant.showcase.waiter.application.port.out.issueCheck.IssueCheckOutCommand;
import restaurant.showcase.waiter.application.port.out.issueCheck.IssueCheckOutPort;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class PaymentAdapter implements IssueCheckOutPort {
    private final WebClient paymentApiClient;
    private final ObjectMapper objectMapper;

    @Override
    public String issueCheck(IssueCheckOutCommand command) {
        String body;
        try {
            body = objectMapper.writeValueAsString(command);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return paymentApiClient.post()
                .uri("/payment")
                .httpRequest(httpRequest -> {
                    HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofSeconds(11));
                })
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN)
                .body(Mono.just(body), String.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
