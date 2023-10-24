package br.com.brunohenrique.desafiocartas.service.impl;

import br.com.brunohenrique.desafiocartas.repository.DeckRepository;
import br.com.brunohenrique.desafiocartas.service.ClientFeignDeck;
import br.com.brunohenrique.desafiocartas.service.DeckService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeckServiceImpl implements DeckService {

  private final DeckRepository deckRepository;

  private final ClientFeignDeck clientFeignDeck;

  public DeckServiceImpl(DeckRepository deckRepository, ClientFeignDeck clientFeignDeck) {
    this.deckRepository = deckRepository;
    this.clientFeignDeck = clientFeignDeck;
  }
}
