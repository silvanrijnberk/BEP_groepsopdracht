package nl.hu.bep.beoordeling.core.application.command;

import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;
import java.util.UUID;

public class RegisterBeoordeling {
    private final UUID id;
    private UUID gebruiker;
    private float sterren;
    private String beschrijving;
    private Date date;


    public RegisterBeoordeling(UUID id, UUID gebruiker, float sterren, Date date, String beschrijving) {
        this.id = id;
        this.gebruiker = gebruiker;
        this.sterren = sterren;
        this.beschrijving = beschrijving;
        this.date = date;
        this.sterren = sterren;
    }

    public UUID getId() {
        return id;
    }

    public UUID getGebruiker() {
        return gebruiker;
    }

    public float getSterren() {
        return sterren;
    }

    public Date getDate() {
        return date;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

}
