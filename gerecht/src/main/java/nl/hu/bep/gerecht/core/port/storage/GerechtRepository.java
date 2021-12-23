package nl.hu.bep.gerecht.core.port.storage;

import nl.hu.bep.gerecht.core.domain.Gerecht;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface GerechtRepository extends MongoRepository<Gerecht, UUID> {
    List<Gerecht> findByKeywordsEquals(String keyword, Sort sort);

    List<Gerecht> findByKeywordsEquals(String keyword);
}
