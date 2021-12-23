package nl.hu.bep.gerecht.core.domain;

import nl.hu.bep.gerecht.core.domain.event.GerechtAddedKeyword;
import nl.hu.bep.gerecht.core.domain.event.GerechtEvent;
import nl.hu.bep.gerecht.core.domain.event.GerechtRemoveKeyword;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
public class Gerecht {
    @Id
    private UUID id;
    @Indexed(unique = true)
    private String naam;
    @Indexed
    private String beschrijving;
    @Indexed
    private String ingredienten;
    @Indexed
    private Float prijs;
    @Indexed
    private Set<String> keywords;
    @Indexed
    private Set<String> bestellingen;
    @Transient
    private List<GerechtEvent> events = new ArrayList<>();

    public Gerecht(String naam, String beschrijving, String ingredienten, Float prijs) {
        this.id = UUID.randomUUID();
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.ingredienten = ingredienten;
        this.prijs = prijs;
        this.keywords = new HashSet<>();
        this.bestellingen = new HashSet<>();
    }
    public void addKeyword(String keyword) {
        this.keywords.add(keyword);
        this.events.add(new GerechtAddedKeyword(id, keyword));
    }

    public void removeKeyword(String keyword) {
        this.keywords.remove(keyword);
        this.events.add(new GerechtRemoveKeyword(id, keyword));
    }

    public void edit(String beschrijving, String ingredienten){
        this.beschrijving = beschrijving;
        this.ingredienten = ingredienten;
    }

    public UUID getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getIngredienten() {
        return ingredienten;
    }

    public void setIngredienten(String ingredienten) {
        this.ingredienten = ingredienten;
    }

    public Float getPrijs() {
        return prijs;
    }

    public void setPrijs(Float prijs) {
        this.prijs = prijs;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public Set<String> getBestellingen() {
        return bestellingen;
    }

    public void setBestellingen(Set<String> bestellingen) {
        this.bestellingen = bestellingen;
    }

    public List<GerechtEvent> listEvents() {
        return events;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void clearEvents() {
        this.events.clear();
    }



}
