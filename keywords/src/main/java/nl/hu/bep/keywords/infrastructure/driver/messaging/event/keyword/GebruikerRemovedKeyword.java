package nl.hu.bep.keywords.infrastructure.driver.messaging.event.keyword;

import com.fasterxml.jackson.annotation.JsonTypeName;
import nl.hu.bep.keywords.infrastructure.driver.messaging.event.KeywordsEvent;

import java.util.UUID;

@JsonTypeName(GebruikerRemovedKeyword.KEY)
public class GebruikerRemovedKeyword extends KeywordsEvent {
    public static final String KEY = "keywords.gebruiker.removed";

    private final UUID gebruiker;
    private final String keyword;

    public GebruikerRemovedKeyword(UUID gebruiker, String keyword) {
        this.gebruiker = gebruiker;
        this.keyword = keyword;
    }

    @Override
    public String getEventKey() {
        return KEY;
    }

    public UUID getGebruiker() {
        return gebruiker;
    }

    public String getKeyword() {
        return keyword;
    }
}
