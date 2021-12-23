package nl.hu.bep.bestelling.infrastructure.driver.web;

import nl.hu.bep.bestelling.core.application.BestellingCommandHandler;
import nl.hu.bep.bestelling.core.application.BestellingQueryHandler;
import nl.hu.bep.bestelling.core.application.command.RegisterBestelling;
import nl.hu.bep.bestelling.core.application.query.GetBestellingById;
import nl.hu.bep.bestelling.core.domain.Bestelling;
import nl.hu.bep.bestelling.infrastructure.driver.web.request.RegisterBestellingRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.UUID;

@RestController
@RequestMapping("/gebruikers")
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

    @PostMapping
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
