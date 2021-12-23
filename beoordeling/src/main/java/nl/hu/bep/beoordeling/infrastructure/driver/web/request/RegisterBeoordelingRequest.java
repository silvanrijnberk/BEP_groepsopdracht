package nl.hu.bep.beoordeling.infrastructure.driver.web.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

public class RegisterBeoordelingRequest {
    @NotNull
    public UUID gebruiker;
    @NotBlank
    public String beschrijving;
    @NotNull
    public float sterren;



}
