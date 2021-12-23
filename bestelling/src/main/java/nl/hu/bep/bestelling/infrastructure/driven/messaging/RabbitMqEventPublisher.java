package nl.hu.bep.bestelling.infrastructure.driven.messaging;

import nl.hu.bep.bestelling.core.domain.event.BestellingEvent;
import nl.hu.bep.bestelling.core.port.messaging.BestellingEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements BestellingEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String bestellingenboardExchange;

    public RabbitMqEventPublisher(
            RabbitTemplate rabbitTemplate,
            String bestellingenboardExchange
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.bestellingenboardExchange = bestellingenboardExchange;
    }

    public void publish(BestellingEvent event) {
        System.out.println("publish");
        this.rabbitTemplate.convertAndSend(bestellingenboardExchange, event.getEventKey(), event);
    }
}
