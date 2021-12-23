package nl.hu.bep.keywords.core.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Document
public class Keyword {
    @Id
    private String keyword;

    @Indexed
    private Set<String> gebruiker;

    @Indexed
    private Set<String> bestelling;

    @Indexed
    private Set<String> gerecht;

    @Indexed
    private Set<String> beoordeling;

    public Keyword(String keyword) {
        this.keyword = keyword;
        this.gebruiker = new HashSet<>();
        this.bestelling = new HashSet<>();
        this.gerecht = new HashSet<>();
        this.beoordeling = new HashSet<>();
    }

    public void addGebruiker(UUID gebruikerId) {
        this.gebruiker.add(gebruikerId.toString());
    }
    public void removeGebruiker(UUID gebruikerId) {
        this.gebruiker.remove(gebruikerId.toString());
    }

    public void addGerecht(UUID gerechtId) {
        this.gerecht.add(gerechtId.toString());
    }
    public void removeGerecht(UUID gerechtId) {
        this.gerecht.remove(gerechtId.toString());
    }

    public void addBeoordeling(UUID beoordelingId) {
        this.beoordeling.add(beoordelingId.toString());
    }
    public void removeBeoordeling(UUID beoordelingId) {
        this.beoordeling.remove(beoordelingId.toString());
    }

    public void addBestelling(UUID bestellingId) {
        this.bestelling.add(bestellingId.toString());
    }

    public void removeBestelling(UUID bestellingId) {
        this.bestelling.remove(bestellingId.toString());
    }

    public String getKeyword() {
        return keyword;
    }

    public Set<String> getGebruiker() {
        return gebruiker;
    }

    public Set<String> getBestelling() {
        return bestelling;
    }
}
