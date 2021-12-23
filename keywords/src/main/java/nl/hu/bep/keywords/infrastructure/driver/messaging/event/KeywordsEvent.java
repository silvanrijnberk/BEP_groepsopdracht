package nl.hu.bep.keywords.infrastructure.driver.messaging.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nl.hu.bep.keywords.infrastructure.driver.messaging.event.keyword.*;

import java.time.Instant;
import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "eventKey")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BeoordelingAddedKeyword.class),
        @JsonSubTypes.Type(value = BestellingAddedKeyword.class),
        @JsonSubTypes.Type(value = GebruikerAddedKeyword.class),
        @JsonSubTypes.Type(value = GerechtAddedKeyword.class),
        @JsonSubTypes.Type(value = BeoordelingRemovedKeyword.class),
        @JsonSubTypes.Type(value = BestellingRemovedKeyword.class),
        @JsonSubTypes.Type(value = GebruikerRemovedKeyword.class),
        @JsonSubTypes.Type(value = GerechtRemovedKeyword.class),


})
public abstract class KeywordsEvent {
    private final UUID eventId = UUID.randomUUID();
    private final Instant eventDate = Instant.now();

    public UUID getEventId() {
        return eventId;
    }

    public Instant getEventDate() {
        return eventDate;
    }

    public abstract String getEventKey();
}
