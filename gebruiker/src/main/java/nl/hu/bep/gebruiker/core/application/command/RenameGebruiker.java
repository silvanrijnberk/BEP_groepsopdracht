package nl.hu.bep.gebruiker.core.application.command;

import java.util.UUID;

public class RenameGebruiker {
    private final UUID id;
    private final String firstname;
    private final String lastname;

    public RenameGebruiker(UUID id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
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
}
