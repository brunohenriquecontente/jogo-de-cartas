package br.com.brunohenrique.desafiocartas.service.impl;

import br.com.brunohenrique.desafiocartas.clients.ClientFeignDeck;
import br.com.brunohenrique.desafiocartas.dto.*;
import br.com.brunohenrique.desafiocartas.entity.CardEntity;
import br.com.brunohenrique.desafiocartas.entity.DeckEntity;
import br.com.brunohenrique.desafiocartas.entity.MatchEntity;
import br.com.brunohenrique.desafiocartas.entity.PlayerEntity;
import br.com.brunohenrique.desafiocartas.exceptions.BadRequestException;
import br.com.brunohenrique.desafiocartas.repository.CardRepository;
import br.com.brunohenrique.desafiocartas.repository.DeckRepository;
import br.com.brunohenrique.desafiocartas.repository.MatchRepository;
import br.com.brunohenrique.desafiocartas.repository.PlayerRepository;
import br.com.brunohenrique.desafiocartas.service.MatchService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MatchServiceImpl implements MatchService {

  private final MatchRepository matchRepository;

  private final ClientFeignDeck clientFeignDeck;

  private final DeckRepository deckRepository;

  private final PlayerRepository playerRepository;

  private final CardRepository cardRepository;

  public MatchServiceImpl(
      MatchRepository matchRepository,
      ClientFeignDeck clientFeignDeck,
      DeckRepository deckRepository,
      PlayerRepository playerRepository,
      CardRepository cardRepository) {
    this.matchRepository = matchRepository;
    this.clientFeignDeck = clientFeignDeck;
    this.deckRepository = deckRepository;
    this.playerRepository = playerRepository;
    this.cardRepository = cardRepository;
  }

  @Override
  public MatchDTO insert(List<PlayerDTO> players) {
    DeckEntity deckEntity = new DeckEntity();
    // Obter baralho
    DeckDTO deckDTO = clientFeignDeck.getNewDeck();
    BeanUtils.copyProperties(deckDTO, deckEntity);
    MatchEntity matchEntity = new MatchEntity(deckEntity);
    matchEntity = matchRepository.save(matchEntity);
    deckEntity.setMatch(matchEntity);
    deckRepository.save(deckEntity);

    // distribuir cartas e rankear
    MatchEntity finalMatchEntity = matchEntity;
    players.forEach(
        playerDTO -> {
          PlayerEntity playerEntity =
              playerRepository
                  .findById(playerDTO.id())
                  .orElseThrow(() -> BadRequestException.notFoundException("Player not found."));
          // remove cartas do jogador, caso ele tenha jogado uma partida anteriormente
          cardRepository.deleteAllByPlayerId(playerEntity.getId());
          playerEntity.setMatch(finalMatchEntity);
          DrawResponseDTO cards = clientFeignDeck.getCards(deckDTO.deckId(), 5);
          List<CardDTO> listCardDTO = cards.cards();
          List<CardEntity> cardEntityList = new ArrayList<>();
          mapCardValues(playerEntity, listCardDTO, cardEntityList);
          playerEntity.setCards(cardEntityList);
          playerRepository.save(playerEntity);
        });

    return new MatchDTO(matchEntity.getId(), null, deckDTO, null);
  }

  private void mapCardValues(
      PlayerEntity playerEntity, List<CardDTO> listCardDTO, List<CardEntity> cardEntityList) {
    for (CardDTO cardDTO : listCardDTO) {
      CardEntity cardEntity = new CardEntity();
      cardEntity.setSuit(cardDTO.suit());
      cardEntity.setCode(cardDTO.code());
      cardEntity.setPlayer(playerEntity);
      Map<String, Integer> valueToRank = new HashMap<>();
      valueToRank.put("KING", 13);
      valueToRank.put("QUEEN", 12);
      valueToRank.put("JACK", 11);
      valueToRank.put("ACE", 1);
      String cardValue = cardDTO.value();
      Integer rank = valueToRank.get(cardValue);
      cardEntity.setRank(rank != null ? rank : Integer.valueOf(cardValue));
      cardEntity.setCode(cardDTO.value());
      cardEntityList.add(cardEntity);
      cardRepository.save(cardEntity);
    }
  }

  @Override
  public MatchDTO getWinner(Long matchId) {
    List<PlayerEntity> playerEntityList = playerRepository.findAllByMatchId(matchId);
    final String winnerPlayer = checkWinnerPlayer(playerEntityList);
    MatchEntity matchEntity =
        matchRepository
            .findById(matchId)
            .orElseThrow(() -> BadRequestException.notFoundException("Match not found."));
    matchEntity.setWinner(winnerPlayer);
    matchRepository.save(matchEntity);
    DeckDTO deckDTO = new DeckDTO(matchEntity.getDeck().getDeckId());

    return new MatchDTO(matchEntity.getId(), matchEntity.getWinner(), deckDTO, null);
  }

  private static String checkWinnerPlayer(List<PlayerEntity> playerEntityList) {
    List<String> winners = new ArrayList<>();
    int maxSum = 0;

    for (PlayerEntity player : playerEntityList) {
      int sum = player.getCards().stream().mapToInt(CardEntity::getRank).sum();

      if (sum > maxSum) {
        maxSum = sum;
        winners.clear();
        winners.add(player.getName());
      } else if (sum == maxSum) {
        winners.add(player.getName());
      }
    }

    return String.join(", ", winners);
  }
}
