package nl.hu.bep.bestelling.infrastructure.driver.web;

import nl.hu.bep.bestelling.core.application.BestellingCommandHandler;
import nl.hu.bep.bestelling.core.application.BestellingQueryHandler;
import nl.hu.bep.bestelling.core.application.command.AddKeyword;
import nl.hu.bep.bestelling.core.application.command.RegisterBestelling;
import nl.hu.bep.bestelling.core.application.command.RemoveKeyword;
import nl.hu.bep.bestelling.core.application.query.GetBestellingById;
import nl.hu.bep.bestelling.core.application.query.ListBestellingen;
import nl.hu.bep.bestelling.core.domain.Bestelling;
import nl.hu.bep.bestelling.infrastructure.driver.web.request.KeywordRequest;
import nl.hu.bep.bestelling.infrastructure.driver.web.request.RegisterBestellingRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/bestellingen")
public class BestellingController {
    private final BestellingCommandHandler commandHandler;
    private final BestellingQueryHandler queryHandler;

    public BestellingController(BestellingCommandHandler commandHandler, BestellingQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }


    @GetMapping("/{id}")
    public Bestelling findBestellingById(@PathVariable UUID id){
        return this.queryHandler.handle(new GetBestellingById(id));
    }

    @GetMapping
    public List<Bestelling> getAllBestellingen(){
        return this.queryHandler.handle(new ListBestellingen(null, null));
    }

    @PostMapping("/{id}/keyword")
    public Bestelling addKeyword(@PathVariable UUID id, @Valid @RequestBody KeywordRequest request) {
        return this.commandHandler.handle(new AddKeyword(id, request.keyword));
    }

    @DeleteMapping("/{id}/keyword")
    public Bestelling removeKeyword(@PathVariable UUID id, @Valid @RequestBody KeywordRequest request) {
        return this.commandHandler.handle(new RemoveKeyword(id, request.keyword));
    }

    @PostMapping("/register")
    public Bestelling registerBestelling(@Valid @RequestBody RegisterBestellingRequest request){
        String status =  "bezig";
        Date date  = new Date();
        ArrayList<UUID> temp = request.gerechten;
        Hashtable<UUID, Integer> gerechten = new Hashtable<>();
        for( UUID gerecht : temp){
            if(gerechten.containsKey(gerecht)){
                Integer temp1 = gerechten.get(gerecht);
                gerechten.replace(gerecht, temp1, temp1+1);
            }else {
                gerechten.put(gerecht, 1);
            }
        }
        return this.commandHandler.handle(new RegisterBestelling(request.gebruiker, status, request.opmerkingen, date, gerechten));
    }




}
