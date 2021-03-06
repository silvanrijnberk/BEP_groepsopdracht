package nl.hu.bep.beoordeling.infrastructure.driver.web;

import nl.hu.bep.beoordeling.core.application.BeoordelingCommandHandler;
import nl.hu.bep.beoordeling.core.application.BeoordelingQueryHandler;
import nl.hu.bep.beoordeling.core.application.command.AddKeyword;
import nl.hu.bep.beoordeling.core.application.command.RegisterBeoordeling;
import nl.hu.bep.beoordeling.core.application.command.RemoveKeyword;
import nl.hu.bep.beoordeling.core.application.query.GetBeoordelingById;
import nl.hu.bep.beoordeling.core.domain.Beoordeling;
import nl.hu.bep.beoordeling.infrastructure.driver.web.request.KeywordRequest;
import nl.hu.bep.beoordeling.infrastructure.driver.web.request.RegisterBeoordelingRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/beoordeling")
public class BeoordelingController {
    private final BeoordelingCommandHandler commandHandler;
    private final BeoordelingQueryHandler queryHandler;

    public BeoordelingController(BeoordelingCommandHandler commandHandler, BeoordelingQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @GetMapping("/{id}")
    public Beoordeling findBeoordelingById(@PathVariable UUID id){
        return this.queryHandler.handle(new GetBeoordelingById(id));
    }

    @PostMapping("/{id}/keyword")
    public Beoordeling addKeyword(@PathVariable UUID id, @Valid @RequestBody KeywordRequest request) {
        return this.commandHandler.handle(new AddKeyword(id, request.keyword));
    }

    @DeleteMapping("/{id}/keyword")
    public Beoordeling removeKeyword(@PathVariable UUID id, @Valid @RequestBody KeywordRequest request) {
        return this.commandHandler.handle(new RemoveKeyword(id, request.keyword));
    }

    @PostMapping("/register")
    public Beoordeling registerBeoordeling(@Valid @RequestBody RegisterBeoordelingRequest request){
        Date date = new Date();
        return this.commandHandler.handle(new RegisterBeoordeling(request.gebruiker, request.sterren, date, request.beschrijving));
    }



}
