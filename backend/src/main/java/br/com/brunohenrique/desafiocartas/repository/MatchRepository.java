package br.com.brunohenrique.desafiocartas.repository;

import br.com.brunohenrique.desafiocartas.entity.MatchEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface MatchRepository extends AbstractBaseRepository<MatchEntity, Long>,
        JpaSpecificationExecutor<MatchEntity> {

}
