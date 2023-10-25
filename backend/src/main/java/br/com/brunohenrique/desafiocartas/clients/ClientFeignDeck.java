package br.com.brunohenrique.desafiocartas.clients;

import br.com.brunohenrique.desafiocartas.dto.DeckDTO;
import br.com.brunohenrique.desafiocartas.dto.DrawResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${deck.card.api.url}", name = "deck-api")
public interface ClientFeignDeck {

  @GetMapping("new/shuffle/?deck_count=1")
  DeckDTO getNewDeck();

  @GetMapping("{deckId}/draw/?count={count}")
  DrawResponseDTO getCards(@PathVariable String deckId, @PathVariable Integer count);
}
