package br.com.brunohenrique.desafiocartas.service;

import br.com.brunohenrique.desafiocartas.entity.DeckEntity;
import br.com.brunohenrique.desafiocartas.repository.AbstractBaseRepositoryImpl;
import br.com.brunohenrique.desafiocartas.repository.DeckRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

   }