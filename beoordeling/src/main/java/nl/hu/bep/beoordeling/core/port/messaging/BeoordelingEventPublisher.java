package nl.hu.bep.beoordeling.core.port.messaging;

import nl.hu.bep.beoordeling.core.domain.event.BeoordelingEvent;

public interface BeoordelingEventPublisher {
    void publish(BeoordelingEvent event);
}
