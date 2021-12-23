package nl.hu.bep.keywords.core.application;

import nl.hu.bep.keywords.core.application.command.*;
import nl.hu.bep.keywords.core.domain.Keyword;
import nl.hu.bep.keywords.core.port.storage.KeywordRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KeywordsCommandHandler {
    public KeywordsCommandHandler(KeywordRepository repository) {
        this.repository = repository;
    }

    private final KeywordRepository repository;

    public void handle(AddGebruikerToKeyword command) {
        Keyword keywordOverview = this.createIfNotExists(command.getKeyword());
        keywordOverview.addGebruiker(command.getGebruiker());

        this.repository.save(keywordOverview);
    }

    public void handle(RemoveGebruikerFromKeyword command) {
        Optional<Keyword> optionalMatch = this.repository.findByKeyword(command.getKeyword());

        if (optionalMatch.isEmpty()) {
            return;
        }

        Keyword keywordOverview = optionalMatch.get();
        keywordOverview.removeGebruiker(command.getGebruiker());

        this.repository.save(keywordOverview);
    }

    public void handle(AddGerechtToKeyword command) {
        Keyword keywordOverview = this.createIfNotExists(command.getKeyword());
        keywordOverview.addGerecht(command.getGerecht());

        this.repository.save(keywordOverview);
    }

    public void handle(RemoveGerechtFromKeyword command) {
        Optional<Keyword> optionalMatch = this.repository.findByKeyword(command.getKeyword());

        if (optionalMatch.isEmpty()) {
            return;
        }

        Keyword keywordOverview = optionalMatch.get();
        keywordOverview.removeGerecht(command.getGerecht());

        this.repository.save(keywordOverview);
    }

    public void handle(AddBeoordelingToKeyword command) {
        Keyword keywordOverview = this.createIfNotExists(command.getKeyword());
        keywordOverview.addBeoordeling(command.getBeoordeling());

        this.repository.save(keywordOverview);
    }

    public void handle(RemoveBeoordelingFromKeyword command) {
        Optional<Keyword> optionalMatch = this.repository.findByKeyword(command.getKeyword());

        if (optionalMatch.isEmpty()) {
            return;
        }

        Keyword keywordOverview = optionalMatch.get();
        keywordOverview.removeBeoordeling(command.getBeoordeling());

        this.repository.save(keywordOverview);
    }

    public void handle(AddBestellingToKeyword command) {
        Keyword keywordOverview = this.createIfNotExists(command.getKeyword());
        keywordOverview.addBestelling(command.getBestelling());

        this.repository.save(keywordOverview);
    }

    public void handle(RemoveBestellingFromKeyword command) {
        Optional<Keyword> optionalMatch = this.repository.findByKeyword(command.getKeyword());

        if (optionalMatch.isEmpty()) {
            return;
        }

        Keyword keywordOverview = optionalMatch.get();
        keywordOverview.removeBestelling(command.getBestelling());

        this.repository.save(keywordOverview);
    }

    private Keyword createIfNotExists(String keyword) {
        return this.repository
                .findByKeyword(keyword)
                .orElse(new Keyword(keyword));
    }
}
