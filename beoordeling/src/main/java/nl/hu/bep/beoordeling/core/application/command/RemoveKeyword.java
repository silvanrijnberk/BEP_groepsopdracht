package nl.hu.bep.beoordeling.core.application.command;

import java.util.UUID;

public class RemoveKeyword {
    private final UUID id;
    private final String keyword;

    public RemoveKeyword(UUID id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }

    public UUID getId() {
        return id;
    }

    public String getKeyword() {
        return keyword;
    }
}
