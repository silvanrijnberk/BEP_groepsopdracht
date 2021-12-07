package nl.hu.bep.gebruiker.core.application.query;

import java.util.UUID;

public class GetGebruikerById {
    private final UUID id;

    public GetGebruikerById(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}

