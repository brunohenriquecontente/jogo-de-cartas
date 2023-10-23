package br.com.brunohenrique.desafiocartas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record DrawResponseDTO(
    boolean success, @JsonProperty("deck_id") String deckId, List<CardDTO> cards, int remaining) {}
