package nl.hu.bep.bestelling.infrastructure.driven.messaging;

import nl.hu.bep.bestelling.core.domain.event.BestellingEvent;
import nl.hu.bep.bestelling.core.port.messaging.BestellingEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements BestellingEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String GerechtExchange;

    public RabbitMqEventPublisher(
            RabbitTemplate rabbitTemplate,
            String jobBoardExchange
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.GerechtExchange = jobBoardExchange;
    }

    public void publish(BestellingEvent event) {
        this.rabbitTemplate.convertAndSend(GerechtExchange, event.getEventKey(), event);
    }
}
