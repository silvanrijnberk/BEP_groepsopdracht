package nl.hu.bep.gebruiker.core.application;

import nl.hu.bep.gebruiker.core.application.command.AddKeyword;
import nl.hu.bep.gebruiker.core.application.command.RegisterGebruiker;
import nl.hu.bep.gebruiker.core.application.command.RemoveKeyword;
import nl.hu.bep.gebruiker.core.domain.Gebruiker;
import nl.hu.bep.gebruiker.core.domain.event.GebruikerEvent;
import nl.hu.bep.gebruiker.core.domain.exception.GebruikerNotFound;
import nl.hu.bep.gebruiker.core.port.messaging.GebruikerEventPublisher;
import nl.hu.bep.gebruiker.core.port.storage.AdresRepository;
import nl.hu.bep.gebruiker.core.port.storage.GebruikerRepository;

import java.util.List;
import java.util.UUID;

public class GebruikerCommandHandler {
    private final GebruikerRepository repository;
    private final GebruikerEventPublisher eventPublisher;
    private final AdresRepository adresGateway;

    public GebruikerCommandHandler(GebruikerRepository repository, GebruikerEventPublisher eventPublisher, AdresRepository adresGateway) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
        this.adresGateway = adresGateway;
    }

    public Gebruiker handle(RegisterGebruiker command) {
        Gebruiker gebruiker = new Gebruiker(command.getFirstname(), command.getLastname(), command.getEmail(), command.getPassword());
        this.publishEventsFor(gebruiker);
        this.repository.save(gebruiker);
        return gebruiker;
    }
    public Gebruiker handle(AddKeyword command) {
        Gebruiker gebruiker = this.getCandidateById(command.getId());

        gebruiker.addKeyword(command.getKeyword());
        this.adresGateway.findByKeywordsEquals(command.getKeyword()).forEach(gebruiker::setAdres);

        this.publishEventsFor(gebruiker);
        this.repository.save(gebruiker);

        return gebruiker;
    }

    public Gebruiker handle(RemoveKeyword command) {
        Gebruiker gebruiker = this.getCandidateById(command.getId());

        gebruiker.removeKeyword(command.getKeyword());
        this.publishEventsFor(gebruiker);
        this.repository.save(gebruiker);

        return gebruiker;
    }

    private Gebruiker getCandidateById(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new GebruikerNotFound(id.toString()));
    }


    private void publishEventsFor(Gebruiker gebruiker) {
        List<GebruikerEvent> events = gebruiker.listEvents();
        events.forEach(eventPublisher::publish);
        gebruiker.clearEvents();
    }
}
