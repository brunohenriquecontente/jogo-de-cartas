package br.com.brunohenrique.desafiocartas.repository;

import br.com.brunohenrique.desafiocartas.entity.CardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<CardEntity, Long> {

  public void deleteAllByPlayerId(Long playerId);
}
