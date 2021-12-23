package nl.hu.bep.gebruiker.infrastructure.driver.messaging;

import nl.hu.bep.gebruiker.core.application.GebruikerCommandHandler;
import nl.hu.bep.gebruiker.core.application.command.AddBestelling;
import nl.hu.bep.gebruiker.infrastructure.driver.event.BestellingKeywordEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final GebruikerCommandHandler commandHandler;

    public RabbitMqEventListener(GebruikerCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.bestelling}'}")
    void listen(BestellingKeywordEvent event) {
        System.out.println(event.eventKey);
        switch (event.eventKey) {
            case "bestelling.registered":
                this.commandHandler.handle(
                        new AddBestelling(event.bestelling, event.keyword, event.gebruiker)

                );
                break;
        }
    }
}
