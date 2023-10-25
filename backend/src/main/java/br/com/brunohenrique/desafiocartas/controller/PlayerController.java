package br.com.brunohenrique.desafiocartas.controller;

import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import br.com.brunohenrique.desafiocartas.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/player")
public class PlayerController {

  private final PlayerService playerService;

  public PlayerController(PlayerService playerService) {
    this.playerService = playerService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PlayerDTO create(@Valid @RequestBody PlayerDTO player) {
    return playerService.insert(player);
  }

  @GetMapping(value = "{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public PlayerDTO get(@PathVariable Long playerId) {
    return playerService.getById(playerId);
  }

  @DeleteMapping(value = "{playerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long playerId) {
    playerService.deleteById(playerId);
  }

  @PutMapping(value = "{playerId}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public PlayerDTO update(@PathVariable Long playerId, @Valid @RequestBody PlayerDTO playerDTO) {
    return playerService.updateById(playerId, playerDTO);
  }
}
