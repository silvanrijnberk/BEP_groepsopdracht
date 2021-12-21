package nl.hu.bep.beoordeling.infrastructure.driver.web.request;


import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.UUID;

public class RegisterBeoordelingRequest {
    @NotBlank
    public UUID id;
    @NotBlank
    public UUID gebruiker;
    @NotBlank
    public String email;
    @NotBlank
    public String beschrijving;
    @NotBlank
    public float sterren;
    @NotBlank
    public Date date;


}
