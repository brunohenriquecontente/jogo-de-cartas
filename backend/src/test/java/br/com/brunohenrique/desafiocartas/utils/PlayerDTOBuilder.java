package br.com.brunohenrique.desafiocartas.utils;

import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;

public final class PlayerDTOBuilder {
  private Long id;
  private String name;

  private PlayerDTOBuilder() {}

  public static PlayerDTOBuilder aPlayerDTO() {
    return new PlayerDTOBuilder();
  }

  public PlayerDTOBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public PlayerDTOBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public PlayerDTO build() {
    return new PlayerDTO(id, name, null, null);
  }

  public PlayerDTOBuilder withDefaultValues() {
    this.id = 1L;
    this.name = "Player 1";
    return this;
  }
}
