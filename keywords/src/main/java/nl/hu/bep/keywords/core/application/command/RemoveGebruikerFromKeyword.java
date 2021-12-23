package nl.hu.bep.keywords.core.application.command;

import java.util.UUID;

public class RemoveGebruikerFromKeyword {
    private final UUID gebruiker;
    private final String keyword;

    public RemoveGebruikerFromKeyword(UUID gebruiker, String keyword) {
        this.gebruiker = gebruiker;
        this.keyword = keyword;
    }

    public UUID getGebruiker() {
        return gebruiker;
    }

    public String getKeyword() {
        return keyword;
    }
}
