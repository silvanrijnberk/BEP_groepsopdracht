package nl.hu.bep.gerecht.core.application.command;

import java.util.UUID;

public class EditGerecht {
    private final UUID id;
    private final String beschrijving;
    private final String ingredienten;

    public EditGerecht(UUID id, String beschrijving, String ingredienten) {
        this.id = id;
        this.beschrijving = beschrijving;
        this.ingredienten = ingredienten;
    }

    public UUID getId() {
        return id;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public String getIngredienten() {
        return ingredienten;
    }
}
