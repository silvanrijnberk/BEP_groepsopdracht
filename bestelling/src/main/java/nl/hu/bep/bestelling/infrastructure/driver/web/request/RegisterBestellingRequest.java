package nl.hu.bep.bestelling.infrastructure.driver.web.request;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.UUID;

public class RegisterBestellingRequest {

    @NotBlank
    public UUID gebruiker;

    @NotBlank
    public String opmerkingen;

    @NotBlank
    public ArrayList<UUID> gerechten;
}
