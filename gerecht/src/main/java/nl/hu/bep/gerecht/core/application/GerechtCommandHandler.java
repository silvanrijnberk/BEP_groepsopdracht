package nl.hu.bep.gerecht.core.application;

import nl.hu.bep.gerecht.core.application.command.AddKeyword;
import nl.hu.bep.gerecht.core.application.command.EditGerecht;
import nl.hu.bep.gerecht.core.application.command.RegisterGerecht;
import nl.hu.bep.gerecht.core.application.command.RemoveKeyword;
import nl.hu.bep.gerecht.core.domain.Gerecht;
import nl.hu.bep.gerecht.core.domain.event.GerechtEvent;
import nl.hu.bep.gerecht.core.domain.exception.GerechtNotFound;
import nl.hu.bep.gerecht.core.port.messaging.GerechtEventPublisher;
import nl.hu.bep.gerecht.core.port.storage.GerechtRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GerechtCommandHandler {
    private final GerechtRepository repository;
    private final GerechtEventPublisher eventPublisher;

    public GerechtCommandHandler(GerechtRepository repository, GerechtEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public Gerecht handle(RegisterGerecht command) {
        Gerecht gerecht = new Gerecht(command.getNaam(), command.getBeschrijving(), command.getIngredienten() ,command.getPrijs());
        this.publishEventsFor(gerecht);
        this.repository.save(gerecht);
        return gerecht;
    }

    public Gerecht handle(EditGerecht command){
        Gerecht gerecht = this.getGerechtById(command.getId());
        gerecht.edit(command.getBeschrijving(),command.getIngredienten());
        this.publishEventsFor(gerecht);
        this.repository.save(gerecht);

        return gerecht;
    }
    public Gerecht handle(AddKeyword command) {
        Gerecht gerecht = this.getGerechtById(command.getId());

        gerecht.addKeyword(command.getKeyword());

        this.publishEventsFor(gerecht);
        this.repository.save(gerecht);

        return gerecht;
    }

    public Gerecht handle(RemoveKeyword command) {
        Gerecht gerecht = this.getGerechtById(command.getId());

        gerecht.removeKeyword(command.getKeyword());
        this.publishEventsFor(gerecht);
        this.repository.save(gerecht);

        return gerecht;
    }

    private Gerecht getGerechtById(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new GerechtNotFound(id.toString()));
    }


    private void publishEventsFor(Gerecht gerecht) {
        List<GerechtEvent> events = gerecht.listEvents();
        events.forEach(eventPublisher::publish);
        gerecht.clearEvents();
    }

}
