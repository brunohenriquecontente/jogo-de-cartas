package br.com.brunohenrique.desafiocartas.service;

import br.com.brunohenrique.desafiocartas.dto.DeckDTO;
import br.com.brunohenrique.desafiocartas.entity.CardEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(url="${deck.card.api.url}", name="deck-api")
public interface ClientFeignDeck {

    @PostMapping("new/shuffle/?deck_count=1")
    DeckDTO getNewDeck();

  /*  @GetMapping("{deckId}/shuffle/")
    void reshuffleCards(@PathVariable String deckId);

    @GetMapping("{deck_id}/draw/?count={count}")
    List<CardEntity> drawCards(@PathVariable String deckId, Integer count);*/
}