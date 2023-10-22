package br.com.brunohenrique.desafiocartas.service;

import br.com.brunohenrique.desafiocartas.dto.DeckDTO;
import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import br.com.brunohenrique.desafiocartas.entity.DeckEntity;
import br.com.brunohenrique.desafiocartas.entity.PlayerEntity;
import br.com.brunohenrique.desafiocartas.repository.AbstractBaseRepositoryImpl;
import br.com.brunohenrique.desafiocartas.repository.DeckRepository;
import br.com.brunohenrique.desafiocartas.repository.PlayerRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DeckServiceImpl extends AbstractBaseRepositoryImpl<DeckEntity, Long> implements DeckService {

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private ClientFeignDeck clientFeignDeck;

    public DeckServiceImpl(DeckRepository deckRepository) {
        super(deckRepository);
    }

    @Override
    public DeckDTO insert() {
        DeckDTO deckDTO = clientFeignDeck.getNewDeck();
        DeckEntity deckEntity = new DeckEntity();
        BeanUtils.copyProperties(deckDTO, deckEntity);
        deckRepository.save(deckEntity);
        return deckDTO;
    }
}