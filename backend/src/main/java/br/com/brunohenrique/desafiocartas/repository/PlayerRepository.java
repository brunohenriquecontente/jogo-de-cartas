package br.com.brunohenrique.desafiocartas.repository;

import br.com.brunohenrique.desafiocartas.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;



@Repository
public interface PlayerRepository extends AbstractBaseRepository<PlayerEntity, UUID>,
        JpaSpecificationExecutor<PlayerEntity> {

}
