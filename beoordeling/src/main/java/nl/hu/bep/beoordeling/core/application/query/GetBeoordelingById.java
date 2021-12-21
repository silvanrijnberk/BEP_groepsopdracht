package nl.hu.bep.beoordeling.core.application.query;

import java.util.UUID;

public class GetBeoordelingById {
    private final UUID id;

    public GetBeoordelingById(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
