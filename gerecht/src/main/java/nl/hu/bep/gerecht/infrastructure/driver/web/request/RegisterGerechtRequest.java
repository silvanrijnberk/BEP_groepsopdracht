package nl.hu.bep.gerecht.infrastructure.driver.web.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

public class RegisterGerechtRequest {
    @NotBlank
    public String naam;
    @NotBlank
    public String beschrijving;
    @NotBlank
    public String ingredienten;
    @NotNull
    public float prijs;

}
