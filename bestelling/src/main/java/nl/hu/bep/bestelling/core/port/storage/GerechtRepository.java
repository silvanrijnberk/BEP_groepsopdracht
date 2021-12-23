package nl.hu.bep.bestelling.core.port.storage;

import java.util.List;
import java.util.UUID;

public interface GerechtRepository{
    List<UUID> findByKeywordsEquals(String keyword);
}
