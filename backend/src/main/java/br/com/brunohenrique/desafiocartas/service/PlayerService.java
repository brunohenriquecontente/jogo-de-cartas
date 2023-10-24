package br.com.brunohenrique.desafiocartas.service;

import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;

public interface PlayerService {
  public PlayerDTO insert(PlayerDTO playerDTO);

  public PlayerDTO getById(Long playerId);

  public void deleteById(Long playerId);

  public PlayerDTO updateById(Long playerId, PlayerDTO playerDTO);
}
