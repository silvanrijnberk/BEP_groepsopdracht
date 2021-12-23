package nl.hu.bep.gerecht.core.domain.exception;

public class GerechtNotFound extends RuntimeException{
    public GerechtNotFound(String message) {
        super(message);
    }
}
