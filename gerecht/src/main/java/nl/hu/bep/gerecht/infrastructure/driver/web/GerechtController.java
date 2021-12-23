package nl.hu.bep.gerecht.infrastructure.driver.web;

import nl.hu.bep.gerecht.core.application.GerechtCommandHandler;
import nl.hu.bep.gerecht.core.application.GerechtQueryHandler;
import nl.hu.bep.gerecht.core.application.command.AddKeyword;
import nl.hu.bep.gerecht.core.application.command.RegisterGerecht;
import nl.hu.bep.gerecht.core.application.command.RemoveKeyword;
import nl.hu.bep.gerecht.core.application.query.GetGerechtById;
import nl.hu.bep.gerecht.core.application.query.ListGerechten;
import nl.hu.bep.gerecht.core.domain.Gerecht;
import nl.hu.bep.gerecht.infrastructure.driver.web.request.KeywordRequest;
import nl.hu.bep.gerecht.infrastructure.driver.web.request.RegisterGerechtRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gerecht")
public class GerechtController {
    private final GerechtCommandHandler commandHandler;
    private final GerechtQueryHandler queryHandler;

    public GerechtController(GerechtCommandHandler commandHandler, GerechtQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }


    @GetMapping("/{id}")
    public Gerecht findGerechtById(@PathVariable UUID id){
        return this.queryHandler.handle(new GetGerechtById(id));
    }

    @GetMapping
    public List<Gerecht> getAllGerechten(){
        return this.queryHandler.handle(new ListGerechten(null, null));
    }

    @PostMapping("/{id}/keyword")
    public Gerecht addKeyword(@PathVariable UUID id, @Valid @RequestBody KeywordRequest request) {
        return this.commandHandler.handle(new AddKeyword(id, request.keyword));
    }

    @DeleteMapping("/{id}/keyword")
    public Gerecht removeKeyword(@PathVariable UUID id, @Valid @RequestBody KeywordRequest request) {
        return this.commandHandler.handle(new RemoveKeyword(id, request.keyword));
    }

    @PostMapping("/register")
    public Gerecht registerGerecht(@Valid @RequestBody RegisterGerechtRequest request){
        return this.commandHandler.handle(new RegisterGerecht(request.naam, request.beschrijving,  request.ingredienten, request.prijs));
    }



}
