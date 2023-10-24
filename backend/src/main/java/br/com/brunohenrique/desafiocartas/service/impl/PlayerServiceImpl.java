package br.com.brunohenrique.desafiocartas.service.impl;

import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import br.com.brunohenrique.desafiocartas.entity.PlayerEntity;
import br.com.brunohenrique.desafiocartas.repository.AbstractBaseRepositoryImpl;
import br.com.brunohenrique.desafiocartas.repository.CardRepository;
import br.com.brunohenrique.desafiocartas.repository.PlayerRepository;
import br.com.brunohenrique.desafiocartas.service.ClientFeignDeck;
import br.com.brunohenrique.desafiocartas.service.PlayerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;


    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;

    }

    public PlayerDTO insert(PlayerDTO playerDTO) {
        PlayerEntity playerEntity = new PlayerEntity();
        BeanUtils.copyProperties(playerDTO, playerEntity);
        playerEntity.setCards(null);
        playerEntity.setMatch(null);
        playerEntity = playerRepository.save(playerEntity);
        return new PlayerDTO(playerEntity.getId(), playerEntity.getName(), null, null);
    }

    public PlayerDTO getById(Long playerId) {
        PlayerEntity playerEntity = playerRepository.findById(playerId).get();
        return playerEntity.toDTO();
    }

    public void deleteById(Long playerId) {
        playerRepository.deleteById(playerId);
    }
}
