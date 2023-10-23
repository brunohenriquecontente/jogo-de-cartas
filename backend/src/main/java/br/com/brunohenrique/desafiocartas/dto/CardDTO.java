package br.com.brunohenrique.desafiocartas.dto;

public record CardDTO(
        Long id,
        String code,
        String value,
        String suit
) {}

