package nl.hu.bep.beoordeling.core.domain;

import nl.hu.bep.beoordeling.core.domain.event.BeoordelingAddedKeyword;
import nl.hu.bep.beoordeling.core.domain.event.BeoordelingEvent;
import nl.hu.bep.beoordeling.core.domain.event.BeoordelingRemoveKeyword;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Document
public class Beoordeling {
    @Id
    private UUID id;
    @Indexed
    private String firstname;
    private String lastname;
    @Indexed(unique = true)
    private String email;
    @Indexed
    private String beschrijving;
    @Indexed
    private int cijfer;
    @Indexed
    private Set<String> keywords;
    @Transient
    private List<BeoordelingEvent> events = new ArrayList<>();

    public Beoordeling(UUID id, String firstname, String lastname, String email, String beschrijving, int cijfer) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.beschrijving = beschrijving;
        this.cijfer = cijfer;
    }

    public void addKeyword(String keyword) {
        this.keywords.add(keyword);
        this.events.add(new BeoordelingAddedKeyword(id, keyword));
    }

    public void removeKeyword(String keyword) {
        this.keywords.remove(keyword);
        this.events.add(new BeoordelingRemoveKeyword(id, keyword));
    }

    public void edit(String beschrijving, int cijfer){
        this.beschrijving = beschrijving;
        this.cijfer = cijfer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public int getCijfer() {
        return cijfer;
    }

    public void setCijfer(int cijfer) {
        this.cijfer = cijfer;
    }

    public List<BeoordelingEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }



}
