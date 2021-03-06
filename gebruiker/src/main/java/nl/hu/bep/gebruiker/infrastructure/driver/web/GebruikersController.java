package nl.hu.bep.gebruiker.infrastructure.driver.web;

import nl.hu.bep.gebruiker.core.application.GebruikerCommandHandler;
import nl.hu.bep.gebruiker.core.application.GebruikerQueryHandler;
import nl.hu.bep.gebruiker.core.application.command.AddKeyword;
import nl.hu.bep.gebruiker.core.application.command.RegisterGebruiker;
import nl.hu.bep.gebruiker.core.application.command.RemoveKeyword;
import nl.hu.bep.gebruiker.core.application.query.FindGebruikerByKeyword;
import nl.hu.bep.gebruiker.core.application.query.GetAdresById;
import nl.hu.bep.gebruiker.core.application.query.GetGebruikerById;
import nl.hu.bep.gebruiker.core.application.query.ListGebruikers;
import nl.hu.bep.gebruiker.core.domain.Adres;
import nl.hu.bep.gebruiker.core.domain.Gebruiker;
import nl.hu.bep.gebruiker.infrastructure.driver.web.request.KeywordRequest;
import nl.hu.bep.gebruiker.infrastructure.driver.web.request.RegisterGebruikerRequest;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gebruikers")
public class GebruikersController {
    private final GebruikerCommandHandler commandHandler;
    private final GebruikerQueryHandler queryHandler;

    public GebruikersController(GebruikerCommandHandler commandHandler, GebruikerQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }


    @GetMapping("/{id}")
    public Gebruiker findGebruikerById(@PathVariable UUID id){
        return this.queryHandler.handle(new GetGebruikerById(id));
    }

    @GetMapping("/adres/{id}")
    public Adres findAdresById(@PathVariable UUID id){
        return this.queryHandler.handle(new GetAdresById(id));
    }

    @GetMapping
    public List<Gebruiker> getAllGebruikers(){
        return this.queryHandler.handle(new ListGebruikers(null, null));
    }

    @PostMapping("/{id}/keyword")
    public Gebruiker addKeyword(@PathVariable UUID id, @Valid @RequestBody KeywordRequest request) {
        return this.commandHandler.handle(new AddKeyword(id, request.keyword));
    }

    @DeleteMapping("/{id}/keyword")
    public Gebruiker removeKeyword(@PathVariable UUID id, @Valid @RequestBody KeywordRequest request) {
        return this.commandHandler.handle(new RemoveKeyword(id, request.keyword));
    }

    @GetMapping(params = {"keyword"})
    public List<Gebruiker> FindGebruikerByKeyword(
            @RequestParam String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return this.queryHandler.handle(
                new FindGebruikerByKeyword(keyword, orderBy, direction)
        );
    }

    @PostMapping("/register")
    public Gebruiker registerGebruiker(@Valid @RequestBody RegisterGebruikerRequest request){

        return this.commandHandler.handle(new RegisterGebruiker(request.firstname, request.lastname, request.email,  request.streetname,  request.number,  request.affix,  request.postalcode,  request.city,  request.province));
    }




}
