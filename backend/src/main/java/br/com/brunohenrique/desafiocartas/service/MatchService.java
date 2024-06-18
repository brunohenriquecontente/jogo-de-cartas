package br.com.brunohenrique.desafiocartas.service;

import br.com.brunohenrique.desafiocartas.dto.MatchDTO;
import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import java.util.List;

public interface MatchService {
  public MatchDTO insert(List<PlayerDTO> players);

  public MatchDTO getWinner(Long matchId);
}
