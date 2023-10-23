package br.com.brunohenrique.desafiocartas.service;

import br.com.brunohenrique.desafiocartas.dto.DeckDTO;
import br.com.brunohenrique.desafiocartas.dto.MatchDTO;
import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import br.com.brunohenrique.desafiocartas.entity.DeckEntity;
import br.com.brunohenrique.desafiocartas.entity.MatchEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface MatchService extends AbstractBaseService<MatchEntity, Long> {
    public MatchDTO insert(List<PlayerDTO> players);

    public MatchDTO getWinner(Long matchId);

}
