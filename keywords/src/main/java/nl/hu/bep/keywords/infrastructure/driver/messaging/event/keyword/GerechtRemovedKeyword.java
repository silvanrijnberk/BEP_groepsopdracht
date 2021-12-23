package nl.hu.bep.keywords.infrastructure.driver.messaging.event.keyword;

import com.fasterxml.jackson.annotation.JsonTypeName;
import nl.hu.bep.keywords.infrastructure.driver.messaging.event.KeywordsEvent;

import java.util.UUID;

@JsonTypeName(GerechtRemovedKeyword.KEY)
public class GerechtRemovedKeyword extends KeywordsEvent {
    public static final String KEY = "keywords.gerecht.removed";

    private final UUID gerecht;
    private final String keyword;

    public GerechtRemovedKeyword(UUID gerecht, String keyword) {
        this.gerecht = gerecht;
        this.keyword = keyword;
    }

    @Override
    public String getEventKey() {
        return KEY;
    }

    public UUID getGerecht() {
        return gerecht;
    }

    public String getKeyword() {
        return keyword;
    }
}
