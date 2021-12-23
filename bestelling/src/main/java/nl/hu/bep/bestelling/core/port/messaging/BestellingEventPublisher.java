package nl.hu.bep.bestelling.core.port.messaging;

import nl.hu.bep.bestelling.core.domain.event.BestellingEvent;

public interface BestellingEventPublisher {
        void publish(BestellingEvent event);
}
