package nl.hu.bep.bestelling.core.domain.event;

import java.util.UUID;

public class BestellingRegistered extends BestellingEvent{
    private final UUID bestelling;
    private final UUID gebruiker;

    public BestellingRegistered(UUID id, UUID gebruiker) {
        this.bestelling = id;
        this.gebruiker = gebruiker;
    }

    @Override
    public String getEventKey() {
        return "bestelling.registered";
    }

    public UUID getBestelling() {
        return bestelling;
    }

    public UUID getGebruiker() {
        return gebruiker;
    }
}
