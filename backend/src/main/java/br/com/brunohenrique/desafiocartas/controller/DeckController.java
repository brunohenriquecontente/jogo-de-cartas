package br.com.brunohenrique.desafiocartas.controller;

import br.com.brunohenrique.desafiocartas.dto.DeckDTO;
import br.com.brunohenrique.desafiocartas.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "deck")
public class DeckController {

    @Autowired
    private DeckService deckService;

    @GetMapping
    public ResponseEntity<DeckDTO> suffle(){
        DeckDTO deckDTO = deckService.insert();
        return ResponseEntity.status(HttpStatus.CREATED).body(deckDTO);
    }
}
