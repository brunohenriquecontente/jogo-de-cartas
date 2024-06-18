package br.com.brunohenrique.desafiocartas.controller;

import static org.mockito.BDDMockito.given;

import br.com.brunohenrique.desafiocartas.dto.DeckDTO;
import br.com.brunohenrique.desafiocartas.dto.MatchDTO;
import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import br.com.brunohenrique.desafiocartas.service.MatchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(MatchController.class)
class MatchControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private MatchService matchService;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void shouldInsertMatch() throws Exception {
    List<PlayerDTO> players =
        List.of(new PlayerDTO(1L, "Player1", null, null), new PlayerDTO(2L, "Player2", null, null));

    MatchDTO mockMatchDTO = new MatchDTO(1L, "Winner", new DeckDTO("deckId"), null);
    given(matchService.insert(players)).willReturn(mockMatchDTO);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/match")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(players)))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(
            MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(mockMatchDTO)));
  }

  @Test
  void testGetWinnerMatch() throws Exception {
    Long matchId = 1L;

    MatchDTO mockMatchDTO = new MatchDTO(1L, "Winner", new DeckDTO("deckId"), null);
    given(matchService.getWinner(matchId)).willReturn(mockMatchDTO);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/match/{matchId}", matchId)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(mockMatchDTO)));
  }
}
