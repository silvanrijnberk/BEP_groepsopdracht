package nl.hu.bep.beoordeling.core.application;

import nl.hu.bep.beoordeling.core.application.command.AddKeyword;
import nl.hu.bep.beoordeling.core.application.command.EditBeoordeling;
import nl.hu.bep.beoordeling.core.application.command.RegisterBeoordeling;
import nl.hu.bep.beoordeling.core.application.command.RemoveKeyword;
import nl.hu.bep.beoordeling.core.domain.Beoordeling;
import nl.hu.bep.beoordeling.core.domain.event.BeoordelingEvent;
import nl.hu.bep.beoordeling.core.domain.exception.BeoordelingNotFound;
import nl.hu.bep.beoordeling.core.port.messaging.BeoordelingEventPublisher;
import nl.hu.bep.beoordeling.core.port.storage.BeoordelingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BeoordelingCommandHandler {
    private final BeoordelingRepository repository;
    private final BeoordelingEventPublisher eventPublisher;

    public BeoordelingCommandHandler(BeoordelingRepository repository, BeoordelingEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public Beoordeling handle(RegisterBeoordeling command) {
        Beoordeling beoordeling = new Beoordeling(command.getId(),command.getFirstname(), command.getLastname(), command.getEmail(), command.getOmschrijving(),command.getCijfer());
        this.publishEventsFor(beoordeling);
        this.repository.save(beoordeling);
        return beoordeling;
    }

    public Beoordeling handle(EditBeoordeling command){
        Beoordeling beoordeling = this.getBeoordelingById(command.getId());

        beoordeling.edit(command.getBeschrijving(),command.getCijfer());
        this.publishEventsFor(beoordeling);
        this.repository.save(beoordeling);

        return beoordeling;
    }
    public Beoordeling handle(AddKeyword command) {
        Beoordeling beoordeling = this.getBeoordelingById(command.getId());

        beoordeling.addKeyword(command.getKeyword());

        this.publishEventsFor(beoordeling);
        this.repository.save(beoordeling);

        return beoordeling;
    }

    public Beoordeling handle(RemoveKeyword command) {
        Beoordeling beoordeling = this.getBeoordelingById(command.getId());

        beoordeling.removeKeyword(command.getKeyword());
        this.publishEventsFor(beoordeling);
        this.repository.save(beoordeling);

        return beoordeling;
    }

    private Beoordeling getBeoordelingById(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new BeoordelingNotFound(id.toString()));
    }


    private void publishEventsFor(Beoordeling beoordeling) {
        List<BeoordelingEvent> events = beoordeling.listEvents();
        events.forEach(eventPublisher::publish);
        beoordeling.clearEvents();
    }

}
