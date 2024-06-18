package br.com.brunohenrique.desafiocartas.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public record MatchDTO(Long id, String winner, DeckDTO deck, @JsonIgnore List<PlayerDTO> players) {}
