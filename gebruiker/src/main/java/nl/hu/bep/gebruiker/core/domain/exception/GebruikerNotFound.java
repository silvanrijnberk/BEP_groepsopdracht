package nl.hu.bep.gebruiker.core.domain.exception;

public class GebruikerNotFound extends RuntimeException {

    public GebruikerNotFound(String message) {
        super(message);
    }
}
