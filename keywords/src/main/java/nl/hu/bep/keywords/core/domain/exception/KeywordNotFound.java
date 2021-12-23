package nl.hu.bep.keywords.core.domain.exception;

public class KeywordNotFound extends RuntimeException {
    public KeywordNotFound(String message) {
        super(message);
    }
}
