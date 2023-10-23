package br.com.brunohenrique.desafiocartas.service;

import br.com.brunohenrique.desafiocartas.dto.DeckDTO;
import br.com.brunohenrique.desafiocartas.entity.DeckEntity;

public interface DeckService extends AbstractBaseService<DeckEntity, Long> {

    public DeckDTO insert();
}
