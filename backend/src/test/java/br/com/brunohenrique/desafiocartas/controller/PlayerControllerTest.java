package br.com.brunohenrique.desafiocartas.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import br.com.brunohenrique.desafiocartas.service.PlayerService;
import br.com.brunohenrique.desafiocartas.utils.PlayerDTOBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private PlayerService playerService;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void shouldCreatePlayer() throws Exception {
    PlayerDTO requestPlayerDTO = PlayerDTOBuilder.aPlayerDTO().withDefaultValues().build();
    PlayerDTO mockPlayerDTO = PlayerDTOBuilder.aPlayerDTO().withDefaultValues().build();

    given(playerService.insert(any(PlayerDTO.class))).willReturn(mockPlayerDTO);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/player")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestPlayerDTO)))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(
            MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(mockPlayerDTO)));
  }

  @Test
  void testGetPlayerById() throws Exception {
    Long playerId = 1L;
    PlayerDTO mockPlayerDTO = PlayerDTOBuilder.aPlayerDTO().withDefaultValues().build();

    given(playerService.getById(playerId)).willReturn(mockPlayerDTO);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/player/{playerId}", playerId)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("$.id").value(mockPlayerDTO.id()))
        .andExpect(jsonPath("$.name").value(mockPlayerDTO.name()));
  }

  @Test
  void testDeletePlayerById() throws Exception {
    Long playerId = 1L;

    Mockito.doNothing().when(playerService).deleteById(playerId);

    mockMvc
        .perform(
            MockMvcRequestBuilders.delete("/player/{playerId}", playerId)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  /*  @Test
  void shouldUpdatePlayerById() throws Exception {
    Long playerId = 1L;
    PlayerDTO playerDTO = PlayerDTOBuilder.aPlayerDTO().withDefaultValues().build();

    Mockito.when(playerService.updateById(playerId, playerDTO)).thenReturn(playerDTO);

    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/player/{playerId}", playerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(playerDTO.toString()))
        .andExpect(MockMvcResultMatchers.status().isAccepted());
  }*/
}
