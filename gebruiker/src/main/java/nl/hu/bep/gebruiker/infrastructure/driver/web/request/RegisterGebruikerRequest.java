package nl.hu.bep.gebruiker.infrastructure.driver.web.request;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegisterGebruikerRequest {

    @NotBlank
    public String firstname;
    @NotBlank
    public String lastname;
    @NotBlank
    public String email;
    @NotBlank
    public String streetname;
    @NotNull
    public Integer number;
    public String affix;
    @NotBlank
    public String postalcode;
    @NotBlank
    public String city;
    @NotBlank
    public String province;
}
