package nl.hu.bep.bestelling.core.domain.exception;

public class BestellingNotFound extends RuntimeException {

    public BestellingNotFound(String message) {
        super(message);
    }
}
