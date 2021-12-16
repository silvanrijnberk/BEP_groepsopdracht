package nl.hu.bep.gebruiker.core.port.storage;

import nl.hu.bep.gebruiker.core.domain.Gebruiker;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GebruikerRepository extends MongoRepository<Gebruiker, UUID> {
    List<Gebruiker> findByKeywordsEquals(String keyword, Sort sort);

    List<Gebruiker> findByKeywordsEquals(String keyword);
}
