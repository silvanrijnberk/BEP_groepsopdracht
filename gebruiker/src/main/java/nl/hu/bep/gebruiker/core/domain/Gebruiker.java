package nl.hu.bep.gebruiker.core.domain;

import nl.hu.bep.gebruiker.core.domain.event.GebruikerAddedKeyword;
import nl.hu.bep.gebruiker.core.domain.event.GebruikerEvent;
import nl.hu.bep.gebruiker.core.domain.event.GebruikerRemoveKeyword;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;
@Document
public class Gebruiker {
    @Id
    private UUID id;
    @Indexed
    private String firstname;
    private String lastname;
    @Indexed(unique = true)
    private String email;
    @Indexed
    private UUID adres;

    @Indexed
    private Set<String> keywords;

    @Indexed
    private Set<String> bestelling;
    @Transient
    private List<GebruikerEvent> events = new ArrayList<>();

    public Gebruiker(String firstname, String lastname, String email, UUID adres) {
        this.id = UUID.randomUUID();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.adres = adres;
        this.keywords = new HashSet<>();
        this.bestelling = new HashSet<>();
    }

    public void addKeyword(String keyword) {
        this.keywords.add(keyword);
        this.events.add(new GebruikerAddedKeyword(id, keyword));
    }

    public void addBestelling(String bestelling) {
        this.keywords.add(bestelling);
        this.events.add(new GebruikerAddedKeyword(id, bestelling));
    }

    public void removeBestelling(String bestelling) {
        this.keywords.remove(bestelling);
        this.events.add(new GebruikerRemoveKeyword(id, bestelling));
    }

    public void removeKeyword(String keyword) {
        this.keywords.remove(keyword);
        this.events.add(new GebruikerRemoveKeyword(id, keyword));
    }

    public void rename(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return firstname + " " + lastname;
    }

    public String getEmail() {
        return email;
    }

    public UUID getAdres() {
        return adres;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdres(UUID adres) {
        this.adres = adres;
    }

    public void clearEvents() {
        this.events.clear();
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public List<GebruikerEvent> listEvents() {
        return events;
    }

}
