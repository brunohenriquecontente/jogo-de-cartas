package br.com.brunohenrique.desafiocartas.utils;

import br.com.brunohenrique.desafiocartas.dto.DeckDTO;
import br.com.brunohenrique.desafiocartas.dto.MatchDTO;

public final class DeckDTOBuilder {
  private Long id;
  private String deckId;
  private MatchDTO match;

  private DeckDTOBuilder() {}

  public static DeckDTOBuilder aDeckDTO() {
    return new DeckDTOBuilder();
  }

  public DeckDTOBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public DeckDTOBuilder withDeckId(String deckId) {
    this.deckId = deckId;
    return this;
  }

  public DeckDTOBuilder withMatch(MatchDTO match) {
    this.match = match;
    return this;
  }

  public DeckDTO build() {
    return new DeckDTO(deckId);
  }

  public DeckDTOBuilder withDefaultValues() {
    this.id = 1L;
    this.deckId = "gutcun0ifbg4";
    this.match = new MatchDTO(1L, null, null, null);
    return this;
  }
}
