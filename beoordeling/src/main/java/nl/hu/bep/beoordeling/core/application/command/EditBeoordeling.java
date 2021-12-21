package nl.hu.bep.beoordeling.core.application.command;

import java.util.UUID;

public class EditBeoordeling {
    private final UUID id;
    private final String beschrijving;
    private final int cijfer;

    public EditBeoordeling(UUID id, String beschrijving, int cijfer) {
        this.id = id;
        this.beschrijving = beschrijving;
        this.cijfer = cijfer;
    }

    public UUID getId() {
        return id;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public int getCijfer() {
        return cijfer;
    }
}
