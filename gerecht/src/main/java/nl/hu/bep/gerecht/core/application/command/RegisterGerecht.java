package nl.hu.bep.gerecht.core.application.command;

import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;
import java.util.UUID;

public class RegisterGerecht {
    private final String naam;
    private final String beschrijving;
    private final String ingredienten;
    private final float prijs;

    public RegisterGerecht(String naam, String beschrijving, String ingredienten, float prijs) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.ingredienten = ingredienten;
        this.prijs = prijs;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public String getIngredienten() {
        return ingredienten;
    }

    public float getPrijs() {
        return prijs;
    }
}
