package restaurant.showcase.waiter.adapter.out.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClientRequest;
import restaurant.showcase.waiter.application.port.out.prepareMeal.PrepareMealOutCommand;
import restaurant.showcase.waiter.application.port.out.prepareMeal.PrepareMealPort;

import java.time.Duration;

@Service
@AllArgsConstructor
public class KitchenAdapter implements PrepareMealPort {

    private final WebClient kitchenApiClient;

    @Override
    public String prepareMeal(PrepareMealOutCommand command) {
        return kitchenApiClient.post()
                .uri("/order")
                .httpRequest(httpRequest -> {
                    HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofSeconds(11));
                })
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromProducer(Mono.just(command), PrepareMealOutCommand.class))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
