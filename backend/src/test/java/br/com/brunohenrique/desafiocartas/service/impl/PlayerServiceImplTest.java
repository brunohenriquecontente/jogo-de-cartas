package br.com.brunohenrique.desafiocartas.service.impl;

import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import br.com.brunohenrique.desafiocartas.entity.CardEntity;
import br.com.brunohenrique.desafiocartas.entity.PlayerEntity;
import br.com.brunohenrique.desafiocartas.repository.PlayerRepository;
import br.com.brunohenrique.desafiocartas.utils.PlayerDTOBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

  @Mock private PlayerRepository playerRepository;

  @InjectMocks private PlayerServiceImpl playerServiceImpl;

  @Test
  void shouldInsertNewPlayerWhenHasValidName() {
    var playerEntity = new PlayerEntity();

    PlayerDTO playerDTO = PlayerDTOBuilder.aPlayerDTO().withDefaultValues().build();

    when(playerRepository.save(any(PlayerEntity.class))).thenReturn(playerEntity);

    assertThatCode(() -> playerServiceImpl.insert(playerDTO)).doesNotThrowAnyException();
  }

  @Test
  void shouldReturnPlayerDTOWhenValidIdProvided() {
    PlayerEntity playerEntity = new PlayerEntity();
    playerEntity.setId(1L);
    playerEntity.setName("Player 1");
    var listCards = List.of(new CardEntity(1, "10", "suit", playerEntity));
    playerEntity.setCards(listCards);

    when(playerRepository.findById(1L)).thenReturn(Optional.of(playerEntity));

    PlayerDTO result = playerServiceImpl.getById(1L);

    assertNotNull(result);

    assertEquals("1", result.id(), playerEntity.getId());
    assertEquals("Player 1", result.name(), playerEntity.getName());
  }

  @Test
  void shouldDeleteAValidPlayer() {
    doNothing().when(playerRepository).deleteById(1L);

    playerServiceImpl.deleteById(1L);

    verify(playerRepository).deleteById(1L);
  }
}
