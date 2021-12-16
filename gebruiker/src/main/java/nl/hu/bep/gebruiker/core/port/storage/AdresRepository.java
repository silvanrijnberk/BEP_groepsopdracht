package nl.hu.bep.gebruiker.core.port.storage;

import nl.hu.bep.gebruiker.core.domain.Adres;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdresRepository extends MongoRepository<Adres, UUID> {
    List<Adres> findByKeywordsEquals(String keyword, Sort sort);


    List<UUID> findByKeywordsEquals(String keyword);
}
