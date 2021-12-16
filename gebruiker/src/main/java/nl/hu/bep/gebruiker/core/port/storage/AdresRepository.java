package nl.hu.bep.gebruiker.core.port.storage;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdresRepository {
//    List<Adres> findByKeywordsEquals(String keyword, Sort sort);

    List<UUID> findByKeywordsEquals(String keyword);
}
