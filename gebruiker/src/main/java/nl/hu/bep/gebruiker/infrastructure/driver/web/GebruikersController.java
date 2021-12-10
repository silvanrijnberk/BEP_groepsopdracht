package nl.hu.bep.gebruiker.infrastructure.driver.web;

import nl.hu.bep.gebruiker.core.application.GebruikerCommandHandler;
import nl.hu.bep.gebruiker.core.application.GebruikerQueryHandler;
import nl.hu.bep.gebruiker.core.application.command.RegisterGebruiker;
import nl.hu.bep.gebruiker.core.application.query.GetGebruikerById;
import nl.hu.bep.gebruiker.core.domain.Gebruiker;
import nl.hu.bep.gebruiker.infrastructure.driver.web.request.RegisterGebruikerRequest;
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

    @PostMapping
    public Gebruiker registerGebruiker(@Valid @RequestBody RegisterGebruikerRequest request){
        return this.commandHandler.handle(new RegisterGebruiker(request.firstname, request.lastname, request.email, request.password));
    }




}
