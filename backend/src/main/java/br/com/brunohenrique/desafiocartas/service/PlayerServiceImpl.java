package br.com.brunohenrique.desafiocartas.service;

import br.com.brunohenrique.desafiocartas.dto.CardDTO;
import br.com.brunohenrique.desafiocartas.dto.DrawResponseDTO;
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

import java.util.List;
import java.util.Objects;
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
        playerEntity = playerRepository.save(playerEntity);
        return new PlayerDTO(playerEntity.getId(),playerEntity.getName(), null);
    }

    public PlayerDTO drawCards(Long playerId, String deckId){
        PlayerEntity playerEntity = playerRepository.findById(playerId).get();

        DrawResponseDTO drawResponseDTO = clientFeignDeck.getCards(deckId, 5);
        List<CardDTO> cards = drawResponseDTO.cards();
        PlayerEntity finalPlayerEntity = playerEntity;
        List<CardEntity> cardEntityList = cards.stream()
                .map(cardDTO -> {
                    CardEntity cardEntity = new CardEntity();
                    cardEntity.setSuit(cardDTO.suit());
                    cardEntity.setPlayer(finalPlayerEntity);

                    if(Objects.equals(cardDTO.value(), "KING")){
                        cardEntity.setRank(13);
                    } else if (Objects.equals(cardDTO.value(), "QUEEN")) {
                        cardEntity.setRank(12);
                    }else if(Objects.equals(cardDTO.value(), "JACK")){
                        cardEntity.setRank(11);
                    }else if (Objects.equals(cardDTO.value(), "ACE")){
                        cardEntity.setRank(1);
                    }else{
                        cardEntity.setRank(Integer.valueOf(cardDTO.value()));
                    }
                    cardEntity.setCode(cardDTO.value());
                    cardRepository.save(cardEntity);
                    return cardEntity;
                })
                .collect(Collectors.toList());
        playerEntity.setCards(cardEntityList);
        playerEntity = playerRepository.save(finalPlayerEntity);


        return new PlayerDTO(playerEntity.getId(),playerEntity.getName(), playerEntity.getCards());
    }

}