package br.com.brunohenrique.desafiocartas.service.impl;

import br.com.brunohenrique.desafiocartas.dto.*;
import br.com.brunohenrique.desafiocartas.entity.CardEntity;
import br.com.brunohenrique.desafiocartas.entity.DeckEntity;
import br.com.brunohenrique.desafiocartas.entity.MatchEntity;
import br.com.brunohenrique.desafiocartas.entity.PlayerEntity;
import br.com.brunohenrique.desafiocartas.repository.*;
import br.com.brunohenrique.desafiocartas.service.ClientFeignDeck;
import br.com.brunohenrique.desafiocartas.service.MatchService;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
    MatchEntity matchEntity = new MatchEntity();
    DeckEntity deckEntity = new DeckEntity();
    // Obter baralho
    DeckDTO deckDTO = clientFeignDeck.getNewDeck();
    BeanUtils.copyProperties(deckDTO, deckEntity);
    deckRepository.save(deckEntity);
    matchEntity.setDeck(deckEntity);
    matchRepository.save(matchEntity);

    // distribuir cartas e rankear
    players.forEach(
        playerDTO -> {
          PlayerEntity playerEntity = playerRepository.findById(playerDTO.id()).get();
          // remove cartas do jogador, caso ele tenha jogado uma partida anteriormente
          cardRepository.deleteAllByPlayerId(playerEntity.getId());
          playerEntity.setMatch(matchEntity);
          DrawResponseDTO cards = clientFeignDeck.getCards(deckDTO.deckId(), 5);
          List<CardDTO> listCardDTO = cards.cards();
          List<CardEntity> cardEntityList = new ArrayList<>();
          mapCardValues(playerEntity, listCardDTO, cardEntityList);
          playerEntity.setCards(cardEntityList);
          playerRepository.save(playerEntity);
        });

    return new MatchDTO(matchEntity.getId(), matchEntity.getWinner(), deckDTO, null);
  }

  private void mapCardValues(
      PlayerEntity playerEntity, List<CardDTO> listCardDTO, List<CardEntity> cardEntityList) {
    for (CardDTO cardDTO : listCardDTO) {
      CardEntity cardEntity = new CardEntity();
      cardEntity.setSuit(cardDTO.suit());
      cardEntity.setCode(cardDTO.code());
      cardEntity.setPlayer(playerEntity);
      if (Objects.equals(cardDTO.value(), "KING")) {
        cardEntity.setRank(13);
      } else if (Objects.equals(cardDTO.value(), "QUEEN")) {
        cardEntity.setRank(12);
      } else if (Objects.equals(cardDTO.value(), "JACK")) {
        cardEntity.setRank(11);
      } else if (Objects.equals(cardDTO.value(), "ACE")) {
        cardEntity.setRank(1);
      } else {
        cardEntity.setRank(Integer.valueOf(cardDTO.value()));
      }
      cardEntity.setCode(cardDTO.value());
      cardEntityList.add(cardEntity);
      cardRepository.save(cardEntity);
    }
  }

  @Override
  public MatchDTO getWinner(Long matchId) {
    List<PlayerEntity> playerEntityList = playerRepository.findAllByMatchId(matchId);
    StringBuilder winnerName = new StringBuilder();
    int maxSum = Integer.MIN_VALUE;
    boolean isTie = false;

    winnerName = checkWinnerPlayer(playerEntityList, winnerName, maxSum, isTie);

    MatchEntity matchEntity = matchRepository.findById(matchId).get();
    matchEntity.setWinner(winnerName.toString());
    matchRepository.save(matchEntity);
    DeckDTO deckDTO = new DeckDTO(matchEntity.getDeck().getDeckId());

    MatchDTO matchDTO = new MatchDTO(matchEntity.getId(), matchEntity.getWinner(), deckDTO, null);

    return matchDTO;
  }

  private static StringBuilder checkWinnerPlayer(
      List<PlayerEntity> playerEntityList, StringBuilder winnerName, int maxSum, boolean isTie) {
    for (PlayerEntity player : playerEntityList) {
      int sum = player.getCards().stream().mapToInt(CardEntity::getRank).sum();

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
    return winnerName;
  }
}
