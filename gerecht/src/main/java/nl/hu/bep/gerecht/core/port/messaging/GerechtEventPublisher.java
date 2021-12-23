package nl.hu.bep.gerecht.core.port.messaging;

import nl.hu.bep.gerecht.core.domain.event.GerechtEvent;

public interface GerechtEventPublisher {
    void publish(GerechtEvent event);
}
