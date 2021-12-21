package nl.hu.bep.beoordeling.core.domain.exception;

public class BeoordelingNotFound extends RuntimeException{
    public BeoordelingNotFound(String message) {
        super(message);
    }
}
