package nl.hu.bep.gebruiker.core.application.command;

import java.util.UUID;

public class UnmatchGebruikers {
    private final UUID bestelling;
    private final String keyword;

    public UnmatchGebruikers(UUID bestelling, String keyword) {
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
