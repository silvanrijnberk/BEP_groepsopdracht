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
public class Adres {
    @Id
    private UUID id;
    @Indexed
    private String streetname;
    @Indexed
    private Integer number;
    @Indexed
    private String affix;
    @Indexed
    private String postalcode;
    @Indexed
    private String city;
    @Indexed
    private String province;
    @Indexed
    private Set<String> keywords;
    @Transient
    private List<GebruikerEvent> events = new ArrayList<>();

    public Adres(String streetname, Integer number, String postalcode, String city, String province) {
        this.id = UUID.randomUUID();
        this.streetname = streetname;
        this.number = number;
        this.postalcode = postalcode;
        this.city = city;
        this.province = province;
        this.keywords = new HashSet<>();
    }
    
    public void addKeyword(String keyword) {
        this.keywords.add(keyword);
        this.events.add(new GebruikerAddedKeyword(id, keyword));
    }

    public void removeKeyword(String keyword) {
        this.keywords.remove(keyword);
        this.events.add(new GebruikerRemoveKeyword(id, keyword));
    }
    
    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAffix() {
        return affix;
    }

    public void setAffix(String affix) {
        this.affix = affix;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<GebruikerEvent> getEvents() {
        return events;
    }

    public void setEvents(List<GebruikerEvent> events) {
        this.events = events;
    }
}
