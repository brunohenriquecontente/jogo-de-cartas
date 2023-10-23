package br.com.brunohenrique.desafiocartas.service;

import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import br.com.brunohenrique.desafiocartas.entity.PlayerEntity;

import java.util.UUID;

public interface PlayerService extends AbstractBaseService<PlayerEntity, Long> {

    public PlayerDTO insert(PlayerDTO playerDTO);

    public PlayerDTO drawCards(Long playerId, String deckId);
}
