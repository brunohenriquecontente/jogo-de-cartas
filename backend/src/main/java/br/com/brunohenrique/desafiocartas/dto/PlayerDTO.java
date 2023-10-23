package br.com.brunohenrique.desafiocartas.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public record PlayerDTO(
    Long id, String name, @JsonIgnore List<CardDTO> cards, @JsonIgnore MatchDTO matchDTO) {}
