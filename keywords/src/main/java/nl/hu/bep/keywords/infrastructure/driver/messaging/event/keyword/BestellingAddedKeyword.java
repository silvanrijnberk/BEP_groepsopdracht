package nl.hu.bep.keywords.infrastructure.driver.messaging.event.keyword;

import com.fasterxml.jackson.annotation.JsonTypeName;
import nl.hu.bep.keywords.infrastructure.driver.messaging.event.KeywordsEvent;

import java.util.UUID;

@JsonTypeName(BestellingAddedKeyword.KEY)
public class BestellingAddedKeyword extends KeywordsEvent {
    public static final String KEY = "keywords.bestelling.added";

    private final UUID bestelling;
    private final String keyword;

    public BestellingAddedKeyword(UUID bestelling, String keyword) {
        this.bestelling = bestelling;
        this.keyword = keyword;
    }

    @Override
    public String getEventKey() {
        return KEY;
    }

    public UUID getBestelling() {
        return bestelling;
    }

    public String getKeyword() {
        return keyword;
    }
}
