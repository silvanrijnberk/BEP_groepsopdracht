package nl.hu.bep.bestelling.core.domain.event;

import java.util.UUID;

public class BestellingRemoveKeyword extends BestellingEvent {
    private final UUID bestelling;
    private final String keyword;

    public BestellingRemoveKeyword(UUID bestelling, String keyword) {
        this.bestelling = bestelling;
        this.keyword = keyword;
    }

    @Override
    public String getEventKey() {
        return "keywords.bestelling.removed";
    }

    public UUID getBestelling() {
        return bestelling;
    }

    public String getKeyword() {
        return keyword;
    }
}
