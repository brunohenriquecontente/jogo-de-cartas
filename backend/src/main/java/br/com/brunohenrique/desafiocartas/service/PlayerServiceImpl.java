package br.com.brunohenrique.desafiocartas.service;

import br.com.brunohenrique.desafiocartas.dto.CardDTO;
import br.com.brunohenrique.desafiocartas.dto.MatchDTO;
import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import br.com.brunohenrique.desafiocartas.entity.CardEntity;
import br.com.brunohenrique.desafiocartas.entity.PlayerEntity;
import br.com.brunohenrique.desafiocartas.repository.AbstractBaseRepositoryImpl;
import br.com.brunohenrique.desafiocartas.repository.CardRepository;
import br.com.brunohenrique.desafiocartas.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlayerServiceImpl extends AbstractBaseRepositoryImpl<PlayerEntity, Long> implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ClientFeignDeck clientFeignDeck;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        super(playerRepository);
    }


    public PlayerDTO insert(PlayerDTO playerDTO) {
        PlayerEntity playerEntity = new PlayerEntity();
        BeanUtils.copyProperties(playerDTO, playerEntity);
        playerEntity.setCards(null);
        playerEntity.setMatch(null);
        playerEntity = playerRepository.save(playerEntity);
        return new PlayerDTO(playerEntity.getId(),playerEntity.getName(), null, null);
    }

    public PlayerDTO getById(Long playerId){
       PlayerEntity playerEntity = playerRepository.findById(playerId).get();
       return  playerEntity.toDTO();
    }

    public void deleteById(Long playerId){
         playerRepository.deleteById(playerId);
    }

}