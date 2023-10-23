package br.com.brunohenrique.desafiocartas.controller;


import br.com.brunohenrique.desafiocartas.dto.MatchDTO;
import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import br.com.brunohenrique.desafiocartas.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insert(@RequestBody List<PlayerDTO> players){
        matchService.insert(players);
        return ResponseEntity.status(HttpStatus.OK).build();
   }

    @GetMapping(  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MatchDTO> getWinner(@PathVariable String matchId){
        MatchDTO matchDTO = matchService.getWinner(matchId);
        return ResponseEntity.status(HttpStatus.OK).body(matchDTO);
    }
}