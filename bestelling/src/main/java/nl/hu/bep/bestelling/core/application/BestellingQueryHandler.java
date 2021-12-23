package nl.hu.bep.bestelling.core.application;

import nl.hu.bep.bestelling.core.application.query.FindBestellingByKeyword;
import nl.hu.bep.bestelling.core.application.query.GetBestellingById;
import nl.hu.bep.bestelling.core.application.query.ListBestellings;
import nl.hu.bep.bestelling.core.domain.Bestelling;
import nl.hu.bep.bestelling.core.domain.exception.BestellingNotFound;
import nl.hu.bep.bestelling.core.port.storage.BestellingRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BestellingQueryHandler {
    private final BestellingRepository repository;

    public BestellingQueryHandler(BestellingRepository repository){
        this.repository = repository;
    }

    public Bestelling handle(GetBestellingById query){
        return this.repository.findById(query.getId())
                .orElseThrow(() -> new BestellingNotFound(query.getId().toString()));
    }

    public List<Bestelling> handle(ListBestellings query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.repository.findAll(sort);
    }

    public List<Bestelling> handle(FindBestellingByKeyword query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.repository.findByKeywordsEquals(query.getKeyword(), sort);
    }

    private Sort createSort(String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);

        if (direction.equals("desc")) {
            sort = sort.descending();
        }

        return sort;
    }


}
