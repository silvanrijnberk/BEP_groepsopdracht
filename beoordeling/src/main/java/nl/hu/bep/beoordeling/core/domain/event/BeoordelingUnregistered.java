package nl.hu.bep.beoordeling.core.domain.event;

import java.util.UUID;

public class BeoordelingUnregistered extends BeoordelingEvent {
    private final UUID beoordeling;

    public BeoordelingUnregistered(UUID id) {
        this.beoordeling = id;
    }

    @Override
    public String getEventKey() {
        return "beoordeling.unregistered";
    }

    public UUID getBeoordeling() {
        return beoordeling;
    }
}
