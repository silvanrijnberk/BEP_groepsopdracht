package nl.hu.bep.bestelling.core.application;

import nl.hu.bep.bestelling.core.application.command.AddKeyword;
import nl.hu.bep.bestelling.core.application.command.RegisterBestelling;
import nl.hu.bep.bestelling.core.application.command.RemoveKeyword;
import nl.hu.bep.bestelling.core.domain.Bestelling;
import nl.hu.bep.bestelling.core.domain.event.BestellingEvent;
import nl.hu.bep.bestelling.core.domain.exception.BestellingNotFound;
import nl.hu.bep.bestelling.core.port.messaging.BestellingEventPublisher;
import nl.hu.bep.bestelling.core.port.storage.BestellingRepository;
import nl.hu.bep.bestelling.core.port.storage.GerechtRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BestellingCommandHandler {
    private final BestellingRepository repository;
    private final BestellingEventPublisher gebruikerEventPublisher;
    private final GerechtRepository gerechtGateway;

    public BestellingCommandHandler(BestellingRepository repository, BestellingEventPublisher gebruikerEventPublisher, GerechtRepository gerechtGateway) {
        this.repository = repository;
        this.gebruikerEventPublisher = gebruikerEventPublisher;
        this.gerechtGateway = gerechtGateway;
    }

    public Bestelling handle(RegisterBestelling command) {
        Bestelling gebruiker = new Bestelling(command.getGebruiker(), command.getStatus(), command.getOpmerkingen(), command.getDate(), command.getGerechten());
        this.publishEventsFor(gebruiker);
        this.repository.save(gebruiker);
        return gebruiker;
    }

    public Bestelling handle(AddKeyword command) {
        Bestelling gebruiker = this.getBestellingById(command.getId());

        gebruiker.addKeyword(command.getKeyword());
        this.gerechtGateway.findByKeywordsEquals(command.getKeyword()).forEach(gebruiker::addGerecht);

        this.publishEventsFor(gebruiker);
        this.repository.save(gebruiker);

        return gebruiker;
    }

    public Bestelling handle(RemoveKeyword command) {
        Bestelling gebruiker = this.getBestellingById(command.getId());

        gebruiker.removeKeyword(command.getKeyword());
        this.publishEventsFor(gebruiker);
        this.repository.save(gebruiker);

        return gebruiker;
    }

    private Bestelling getBestellingById(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new BestellingNotFound(id.toString()));
    }


    private void publishEventsFor(Bestelling gebruiker) {
        List<BestellingEvent> events = gebruiker.listEvents();
        events.forEach(gebruikerEventPublisher::publish);
        gebruiker.clearEvents();
    }
}
