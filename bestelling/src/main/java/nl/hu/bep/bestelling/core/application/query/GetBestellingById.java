package nl.hu.bep.bestelling.core.application.query;

import java.util.UUID;

public class GetBestellingById {
    private final UUID id;

    public GetBestellingById(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}

