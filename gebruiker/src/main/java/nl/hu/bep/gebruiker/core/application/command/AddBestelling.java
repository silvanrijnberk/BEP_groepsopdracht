package nl.hu.bep.gebruiker.core.application.command;

import java.util.UUID;

public class AddBestelling {
    private final UUID bestelling;
    private final String keyword;
    private final UUID gebruiker;
    public AddBestelling(UUID bestelling, String keyword, UUID gebruiker) {
        this.bestelling = bestelling;
        this.keyword = keyword;
        this.gebruiker = gebruiker;
    }

    public UUID getBestelling() {
        return bestelling;
    }

    public UUID getGebruiker() {
        return gebruiker;
    }

    public String getKeyword() {
        return keyword;
    }
}
