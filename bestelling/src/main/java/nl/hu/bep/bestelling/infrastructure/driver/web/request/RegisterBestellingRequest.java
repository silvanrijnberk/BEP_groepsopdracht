package nl.hu.bep.bestelling.infrastructure.driver.web.request;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.UUID;

public class RegisterBestellingRequest {

    @NotNull
    public UUID gebruiker;

    @NotBlank
    public String opmerkingen;

    @NotNull
    public ArrayList<UUID> gerechten;
}
