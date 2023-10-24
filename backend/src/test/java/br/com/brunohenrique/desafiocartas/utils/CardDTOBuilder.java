package br.com.brunohenrique.desafiocartas.utils;

import br.com.brunohenrique.desafiocartas.dto.CardDTO;

public final class CardDTOBuilder {
  private Long id;
  private String code;
  private String value;
  private String suit;

  private CardDTOBuilder() {}

  public static CardDTOBuilder aCardDTO() {
    return new CardDTOBuilder();
  }

  public CardDTOBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public CardDTOBuilder withCode(String code) {
    this.code = code;
    return this;
  }

  public CardDTOBuilder withValue(String value) {
    this.value = value;
    return this;
  }

  public CardDTOBuilder withSuit(String suit) {
    this.suit = suit;
    return this;
  }

  public CardDTO build() {
    return new CardDTO(id, code, value, suit);
  }

  public CardDTOBuilder withDefaultValues() {
    this.id = 1L;
    this.code = "code";
    this.suit = "suit";
    this.value = "JACK";
    return this;
  }
}
