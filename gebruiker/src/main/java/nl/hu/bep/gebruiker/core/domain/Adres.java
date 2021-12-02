package nl.hu.bep.gebruiker.core.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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


    public Adres(String streetname, Integer number, String postalcode, String city, String province) {
        this.id = UUID.randomUUID();
        this.streetname = streetname;
        this.number = number;
        this.postalcode = postalcode;
        this.city = city;
        this.province = province;
    }
}
