package nl.hu.bep.bestelling.core.application.command;



import java.util.Date;
import java.util.Hashtable;
import java.util.UUID;

public class RegisterBestelling {
    private final java.util.UUID id;
    private final java.util.UUID gebruiker;
    private final String opmerkingen;
    private final Date date;
    private final String status;
    private final Hashtable<UUID, Integer> gerechten;

    public RegisterBestelling(UUID gebruiker, String status, String opmerkingen, Date date, Hashtable<UUID, Integer> gerechten) {
        this.id = java.util.UUID.randomUUID();
        this.gebruiker = gebruiker;
        this.opmerkingen = opmerkingen;
        this.status = status;
        this.date = date;
        this.gerechten = gerechten;
    }

    public java.util.UUID getId() {
        return id;
    }

    public java.util.UUID getGebruiker() {
        return gebruiker;
    }

    public String getStatus() {
        return status;
    }

    public String getOpmerkingen() {
        return opmerkingen;
    }

    public Date getDate() {
        return date;
    }

    public Hashtable<UUID, Integer> getGerechten() {
        return gerechten;
    }
}
