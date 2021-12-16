package nl.hu.bep.beoordeling.core.application;

import nl.hu.bep.beoordeling.core.application.query.FindBeoordelingByKeyword;
import nl.hu.bep.beoordeling.core.application.query.GetBeoordelingById;
import nl.hu.bep.beoordeling.core.application.query.ListBeoordelingen;
import nl.hu.bep.beoordeling.core.domain.Beoordeling;
import nl.hu.bep.beoordeling.core.domain.exception.BeoordelingNotFound;
import nl.hu.bep.beoordeling.core.port.storage.BeoordelingRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeoordelingQueryHandler {
    private final BeoordelingRepository repository;

    public BeoordelingQueryHandler(BeoordelingRepository repository) {
        this.repository = repository;
    }

    public Beoordeling handle(GetBeoordelingById query){
        return this.repository.findById(query.getId())
                .orElseThrow(() -> new BeoordelingNotFound(query.getId().toString()));
    }

    public List<Beoordeling> handle(ListBeoordelingen query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.repository.findAll(sort);
    }

    public List<Beoordeling> handle(FindBeoordelingByKeyword query) {
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
