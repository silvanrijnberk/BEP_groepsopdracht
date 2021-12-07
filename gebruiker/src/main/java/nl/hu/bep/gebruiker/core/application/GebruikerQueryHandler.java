package nl.hu.bep.gebruiker.core.application;

import nl.hu.bep.gebruiker.core.application.query.GetGebruikerById;
import nl.hu.bep.gebruiker.core.application.query.ListGebruikers;
import nl.hu.bep.gebruiker.core.domain.Gebruiker;
import nl.hu.bep.gebruiker.core.domain.exception.GebruikerNotFound;
import nl.hu.bep.gebruiker.core.port.storage.GebruikerRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GebruikerQueryHandler {
    private final GebruikerRepository repository;

    public GebruikerQueryHandler(GebruikerRepository repository){
        this.repository = repository;
    }

    public Gebruiker handle(GetGebruikerById query){
        return this.repository.findById(query.getId())
                .orElseThrow(() -> new GebruikerNotFound(query.getId().toString()));
    }

    public List<Gebruiker> handle(ListGebruikers query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.repository.findAll(sort);
    }

    private Sort createSort(String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);

        if (direction.equals("desc")) {
            sort = sort.descending();
        }

        return sort;
    }


}
