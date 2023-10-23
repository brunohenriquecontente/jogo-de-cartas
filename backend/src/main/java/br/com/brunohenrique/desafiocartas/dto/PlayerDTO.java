package br.com.brunohenrique.desafiocartas.dto;

import br.com.brunohenrique.desafiocartas.entity.CardEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public record PlayerDTO(Long id, String name, @JsonIgnore List<CardEntity> cards) {

}
