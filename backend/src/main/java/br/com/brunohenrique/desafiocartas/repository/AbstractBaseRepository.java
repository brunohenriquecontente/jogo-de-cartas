package br.com.brunohenrique.desafiocartas.repository;

import br.com.brunohenrique.desafiocartas.entity.AbstractBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 
 * AbstractBaseRepository interface, responsible for exposing database
 * persistence functionalities. <br>
 * <br>
 * 
 * Interface AbstractBaseRepository, é responsável por expor as funcionalidades
 * de persistência de dados.
 * 
 * @author brunohenriquecontente
 *
 * @param <T>  -
 *             {@link AbstractBaseEntity AbstractBaseEntity}
 * @param <ID>
 */
@NoRepositoryBean
public interface AbstractBaseRepository<T extends AbstractBaseEntity, ID extends Serializable>
		extends JpaRepository<T, ID> {

}
