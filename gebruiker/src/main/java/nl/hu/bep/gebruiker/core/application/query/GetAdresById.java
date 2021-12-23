package nl.hu.bep.gebruiker.core.application.query;

import java.util.UUID;

public class GetAdresById {
    private final UUID id;

    public GetAdresById(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
