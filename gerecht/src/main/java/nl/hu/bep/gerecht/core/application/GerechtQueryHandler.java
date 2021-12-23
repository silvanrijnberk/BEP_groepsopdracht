package nl.hu.bep.gerecht.core.application;

import nl.hu.bep.gerecht.core.application.query.FindGerechtByKeyword;
import nl.hu.bep.gerecht.core.application.query.GetGerechtById;
import nl.hu.bep.gerecht.core.application.query.ListGerechten;
import nl.hu.bep.gerecht.core.domain.Gerecht;
import nl.hu.bep.gerecht.core.domain.exception.GerechtNotFound;
import nl.hu.bep.gerecht.core.port.storage.GerechtRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerechtQueryHandler {
    private final GerechtRepository repository;

    public GerechtQueryHandler(GerechtRepository repository) {
        this.repository = repository;
    }

    public Gerecht handle(GetGerechtById query){
        return this.repository.findById(query.getId())
                .orElseThrow(() -> new GerechtNotFound(query.getId().toString()));
    }

    public List<Gerecht> handle(ListGerechten query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.repository.findAll(sort);
    }

    public List<Gerecht> handle(FindGerechtByKeyword query) {
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
