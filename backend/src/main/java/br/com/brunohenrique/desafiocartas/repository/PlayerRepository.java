package br.com.brunohenrique.desafiocartas.repository;

import br.com.brunohenrique.desafiocartas.entity.PlayerEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository
    extends AbstractBaseRepository<PlayerEntity, Long>, JpaSpecificationExecutor<PlayerEntity> {

  List<PlayerEntity> findAllByMatchId(Long matchId);
}
