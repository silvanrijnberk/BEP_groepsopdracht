package nl.hu.bep.beoordeling.infrastructure.driver.web.request;


import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class RegisterBeoordelingRequest {
    @NotBlank
    public UUID id;
    @NotBlank
    public String firstname;
    public String lastname;
    @NotBlank
    public String email;
    @NotBlank
    public String beschrijving;
    @NotBlank
    public int cijfer;

}
