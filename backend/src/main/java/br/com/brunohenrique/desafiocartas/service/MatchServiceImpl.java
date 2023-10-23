package br.com.brunohenrique.desafiocartas.service;

import br.com.brunohenrique.desafiocartas.dto.*;
import br.com.brunohenrique.desafiocartas.entity.CardEntity;
import br.com.brunohenrique.desafiocartas.entity.DeckEntity;
import br.com.brunohenrique.desafiocartas.entity.MatchEntity;
import br.com.brunohenrique.desafiocartas.entity.PlayerEntity;
import br.com.brunohenrique.desafiocartas.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class MatchServiceImpl extends AbstractBaseRepositoryImpl<MatchEntity, Long> implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ClientFeignDeck clientFeignDeck;

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CardRepository cardRepository;

    public MatchServiceImpl(MatchRepository matchRepository) {
        super(matchRepository);
    }


    @Override
    public MatchDTO insert(List<PlayerDTO> players) {
        MatchEntity matchEntity = new MatchEntity();
        DeckEntity deckEntity = new DeckEntity();
        //Obter baralho
        DeckDTO deckDTO = clientFeignDeck.getNewDeck();
        BeanUtils.copyProperties(deckDTO, deckEntity);
        deckEntity =  deckRepository.save(deckEntity);
        matchEntity.setDeck(deckEntity);
        matchEntity = matchRepository.save(matchEntity);

        //distribuir cartas e rankear
        MatchEntity finalMatchEntity = matchEntity;
        List<PlayerEntity> playerEntitiesList = players.stream()
                .map(playerDTO -> {
                    PlayerEntity playerEntity = playerRepository.findById(playerDTO.id()).get();
                    playerEntity.setMatch(finalMatchEntity);
                    DrawResponseDTO cards = clientFeignDeck.getCards(deckDTO.deckId(), 5);
                    List<CardDTO> listCardDTO = cards.cards();
                    List<CardEntity> cardEntityList = new ArrayList<>();
                    for (CardDTO cardDTO : listCardDTO) {
                        CardEntity cardEntity = new CardEntity();
                        cardEntity.setSuit(cardDTO.suit());
                        cardEntity.setCode(cardDTO.code());
                        cardEntity.setPlayer(playerEntity);
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
                        cardEntityList.add(cardEntity);
                        cardRepository.save(cardEntity);
                    }

                    playerEntity.setCards(cardEntityList);
                    playerRepository.save(playerEntity);
                    return playerEntity;
                })
            .toList();

        return new MatchDTO(matchEntity.getId(),matchEntity.getWinner(),deckDTO, null);
    }

    @Override
    public MatchDTO getWinner(Long matchId) {
       List<PlayerEntity> playerEntityList = playerRepository.findAllByMatchId(matchId);
        StringBuilder winnerName = new StringBuilder();
        int maxSum = Integer.MIN_VALUE;
        boolean isTie = false;

        for (PlayerEntity player : playerEntityList) {
            int sum = player.getCards().stream()
                    .mapToInt(CardEntity::getRank)
                    .sum();

            if (sum > maxSum) {
                maxSum = sum;
                winnerName = new StringBuilder(player.getName());
                isTie = false;
            } else if (sum == maxSum) {
                if (!isTie) {
                    winnerName.append(", ").append(player.getName());
                    isTie = true;
                } else {
                    winnerName.append(", ").append(player.getName());
                }
            }
        }
        MatchEntity matchEntity = matchRepository.findById(matchId).get();
        matchEntity.setWinner(winnerName.toString());
        matchRepository.save(matchEntity);
        DeckDTO deckDTO = new DeckDTO(matchEntity.getDeck().getDeckId());

        MatchDTO matchDTO = new MatchDTO(matchEntity.getId(),matchEntity.getWinner(),deckDTO, null );

        return matchDTO;
    }
}