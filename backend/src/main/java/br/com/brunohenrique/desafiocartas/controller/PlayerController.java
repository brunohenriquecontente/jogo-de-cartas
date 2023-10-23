package br.com.brunohenrique.desafiocartas.controller;

import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import br.com.brunohenrique.desafiocartas.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerDTO> create(@RequestBody PlayerDTO player){
        player = playerService.insert(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(player);
    }

    @GetMapping(value = "{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerDTO> get(@PathVariable Long playerId){
        PlayerDTO player = playerService.getById(playerId);
        return ResponseEntity.status(HttpStatus.OK).body(player);
    }

    @DeleteMapping(value = "{playerId}")
    public ResponseEntity<String> delete(@PathVariable Long playerId) {
        playerService.deleteById(playerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}
