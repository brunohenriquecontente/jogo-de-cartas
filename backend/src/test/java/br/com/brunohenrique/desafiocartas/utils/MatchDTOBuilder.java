package br.com.brunohenrique.desafiocartas.utils;

import br.com.brunohenrique.desafiocartas.dto.DeckDTO;
import br.com.brunohenrique.desafiocartas.dto.MatchDTO;
import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import java.util.List;

public final class MatchDTOBuilder {
  private Long id;
  private String winner;
  private DeckDTO deck;
  private List<PlayerDTO> players;

  private MatchDTOBuilder() {}

  public static MatchDTOBuilder aMatchDTO() {
    return new MatchDTOBuilder();
  }

  public MatchDTOBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public MatchDTOBuilder withWinner(String winner) {
    this.winner = winner;
    return this;
  }

  public MatchDTOBuilder withDeck(DeckDTO deck) {
    this.deck = deck;
    return this;
  }

  public MatchDTOBuilder withPlayers(List<PlayerDTO> players) {
    this.players = players;
    return this;
  }

  public MatchDTO build() {
    return new MatchDTO(id, winner, deck, players);
  }

  public MatchDTOBuilder withDefaultValues() {
    this.id = 1L;
    this.deck = new DeckDTO("gutcun0ifbg4");
    this.winner = null;
    this.players = List.of(PlayerDTOBuilder.aPlayerDTO().withDefaultValues().build());
    return this;
  }
}
