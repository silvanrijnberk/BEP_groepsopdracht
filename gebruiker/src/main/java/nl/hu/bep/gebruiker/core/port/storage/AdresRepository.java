package nl.hu.bep.gebruiker.core.port.storage;

import nl.hu.bep.gebruiker.core.domain.Adres;
import nl.hu.bep.gebruiker.core.domain.Gebruiker;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface AdresRepository extends MongoRepository<Adres, UUID> {

    List<Adres> findByKeywordsEquals(String keyword, Sort sort);

    List<UUID> findByKeywordsEquals(String keyword);
}
