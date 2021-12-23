package nl.hu.bep.keywords.core.port.storage;

import nl.hu.bep.keywords.core.domain.Keyword;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface KeywordRepository extends MongoRepository<Keyword, String> {
    Optional<Keyword> findByKeyword(String keyword);

    List<Keyword> findAllByCandidatesEquals(String candidateId);

    List<Keyword> findAllByJobsEquals(String jobId);
}
