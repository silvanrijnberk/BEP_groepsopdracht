package nl.hu.bep.gebruiker.infrastructure.driver.event;

import java.time.Instant;
import java.util.UUID;

public class BestellingKeywordEvent {
    public UUID eventId;
    public String eventKey;
    public Instant eventDate;
    public UUID bestelling;
    public String keyword;
}
