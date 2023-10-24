package br.com.brunohenrique.desafiocartas.repository;

import br.com.brunohenrique.desafiocartas.entity.CardEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository
    extends AbstractBaseRepository<CardEntity, Long>, JpaSpecificationExecutor<CardEntity> {

  public void deleteAllByPlayerId(Long playerId);
}
