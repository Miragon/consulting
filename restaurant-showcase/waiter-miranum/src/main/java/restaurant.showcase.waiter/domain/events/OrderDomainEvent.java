package restaurant.showcase.waiter.domain.events;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public abstract class OrderDomainEvent {
    ObjectMapper oMapper = new ObjectMapper();

    public abstract HashMap<String, Object> convertValuesToMap();
}
