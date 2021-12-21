package nl.hu.bep.beoordeling.core.domain.event;

import java.util.UUID;

public class BeoordelingRemoveKeyword extends BeoordelingEvent {
    private final UUID beoordeling;
    private final String keyword;

    public BeoordelingRemoveKeyword(UUID beoordeling, String keyword) {
        this.beoordeling = beoordeling;
        this.keyword = keyword;
    }

    @Override
    public String getEventKey() {
        return "keywords.beoordeling.removed";
    }

    public UUID getBeoordeling() {
        return beoordeling;
    }

    public String getKeyword() {
        return keyword;
    }
}
