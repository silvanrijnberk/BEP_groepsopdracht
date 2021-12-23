package nl.hu.bep.gebruiker.core.application.command;

import java.util.UUID;

public class MatchGebruikers {
    private final UUID gebruikers;
    private final String keyword;

    public MatchGebruikers(UUID gebruikers, String keyword) {
        this.gebruikers = gebruikers;
        this.keyword = keyword;
    }

    public UUID getGebruikers() {
        return gebruikers;
    }

    public String getKeyword() {
        return keyword;
    }
}
