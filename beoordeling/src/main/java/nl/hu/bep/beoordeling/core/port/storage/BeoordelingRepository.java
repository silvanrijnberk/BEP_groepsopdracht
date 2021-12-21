package nl.hu.bep.beoordeling.core.port.storage;

import nl.hu.bep.beoordeling.core.domain.Beoordeling;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface BeoordelingRepository extends MongoRepository<Beoordeling, UUID> {
    List<Beoordeling> findByKeywordsEquals(String keyword, Sort sort);

    List<Beoordeling> findByKeywordsEquals(String keyword);
}
