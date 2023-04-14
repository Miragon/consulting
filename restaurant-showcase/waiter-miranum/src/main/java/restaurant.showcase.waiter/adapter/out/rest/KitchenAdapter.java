package restaurant.showcase.waiter.adapter.out.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClientRequest;
import restaurant.showcase.waiter.application.port.out.prepareMeal.PrepareMealOutCommand;
import restaurant.showcase.waiter.application.port.out.prepareMeal.PrepareMealOutPort;

import java.time.Duration;

@Service
@AllArgsConstructor
public class KitchenAdapter implements PrepareMealOutPort {

    private final WebClient kitchenApiClient;
    private final ObjectMapper objectMapper;

    @Override
    public String prepareMeal(PrepareMealOutCommand command) {
        String body;
        try {
            body = objectMapper.writeValueAsString(command);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return kitchenApiClient.post()
                .uri("/order")
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
