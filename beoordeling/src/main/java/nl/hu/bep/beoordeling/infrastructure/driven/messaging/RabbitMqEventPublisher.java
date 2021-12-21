package nl.hu.bep.beoordeling.infrastructure.driven.messaging;

import nl.hu.bep.beoordeling.core.domain.event.BeoordelingEvent;
import nl.hu.bep.beoordeling.core.port.messaging.BeoordelingEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements BeoordelingEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String jobBoardExchange;

    public RabbitMqEventPublisher(
            RabbitTemplate rabbitTemplate,
            String jobBoardExchange
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.jobBoardExchange = jobBoardExchange;
    }

    public void publish(BeoordelingEvent event) {
        this.rabbitTemplate.convertAndSend(jobBoardExchange, event.getEventKey(), event);
    }
}
