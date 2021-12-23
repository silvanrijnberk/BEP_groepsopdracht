package nl.hu.bep.keywords.infrastructure.driver.messaging.event.keyword;

import com.fasterxml.jackson.annotation.JsonTypeName;
import nl.hu.bep.keywords.infrastructure.driver.messaging.event.KeywordsEvent;

import java.util.UUID;

@JsonTypeName(GerechtAddedKeyword.KEY)
public class GerechtAddedKeyword extends KeywordsEvent {
    public static final String KEY = "keywords.gerecht.added";

    private final UUID gerecht;
    private final String keyword;

    public GerechtAddedKeyword(UUID gerecht, String keyword) {
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
