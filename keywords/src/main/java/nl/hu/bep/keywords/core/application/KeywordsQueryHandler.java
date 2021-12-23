package nl.hu.bep.keywords.core.application;

import nl.hu.bep.keywords.core.application.query.ListMatchesForKeyword;
import nl.hu.bep.keywords.core.domain.Keyword;
import nl.hu.bep.keywords.core.domain.exception.KeywordNotFound;
import nl.hu.bep.keywords.core.port.storage.KeywordRepository;
import org.springframework.stereotype.Service;

@Service
public class KeywordsQueryHandler {
    public KeywordsQueryHandler(KeywordRepository repository) {
        this.repository = repository;
    }

    private final KeywordRepository repository;

    public Keyword handle(ListMatchesForKeyword query) {
        return this.repository.findByKeyword(query.getKeyword())
                .orElseThrow(() -> new KeywordNotFound(query.getKeyword()));
    }
}
