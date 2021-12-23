package nl.hu.bep.bestelling.core.domain.event;

import java.util.UUID;

public class BestellingRemoveKeyword extends BestellingEvent {
    private final UUID gebruiker;
    private final String keyword;

    public BestellingRemoveKeyword(UUID gebruiker, String keyword) {
        this.gebruiker = gebruiker;
        this.keyword = keyword;
    }

    @Override
    public String getEventKey() {
        return "keywords.bestelling.removed";
    }

    public UUID getBestelling() {
        return gebruiker;
    }

    public String getKeyword() {
        return keyword;
    }
}
