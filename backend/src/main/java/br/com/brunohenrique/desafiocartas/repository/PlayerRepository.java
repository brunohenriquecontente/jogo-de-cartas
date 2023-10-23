package br.com.brunohenrique.desafiocartas.repository;

import br.com.brunohenrique.desafiocartas.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends AbstractBaseRepository<PlayerEntity, Long>,
        JpaSpecificationExecutor<PlayerEntity> {

        List<PlayerEntity> findAllByMatchId(Long matchId);
}
