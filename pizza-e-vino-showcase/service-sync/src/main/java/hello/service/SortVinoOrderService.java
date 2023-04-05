package hello.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component(value = "sortVinoOrder")
public class SortVinoOrderService {
    private final RestTemplate restTemplate;

    public SortVinoOrderService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    long waitTimeMillis = 1000;

    public void execute() throws InterruptedException {

        int loops = 3;
            System.out.println("We're Working on getting you Vino! -- " );
            while(loops >= 0) {
                System.out.print("..");
                Thread.sleep(waitTimeMillis);
                loops--;
            }
            System.out.println();
            System.out.println("Vino is up -- " );

        System.out.println("All good - order is complete");
    }
}

