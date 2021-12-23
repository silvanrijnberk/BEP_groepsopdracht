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
    private final BestellingEventPublisher bestellingEventPublisher;
    private final GerechtRepository gerechtGateway;

    public BestellingCommandHandler(BestellingRepository repository, BestellingEventPublisher bestellingEventPublisher, GerechtRepository gerechtGateway) {
        this.repository = repository;
        this.bestellingEventPublisher = bestellingEventPublisher;
        this.gerechtGateway = gerechtGateway;
    }

    public Bestelling handle(RegisterBestelling command) {
        Bestelling bestelling = new Bestelling(command.getGebruiker(), command.getStatus(), command.getOpmerkingen(), command.getDate(), command.getGerechten());
        this.publishEventsFor(bestelling);
        this.repository.save(bestelling);
        return bestelling;
    }

    public Bestelling handle(AddKeyword command) {
        Bestelling bestelling = this.getBestellingById(command.getId());

        bestelling.addKeyword(command.getKeyword());
        this.gerechtGateway.findByKeywordsEquals(command.getKeyword()).forEach(bestelling::addGerecht);

        this.publishEventsFor(bestelling);
        this.repository.save(bestelling);

        return bestelling;
    }

    public Bestelling handle(RemoveKeyword command) {
        Bestelling bestelling = this.getBestellingById(command.getId());

        bestelling.removeKeyword(command.getKeyword());
        this.publishEventsFor(bestelling);
        this.repository.save(bestelling);

        return bestelling;
    }

    private Bestelling getBestellingById(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new BestellingNotFound(id.toString()));
    }


    private void publishEventsFor(Bestelling bestelling) {
        List<BestellingEvent> events = bestelling.listEvents();
        events.forEach(bestellingEventPublisher::publish);
        bestelling.clearEvents();
    }
}
