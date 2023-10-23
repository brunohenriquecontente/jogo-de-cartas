package br.com.brunohenrique.desafiocartas.repository;

import br.com.brunohenrique.desafiocartas.entity.DeckEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface DeckRepository extends AbstractBaseRepository<DeckEntity, Long>,
        JpaSpecificationExecutor<DeckEntity> {


}
