package nl.hu.bep.gerecht.core.domain.event;

import java.util.UUID;

public class GerechtUnregistered extends GerechtEvent {
    private final UUID gerecht;

    public GerechtUnregistered(UUID id) {
        this.gerecht = id;
    }

    @Override
    public String getEventKey() {
        return "gerecht.unregistered";
    }

    public UUID getGerecht() {
        return gerecht;
    }
}
