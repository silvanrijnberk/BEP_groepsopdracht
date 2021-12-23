package nl.hu.bep.bestelling.core.port.storage;

import nl.hu.bep.bestelling.core.domain.Bestelling;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BestellingRepository extends MongoRepository<Bestelling, UUID> {
    List<Bestelling> findByKeywordsEquals(String keyword, Sort sort);

    List<Bestelling> findByKeywordsEquals(String keyword);
}
