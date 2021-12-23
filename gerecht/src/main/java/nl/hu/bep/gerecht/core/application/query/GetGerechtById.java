package nl.hu.bep.gerecht.core.application.query;

import java.util.UUID;

public class GetGerechtById {
    private final UUID id;

    public GetGerechtById(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
