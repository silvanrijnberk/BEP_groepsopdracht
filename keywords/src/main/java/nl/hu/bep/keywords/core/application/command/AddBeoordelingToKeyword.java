package nl.hu.bep.keywords.core.application.command;

import java.util.UUID;

public class AddBeoordelingToKeyword {
    private final UUID beoordeling;
    private final String keyword;

    public AddBeoordelingToKeyword(UUID beoordeling, String keyword) {
        this.beoordeling = beoordeling;
        this.keyword = keyword;
    }

    public UUID getBeoordeling() {
        return beoordeling;
    }

    public String getKeyword() {
        return keyword;
    }
}
