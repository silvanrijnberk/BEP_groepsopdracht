package nl.hu.bep.bestelling.core.domain;

import nl.hu.bep.bestelling.core.domain.event.BestellingAddedKeyword;
import nl.hu.bep.bestelling.core.domain.event.BestellingEvent;
import nl.hu.bep.bestelling.core.domain.event.BestellingRemoveKeyword;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;
@Document
public class Bestelling {
    @Id
    private UUID id;
    @Indexed
    private java.util.UUID gebruiker;
    private String opmerkingen;
    private String status;
    @Indexed(unique = true)
    private Date date;
    @Indexed
    private Hashtable<UUID, Integer> gerechten;
    @Indexed
    private Set<String> keywords;
    @Transient
    private List<BestellingEvent> events = new ArrayList<>();



    public Bestelling(java.util.UUID gebruiker, String status, String opmerkingen, Date date, Hashtable<UUID, Integer> gerechten) {
        this.id = java.util.UUID.randomUUID();
        this.gebruiker = gebruiker;
        this.status = status;
        this.date = date;
        this.opmerkingen = opmerkingen;
        this.gerechten = gerechten;
        this.keywords = new HashSet<>();
    }

    public void addKeyword(String keyword) {
        this.keywords.add(keyword);
        this.events.add(new BestellingAddedKeyword(id, keyword));
    }

    public void removeKeyword(String keyword) {
        this.keywords.remove(keyword);
        this.events.add(new BestellingRemoveKeyword(id, keyword));
    }


    public java.util.UUID getId() {
        return id;
    }

    public java.util.UUID getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(java.util.UUID gebruiker) {
        this.gebruiker = gebruiker;
    }

    public String getOpmerkingen() {
        return opmerkingen;
    }

    public void setOpmerkingen(String opmerkingen) {
        this.opmerkingen = opmerkingen;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Hashtable<UUID, Integer> getGerechten() {
        return gerechten;
    }

    public void setGerechten(Hashtable<UUID, Integer> gerechten) {
        this.gerechten = gerechten;
    }

    public void clearEvents() {
        this.events.clear();
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public List<BestellingEvent> listEvents() {
        return events;
    }

    public void addGerecht(UUID gerecht) {
        if(gerechten.containsKey(gerecht)){
            Integer temp = gerechten.get(gerecht);
            gerechten.replace(gerecht, temp, temp+1);
        }else {
            gerechten.put(gerecht, 1);
        }

    }
}
