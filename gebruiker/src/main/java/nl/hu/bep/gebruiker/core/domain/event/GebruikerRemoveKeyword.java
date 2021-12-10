package nl.hu.bep.gebruiker.core.domain.event;

import java.util.UUID;

public class GebruikerRemoveKeyword extends GebruikerEvent {
    private final UUID gebruiker;
    private final String keyword;

    public GebruikerRemoveKeyword(UUID gebruiker, String keyword) {
        this.gebruiker = gebruiker;
        this.keyword = keyword;
    }

    @Override
    public String getEventKey() {
        return "keywords.gebruiker.removed";
    }

    public UUID getGebruiker() {
        return gebruiker;
    }

    public String getKeyword() {
        return keyword;
    }
}
