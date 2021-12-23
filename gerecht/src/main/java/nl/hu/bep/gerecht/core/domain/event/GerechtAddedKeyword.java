package nl.hu.bep.gerecht.core.domain.event;

import java.util.UUID;

public class GerechtAddedKeyword extends GerechtEvent {
    private final UUID gerecht;
    private final String keyword;

    public GerechtAddedKeyword(UUID gerecht, String keyword) {
        this.gerecht = gerecht;
        this.keyword = keyword;
    }

    @Override
    public String getEventKey() {
        return "keywords.gerecht.added";
    }

    public UUID getGerecht() {
        return gerecht;
    }

    public String getKeyword() {
        return keyword;
    }
}
