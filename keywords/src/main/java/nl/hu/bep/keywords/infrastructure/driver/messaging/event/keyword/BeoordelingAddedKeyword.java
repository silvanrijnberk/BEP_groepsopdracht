package nl.hu.bep.keywords.infrastructure.driver.messaging.event.keyword;

import com.fasterxml.jackson.annotation.JsonTypeName;
import nl.hu.bep.keywords.infrastructure.driver.messaging.event.KeywordsEvent;

import java.util.UUID;

@JsonTypeName(BeoordelingAddedKeyword.KEY)
public class BeoordelingAddedKeyword extends KeywordsEvent {
    public static final String KEY = "keywords.beoordeling.added";

    private final UUID beoordeling;
    private final String keyword;

    public BeoordelingAddedKeyword(UUID beoordeling, String keyword) {
        this.beoordeling = beoordeling;
        this.keyword = keyword;
    }

    @Override
    public String getEventKey() {
        return KEY;
    }

    public UUID getBeoordeling() {
        return beoordeling;
    }

    public String getKeyword() {
        return keyword;
    }
}
