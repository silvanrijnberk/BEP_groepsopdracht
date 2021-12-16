package nl.hu.bep.beoordeling.core.domain.event;

import java.util.UUID;

public class BeoordelingAddedKeyword extends BeoordelingEvent {
    private final UUID beoordeling;
    private final String keyword;

    public BeoordelingAddedKeyword(UUID beoordeling, String keyword) {
        this.beoordeling = beoordeling;
        this.keyword = keyword;
    }

    @Override
    public String getEventKey() {
        return "keywords.beoordeling.added";
    }

    public UUID getBeoordeling() {
        return beoordeling;
    }

    public String getKeyword() {
        return keyword;
    }
}
