package nl.hu.bep.gerecht.core.domain.event;

import java.util.UUID;

public class GerechtRemoveKeyword extends GerechtEvent {
    private final UUID gerecht;
    private final String keyword;

    public GerechtRemoveKeyword(UUID gerecht, String keyword) {
        this.gerecht = gerecht;
        this.keyword = keyword;
    }

    @Override
    public String getEventKey() {
        return "keywords.gerecht.removed";
    }

    public UUID getGerecht() {
        return gerecht;
    }

    public String getKeyword() {
        return keyword;
    }
}
