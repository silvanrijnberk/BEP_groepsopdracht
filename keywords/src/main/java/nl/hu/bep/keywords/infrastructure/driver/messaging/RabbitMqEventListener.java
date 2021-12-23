package nl.hu.bep.keywords.infrastructure.driver.messaging;

import nl.hu.bep.keywords.core.application.KeywordsCommandHandler;
import nl.hu.bep.keywords.core.application.command.*;
import nl.hu.bep.keywords.infrastructure.driver.messaging.event.KeywordsEvent;
import nl.hu.bep.keywords.infrastructure.driver.messaging.event.keyword.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    public RabbitMqEventListener(KeywordsCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    private final KeywordsCommandHandler commandHandler;

    @RabbitListener(queues = "#{'${messaging.queue.all-keywords}'}")
    public void listen(KeywordsEvent event) {
        switch (event.getEventKey()) {
            case GebruikerAddedKeyword.KEY:
                this.handle((GebruikerAddedKeyword) event);
                break;
            case GebruikerRemovedKeyword.KEY:
                this.handle((GebruikerRemovedKeyword) event);
                break;
            case BeoordelingAddedKeyword.KEY:
                this.handle((BeoordelingAddedKeyword) event);
                break;
            case BeoordelingRemovedKeyword.KEY:
                this.handle((BeoordelingRemovedKeyword) event);
                break;
            case GerechtAddedKeyword.KEY:
                this.handle((GerechtAddedKeyword) event);
                break;
            case GerechtRemovedKeyword.KEY:
                this.handle((GerechtRemovedKeyword) event);
                break;
            case BestellingAddedKeyword.KEY:
                this.handle((BestellingAddedKeyword) event);
                break;
            case BestellingRemovedKeyword.KEY:
                this.handle((BestellingRemovedKeyword) event);
                break;
        }
    }

    private void handle(GebruikerAddedKeyword event) {
        this.commandHandler.handle(new AddGebruikerToKeyword(event.getGebruiker(), event.getKeyword()));
    }

    private void handle(GebruikerRemovedKeyword event) {
        this.commandHandler.handle(new RemoveGebruikerFromKeyword(event.getGebruiker(), event.getKeyword()));
    }

    private void handle(GerechtAddedKeyword event) {
        this.commandHandler.handle(new AddGerechtToKeyword(event.getGerecht(), event.getKeyword()));
    }

    private void handle(GerechtRemovedKeyword event) {
        this.commandHandler.handle(new RemoveGerechtFromKeyword(event.getGerecht(), event.getKeyword()));
    }
    private void handle(BeoordelingAddedKeyword event) {
        this.commandHandler.handle(new AddBeoordelingToKeyword(event.getBeoordeling(), event.getKeyword()));
    }

    private void handle(BeoordelingRemovedKeyword event) {
        this.commandHandler.handle(new RemoveBeoordelingFromKeyword(event.getBeoordeling(), event.getKeyword()));
    }
    private void handle(BestellingAddedKeyword event) {
        this.commandHandler.handle(new AddBestellingToKeyword(event.getBestelling(), event.getKeyword()));
    }

    private void handle(BestellingRemovedKeyword event) {
        this.commandHandler.handle(new RemoveBestellingFromKeyword(event.getBestelling(), event.getKeyword()));
    }
}
