package br.com.brunohenrique.desafiocartas.service.impl;

import br.com.brunohenrique.desafiocartas.dto.*;
import br.com.brunohenrique.desafiocartas.entity.CardEntity;
import br.com.brunohenrique.desafiocartas.entity.DeckEntity;
import br.com.brunohenrique.desafiocartas.entity.MatchEntity;
import br.com.brunohenrique.desafiocartas.entity.PlayerEntity;
import br.com.brunohenrique.desafiocartas.repository.CardRepository;
import br.com.brunohenrique.desafiocartas.repository.DeckRepository;
import br.com.brunohenrique.desafiocartas.repository.MatchRepository;
import br.com.brunohenrique.desafiocartas.repository.PlayerRepository;
import br.com.brunohenrique.desafiocartas.service.ClientFeignDeck;
import br.com.brunohenrique.desafiocartas.utils.CardDTOBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MatchServiceImplTest {

    @Mock
    private MatchRepository matchRepository;
    @Mock
    private ClientFeignDeck clientFeignDeck;
    @Mock
    private DeckRepository deckRepository;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private MatchServiceImpl matchServiceImp;


    @ParameterizedTest
    @ValueSource(strings = {"KING", "QUEEN", "JACK", "ACE", "1"})
    void shouldInsertNewPlayerWhenListIsValid(String cardValue){
        var listPlayers = List.of(new PlayerDTO(1L, "player1", null, null));
        var deckEntity = new DeckEntity();
        var matchEntity = new MatchEntity();
        var playerEntity = new PlayerEntity();
        var cardEntity = new CardEntity();

        var listOfCards = List.of(CardDTOBuilder.aCardDTO().withDefaultValues().withValue(cardValue).build(),
                CardDTOBuilder.aCardDTO().withDefaultValues().withValue(cardValue).build());

        var drawResponseDTO = new DrawResponseDTO(true,"gutcun0ifbg4", listOfCards, 52);

        when(clientFeignDeck.getNewDeck()).thenReturn(new DeckDTO("gutcun0ifbg4"));
        when(deckRepository.save(any(DeckEntity.class))).thenReturn(deckEntity);
        when(matchRepository.save(any(MatchEntity.class))).thenReturn(matchEntity);
        when(playerRepository.findById(any())).thenReturn(Optional.of(playerEntity));
        //doNothing().when(cardRepository.deleteAllByPlayerId(any()));
        when(clientFeignDeck.getCards("gutcun0ifbg4", 5)).thenReturn(drawResponseDTO);
        when(cardRepository.save(any(CardEntity.class))).thenReturn(cardEntity);
        when(playerRepository.save(any())).thenReturn(playerEntity);

        assertThatCode(() -> matchServiceImp.insert(listPlayers)).doesNotThrowAnyException();


    }
}