package br.com.brunohenrique.desafiocartas.controller;

import br.com.brunohenrique.desafiocartas.dto.MatchDTO;
import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import br.com.brunohenrique.desafiocartas.entity.CardEntity;
import br.com.brunohenrique.desafiocartas.service.MatchService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "match")
public class MatchController {

  private final MatchService matchService;

  public MatchController(MatchService matchService) {
    this.matchService = matchService;
  }

  @PostMapping(
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE, path = "teste")
          @ResponseStatus(HttpStatus.CREATED)
          public MatchDTO insertNovo(@Valid @RequestBody List<PlayerDTO> players) {
          return matchService.insert(players);
          }

          @GetMapping(value = "{matchId}", produces = MediaType.APPLICATION_JSON_VALUE)
          @ResponseStatus(HttpStatus.OK)
          public MatchDTO getWinner(@PathVariable Long matchId) {
          return matchService.getWinner(matchId);
          }


}
