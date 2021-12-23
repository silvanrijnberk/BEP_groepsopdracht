package nl.hu.bep.bestelling.core.domain.event;

import java.util.UUID;

public class BestellingUnregistered extends BestellingEvent {
    private final UUID gebruiker;

    public BestellingUnregistered(UUID id) {
        this.gebruiker = id;
    }

    @Override
    public String getEventKey() {
        return "bestelling.unregistered";
    }

    public UUID getBestelling() {
        return gebruiker;
    }
}
