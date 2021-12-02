package nl.hu.bep.gebruiker.core.application.command;

import nl.hu.bep.gebruiker.core.domain.Adres;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.UUID;

public class RegisterGebruiker {
    private final UUID id;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final char[] password;

    public RegisterGebruiker(String firstname, String lastname, String email, char[] password) {
        this.id = UUID.randomUUID();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
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

    public char[] getPassword() {
        return password;
    }




}
