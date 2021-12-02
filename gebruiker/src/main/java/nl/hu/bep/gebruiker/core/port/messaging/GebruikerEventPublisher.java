package nl.hu.bep.gebruiker.core.port.messaging;

import nl.hu.bep.gebruiker.core.domain.event.GebruikerEvent;

public interface GebruikerEventPublisher {
        void publish(GebruikerEvent event);
}
