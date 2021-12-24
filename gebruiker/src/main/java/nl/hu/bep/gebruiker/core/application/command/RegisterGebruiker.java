package nl.hu.bep.gebruiker.core.application.command;


public class RegisterGebruiker {
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String streetname;
    private final Integer number;
    private final String affix;
    private final String postalcode;
    private final String city;
    private final String province;

    public RegisterGebruiker(String firstname, String lastname, String email, String streetname, Integer number, String affix, String postalcode, String city, String province) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.streetname = streetname;
        this.number = number;
        this.affix = affix;
        this.postalcode = postalcode;
        this.city = city;
        this.province = province;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getStreetname() {
        return streetname;
    }

    public Integer getNumber() {
        return number;
    }

    public String getAffix() {
        return affix;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }
}
