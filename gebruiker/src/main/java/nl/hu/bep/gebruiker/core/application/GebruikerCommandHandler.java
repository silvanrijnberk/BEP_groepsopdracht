package nl.hu.bep.gebruiker.core.application;

import nl.hu.bep.gebruiker.core.application.command.*;
import nl.hu.bep.gebruiker.core.domain.Adres;
import nl.hu.bep.gebruiker.core.domain.Gebruiker;
import nl.hu.bep.gebruiker.core.domain.event.GebruikerEvent;
import nl.hu.bep.gebruiker.core.domain.exception.GebruikerNotFound;
import nl.hu.bep.gebruiker.core.port.messaging.GebruikerEventPublisher;
import nl.hu.bep.gebruiker.core.port.storage.AdresRepository;
import nl.hu.bep.gebruiker.core.port.storage.BestellingenRepository;
import nl.hu.bep.gebruiker.core.port.storage.GebruikerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GebruikerCommandHandler {
    private final GebruikerRepository repository;
    private final GebruikerEventPublisher gebruikerEventPublisher;
    private final AdresRepository adresRepository;
    private final BestellingenRepository bestellingenGateway;

    public GebruikerCommandHandler(GebruikerRepository repository, GebruikerEventPublisher gebruikerEventPublisher, AdresRepository adresRepository, BestellingenRepository bestellingenGateway) {
        this.repository = repository;
        this.gebruikerEventPublisher = gebruikerEventPublisher;
        this.adresRepository = adresRepository;
        this.bestellingenGateway  = bestellingenGateway;

    }

    public Gebruiker handle(RegisterGebruiker command) {

        Adres adres = new Adres(command.getStreetname(), command.getNumber(), command.getPostalcode(), command.getCity(), command.getProvince());
        Gebruiker gebruiker = new Gebruiker(command.getFirstname(), command.getLastname(), command.getEmail(), adres.getId());
        this.adresRepository.save(adres);
        this.publishEventsFor(gebruiker);
        this.repository.save(gebruiker);
        return gebruiker;
    }

    public Gebruiker handle(RenameGebruiker command){
        Gebruiker gebruiker = this.getGebruikerById(command.getId());

        gebruiker.rename(command.getFirstname(), command.getLastname());
        this.publishEventsFor(gebruiker);
        this.repository.save(gebruiker);

        return gebruiker;
    }
    public Gebruiker handle(AddKeyword command) {
        Gebruiker gebruiker = this.getGebruikerById(command.getId());

        gebruiker.addKeyword(command.getKeyword());
        this.adresRepository.findByKeywordsEquals(command.getKeyword()).forEach(gebruiker::setAdres);

        this.publishEventsFor(gebruiker);
        this.repository.save(gebruiker);

        return gebruiker;
    }

    public Gebruiker handle(RemoveKeyword command) {
        Gebruiker gebruiker = this.getGebruikerById(command.getId());

        gebruiker.removeKeyword(command.getKeyword());
        this.publishEventsFor(gebruiker);
        this.repository.save(gebruiker);

        return gebruiker;
    }

    private Gebruiker getGebruikerById(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new GebruikerNotFound(id.toString()));
    }


    private void publishEventsFor(Gebruiker gebruiker) {
        List<GebruikerEvent> events = gebruiker.listEvents();
        events.forEach(gebruikerEventPublisher::publish);
        gebruiker.clearEvents();
    }

    public void handle(AddBestelling command) {
        Gebruiker gebruiker = this.getGebruikerById(command.getGebruiker());
        gebruiker.addBestelling(command.getBestelling());
        this.repository.save(gebruiker);
    }

}
