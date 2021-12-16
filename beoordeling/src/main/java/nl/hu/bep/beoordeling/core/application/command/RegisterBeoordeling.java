package nl.hu.bep.beoordeling.core.application.command;

import java.util.UUID;

public class RegisterBeoordeling {
    private final UUID id;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String omschrijving;
    private final int cijfer;

    public RegisterBeoordeling(UUID id, String firstname, String lastname, String email, String omschrijving, int cijfer) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.omschrijving = omschrijving;
        this.cijfer = cijfer;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public int getCijfer() {
        return cijfer;
    }
}
