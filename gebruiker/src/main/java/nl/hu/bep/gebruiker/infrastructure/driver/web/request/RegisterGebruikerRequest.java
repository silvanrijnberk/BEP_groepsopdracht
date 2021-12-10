package nl.hu.bep.gebruiker.infrastructure.driver.web.request;
import javax.validation.constraints.NotBlank;

public class RegisterGebruikerRequest {

    @NotBlank
    public String firstname;

    @NotBlank
    public String lastname;

    @NotBlank
    public String email;

    @NotBlank
    public char[] password;
}
