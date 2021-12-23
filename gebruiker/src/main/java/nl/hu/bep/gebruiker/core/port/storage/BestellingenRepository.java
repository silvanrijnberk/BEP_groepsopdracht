package nl.hu.bep.gebruiker.core.port.storage;

import java.util.List;
import java.util.UUID;

public interface BestellingenRepository {
    List<UUID> findBestellingenByKeyword(String keyword);
}
