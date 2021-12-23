package nl.hu.bep.keywords.core.application.command;

import java.util.UUID;

public class AddBestellingToKeyword {
    private final UUID bestelling;
    private final String keyword;

    public AddBestellingToKeyword(UUID bestelling, String keyword) {
        this.bestelling = bestelling;
        this.keyword = keyword;
    }

    public UUID getBestelling() {
        return bestelling;
    }

    public String getKeyword() {
        return keyword;
    }
}
