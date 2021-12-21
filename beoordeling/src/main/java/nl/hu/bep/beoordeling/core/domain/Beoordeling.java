package nl.hu.bep.beoordeling.core.domain;

import nl.hu.bep.beoordeling.core.domain.event.BeoordelingAddedKeyword;
import nl.hu.bep.beoordeling.core.domain.event.BeoordelingEvent;
import nl.hu.bep.beoordeling.core.domain.event.BeoordelingRemoveKeyword;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
public class Beoordeling {
    @Id
    private UUID id;
    @Indexed(unique = true)
    private UUID gebruiker;
    @Indexed
    private float sterren;
    @Indexed
    private String beschrijving;
    @Indexed
    private Date date;
    @Indexed
    private Set<String> keywords;
    @Transient
    private List<BeoordelingEvent> events = new ArrayList<>();

    public Beoordeling(UUID gebruiker, String beschrijving, float sterren, Date date) {
        this.id = UUID.randomUUID();
        this.gebruiker = gebruiker;
        this.beschrijving = beschrijving;
        this.date = date;
        this.sterren = sterren;
    }
    public void addKeyword(String keyword) {
        this.keywords.add(keyword);
        this.events.add(new BeoordelingAddedKeyword(id, keyword));
    }

    public void removeKeyword(String keyword) {
        this.keywords.remove(keyword);
        this.events.add(new BeoordelingRemoveKeyword(id, keyword));
    }

    public void edit(String beschrijving, int sterren){
        this.beschrijving = beschrijving;
        this.sterren = sterren;
    }

    public UUID getId() {
        return id;
    }

    public UUID getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(UUID gebruiker) {
        this.gebruiker = gebruiker;
    }

    public float getSterren() {
        return sterren;
    }

    public void setSterren(float sterren) {
        this.sterren = sterren;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<BeoordelingEvent> listEvents() {
        return events;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void clearEvents() {
        this.events.clear();
    }



}
