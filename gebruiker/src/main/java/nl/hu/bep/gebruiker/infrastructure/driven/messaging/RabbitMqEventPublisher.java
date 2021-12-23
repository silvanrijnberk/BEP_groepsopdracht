package nl.hu.bep.gebruiker.infrastructure.driven.messaging;

import nl.hu.bep.gebruiker.core.domain.event.GebruikerEvent;
import nl.hu.bep.gebruiker.core.port.messaging.GebruikerEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements GebruikerEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String bestellingenboardExchange;

    public RabbitMqEventPublisher(
            RabbitTemplate rabbitTemplate,
            String bestellingenboardExchange
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.bestellingenboardExchange = bestellingenboardExchange;
    }

    public void publish(GebruikerEvent event) {
        this.rabbitTemplate.convertAndSend(bestellingenboardExchange, event.getEventKey(), event);
    }
}
