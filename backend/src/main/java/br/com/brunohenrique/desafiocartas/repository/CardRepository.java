package br.com.brunohenrique.desafiocartas.repository;

import br.com.brunohenrique.desafiocartas.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CardRepository extends AbstractBaseRepository<CardEntity, Long>,
        JpaSpecificationExecutor<CardEntity> {

    public List<CardEntity> deleteAllByPlayerId(Long playerId);
}
