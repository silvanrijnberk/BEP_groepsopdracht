package nl.hu.bep.gebruiker.core.domain.event;

import java.util.UUID;

public class GebruikerUnregistered extends GebruikerEvent {
    private final UUID gebruiker;

    public GebruikerUnregistered(UUID id) {
        this.gebruiker = id;
    }

    @Override
    public String getEventKey() {
        return "gebruiker.unregistered";
    }

    public UUID getGebruiker() {
        return gebruiker;
    }
}
