package br.com.brunohenrique.desafiocartas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DeckDTO(@JsonProperty("deck_id") String deckId) {}
