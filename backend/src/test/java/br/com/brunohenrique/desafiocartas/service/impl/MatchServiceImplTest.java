package br.com.brunohenrique.desafiocartas.service.impl;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.brunohenrique.desafiocartas.clients.ClientFeignDeck;
import br.com.brunohenrique.desafiocartas.dto.DeckDTO;
import br.com.brunohenrique.desafiocartas.dto.DrawResponseDTO;
import br.com.brunohenrique.desafiocartas.dto.MatchDTO;
import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import br.com.brunohenrique.desafiocartas.entity.CardEntity;
import br.com.brunohenrique.desafiocartas.entity.DeckEntity;
import br.com.brunohenrique.desafiocartas.entity.MatchEntity;
import br.com.brunohenrique.desafiocartas.entity.PlayerEntity;
import br.com.brunohenrique.desafiocartas.repository.CardRepository;
import br.com.brunohenrique.desafiocartas.repository.DeckRepository;
import br.com.brunohenrique.desafiocartas.repository.MatchRepository;
import br.com.brunohenrique.desafiocartas.repository.PlayerRepository;
import br.com.brunohenrique.desafiocartas.utils.CardDTOBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MatchServiceImplTest {

  @Mock private MatchRepository matchRepository;
  @Mock private ClientFeignDeck clientFeignDeck;
  @Mock private DeckRepository deckRepository;
  @Mock private PlayerRepository playerRepository;
  @Mock private CardRepository cardRepository;

  @InjectMocks private MatchServiceImpl matchServiceImpl;

  @ParameterizedTest
  @ValueSource(strings = {"KING", "QUEEN", "JACK", "ACE", "1"})
  void shouldInsertNewPlayerWhenListIsValid(String cardValue) {
    var listPlayers = List.of(new PlayerDTO(1L, "player1", null, null));
    var deckEntity = new DeckEntity();
    var matchEntity = new MatchEntity(deckEntity); // Criar matchEntity a partir do deckEntity
    matchEntity.setId(1L);
    matchEntity.setWinner("playerwinner");
    var playerEntity = new PlayerEntity();
    var cardEntity = new CardEntity();

    var listOfCards =
        List.of(
            CardDTOBuilder.aCardDTO().withDefaultValues().withValue(cardValue).build(),
            CardDTOBuilder.aCardDTO().withDefaultValues().withValue(cardValue).build(),
            CardDTOBuilder.aCardDTO().withDefaultValues().withValue(cardValue).build(),
            CardDTOBuilder.aCardDTO().withDefaultValues().withValue(cardValue).build(),
            CardDTOBuilder.aCardDTO().withDefaultValues().withValue(cardValue).build());

    var drawResponseDTO = new DrawResponseDTO(true, "gutcun0ifbg4", listOfCards, 52);

    when(clientFeignDeck.getNewDeck()).thenReturn(new DeckDTO("gutcun0ifbg4"));
    when(deckRepository.save(any(DeckEntity.class))).thenReturn(deckEntity);
    when(matchRepository.save(any(MatchEntity.class))).thenReturn(matchEntity);
    when(playerRepository.findById(any())).thenReturn(Optional.of(playerEntity));
    doNothing().when(cardRepository).deleteAllByPlayerId(any());
    when(clientFeignDeck.getCards("gutcun0ifbg4", 5)).thenReturn(drawResponseDTO);
    when(cardRepository.save(any(CardEntity.class))).thenReturn(cardEntity);
    when(playerRepository.save(any())).thenReturn(playerEntity);

    assertThatCode(
            () -> {
              var resultMatchDTO = matchServiceImpl.insert(listPlayers);
              assertEquals(matchEntity.getId(), resultMatchDTO.id());
            })
        .doesNotThrowAnyException();

    verify(playerRepository, times(1)).findById(any());
    verify(cardRepository, times(listPlayers.size())).deleteAllByPlayerId(any());
    verify(playerRepository, times(listPlayers.size())).save(any());
    verify(cardRepository, times(listPlayers.size() * 5)).save(any());
  }

  @Test
  void shouldReturnAWinnerPlayerWhenHasNoTie() {
    Long matchId = 1L;
    PlayerEntity player1 = new PlayerEntity();
    player1.setName("Player 1");
    player1.setCards(
        List.of(
            new CardEntity(13, "KING", "DIAMONDS", player1),
            new CardEntity(12, "QUEEN", "DIAMONDS", player1),
            new CardEntity(11, "JACK", "DIAMONDS", player1),
            new CardEntity(10, "10", "DIAMONDS", player1),
            new CardEntity(9, "9", "DIAMONDS", player1)));
    PlayerEntity player2 = new PlayerEntity();
    player2.setName("Player 2");
    player2.setCards(
        List.of(
            new CardEntity(10, "10", "SPADES", player2),
            new CardEntity(9, "9", "SPADES", player2),
            new CardEntity(8, "8", "SPADES", player2),
            new CardEntity(7, "7", "SPADES", player2),
            new CardEntity(6, "6", "SPADES", player2)));

    MatchEntity matchEntity = new MatchEntity();
    matchEntity.setId(matchId);
    matchEntity.setDeck(new DeckEntity());

    when(playerRepository.findAllByMatchId(matchId)).thenReturn(List.of(player1, player2));
    when(matchRepository.findById(matchId)).thenReturn(Optional.of(matchEntity));

    MatchDTO result = matchServiceImpl.getWinner(matchId);

    assertNotNull(result);
    assertEquals("Player 1", result.winner());
  }

  @Test
  void shouldReturnWinnersPlayersWhenHasATie() {
    Long matchId = 1L;
    PlayerEntity player1 = new PlayerEntity();
    player1.setName("Player 1");
    player1.setCards(
        List.of(
            new CardEntity(13, "KING", "DIAMONDS", player1),
            new CardEntity(12, "QUEEN", "DIAMONDS", player1),
            new CardEntity(11, "JACK", "DIAMONDS", player1),
            new CardEntity(10, "10", "DIAMONDS", player1),
            new CardEntity(9, "9", "DIAMONDS", player1)));
    PlayerEntity player2 = new PlayerEntity();
    player2.setName("Player 2");
    player2.setCards(
        List.of(
            new CardEntity(13, "KING", "SPADES", player2),
            new CardEntity(12, "QUEEN", "SPADES", player2),
            new CardEntity(11, "JACK", "SPADES", player2),
            new CardEntity(10, "10", "SPADES", player2),
            new CardEntity(9, "9", "SPADES", player2)));

    MatchEntity matchEntity = new MatchEntity();
    matchEntity.setId(matchId);
    matchEntity.setDeck(new DeckEntity());

    when(playerRepository.findAllByMatchId(matchId)).thenReturn(Arrays.asList(player1, player2));
    when(matchRepository.findById(matchId)).thenReturn(Optional.of(matchEntity));

    MatchDTO result = matchServiceImpl.getWinner(matchId);

    assertNotNull(result);
    assertEquals("Player 1, Player 2", result.winner());
  }
}
