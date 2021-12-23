package nl.hu.bep.keywords.core.application.command;

import java.util.UUID;

public class AddGerechtToKeyword {
    private final UUID gerecht;
    private final String keyword;

    public AddGerechtToKeyword(UUID gerecht, String keyword) {
        this.gerecht = gerecht;
        this.keyword = keyword;
    }

    public UUID getGerecht() {
        return gerecht;
    }

    public String getKeyword() {
        return keyword;
    }
}
