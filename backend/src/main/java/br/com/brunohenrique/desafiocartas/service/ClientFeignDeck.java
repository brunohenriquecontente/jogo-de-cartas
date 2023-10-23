package br.com.brunohenrique.desafiocartas.service;

import br.com.brunohenrique.desafiocartas.dto.DeckDTO;
import br.com.brunohenrique.desafiocartas.dto.DrawResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url="${deck.card.api.url}", name="deck-api")
public interface ClientFeignDeck {

    @GetMapping("new/shuffle/?deck_count=1")
    DeckDTO getNewDeck();

    @GetMapping("{deck_id}/draw/?count={count}")
    DrawResponseDTO getCards(@PathVariable String deck_id, @PathVariable Integer count);
}