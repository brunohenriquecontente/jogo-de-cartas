package br.com.brunohenrique.desafiocartas.service;

import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import br.com.brunohenrique.desafiocartas.entity.PlayerEntity;

import java.util.Optional;

public interface PlayerService extends AbstractBaseService<PlayerEntity, Long> {

    public PlayerDTO insert(PlayerDTO playerDTO);

    public PlayerDTO getById(Long playerId);

    public void deleteById(Long playerId);

}
