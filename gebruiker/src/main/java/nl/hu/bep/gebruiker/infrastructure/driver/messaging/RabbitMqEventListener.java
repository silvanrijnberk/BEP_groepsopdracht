package nl.hu.bep.gebruiker.infrastructure.driver.messaging;

import nl.hu.bep.gebruiker.core.application.GebruikerCommandHandler;
import nl.hu.bep.gebruiker.core.application.command.MatchGebruikers;
import nl.hu.bep.gebruiker.core.application.command.UnmatchGebruikers;
import nl.hu.bep.gebruiker.infrastructure.driver.event.BestellingKeywordEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final GebruikerCommandHandler commandHandler;

    public RabbitMqEventListener(GebruikerCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.job-keywords}'}")
    void listen(BestellingKeywordEvent event) {
        switch (event.eventKey) {
            case "keywords.bestelling.added":
                this.commandHandler.handle(
                        new MatchGebruikers(event.bestelling, event.keyword)
                );
                break;
            case "keywords.bestelling.removed":
                this.commandHandler.handle(
                        new UnmatchGebruikers(event.bestelling, event.keyword)
                );
                break;
        }
    }
}
