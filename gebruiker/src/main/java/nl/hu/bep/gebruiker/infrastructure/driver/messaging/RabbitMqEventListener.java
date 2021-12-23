package nl.hu.bep.gebruiker.infrastructure.driver.messaging;

import nl.hu.bep.gebruiker.core.application.GebruikerCommandHandler;
import nl.hu.bep.gebruiker.core.application.command.MatchGebruikers;
import nl.hu.bep.gebruiker.core.application.command.UnmatchGebruikers;
import nl.hu.bep.gebruiker.infrastructure.driver.event.GebruikerKeywordEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final GebruikerCommandHandler commandHandler;

    public RabbitMqEventListener(GebruikerCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.gebruiker-keywords}'}")
    void listen(GebruikerKeywordEvent event) {
        switch (event.eventKey) {
            case "keywords.gebruiker.added":
                this.commandHandler.handle(
                        new MatchGebruikers(event.gebruiker, event.keyword)
                );
                break;
            case "keywords.gebruiker.removed":
                this.commandHandler.handle(
                        new UnmatchGebruikers(event.gebruiker, event.keyword)
                );
                break;
        }
    }
}
