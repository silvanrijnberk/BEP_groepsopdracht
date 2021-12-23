package nl.hu.bep.keywords.infrastructure.driver.messaging.event.keyword;

import com.fasterxml.jackson.annotation.JsonTypeName;
import nl.hu.bep.keywords.infrastructure.driver.messaging.event.KeywordsEvent;

import java.util.UUID;

@JsonTypeName(BestellingRemovedKeyword.KEY)
public class BestellingRemovedKeyword extends KeywordsEvent {
    public static final String KEY = "keywords.bestelling.removed";

    private final UUID bestelling;
    private final String keyword;

    public BestellingRemovedKeyword(UUID bestelling, String keyword) {
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
