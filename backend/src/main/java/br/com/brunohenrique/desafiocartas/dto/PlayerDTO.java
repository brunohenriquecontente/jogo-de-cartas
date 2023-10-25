package br.com.brunohenrique.desafiocartas.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record PlayerDTO(
    Long id,
    @NotBlank String name,
    @JsonIgnore List<CardDTO> cards,
    @JsonIgnore MatchDTO matchDTO) {}
