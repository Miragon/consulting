package restaurant.showcase.waiter.domain.events;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public abstract class DomainEvent {

    ObjectMapper oMapper = new ObjectMapper();

    public HashMap<String, Object> convertToMap() {
        return oMapper.convertValue(this, HashMap.class);
    }
}
