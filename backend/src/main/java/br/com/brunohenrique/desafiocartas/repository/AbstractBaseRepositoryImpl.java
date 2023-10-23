package br.com.brunohenrique.desafiocartas.repository;

import br.com.brunohenrique.desafiocartas.entity.AbstractBaseEntity;
import br.com.brunohenrique.desafiocartas.service.AbstractBaseService;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Classe abastrata e genérica AbstractBaseRepositoryImpl que expõe a implementação das
 * funcionalidadades comuns de CRUD (create, recover, update, delete) do banco de dados.
 *
 * @author brunohenriquecontente
 * @version 0.0.1
 * @param <T> - Classe do tipo {@link @Entity}
 * @param <ID> - Identificador único - {@link java.util.UUID}
 */
@Service
@Transactional
public abstract class AbstractBaseRepositoryImpl<
        T extends AbstractBaseEntity, ID extends Serializable>
    implements AbstractBaseService<T, ID> {

  private AbstractBaseRepository<T, ID> abstractBaseRepository;

  /**
   * Construtor da classe AbstractBaseRepositoryImpl
   *
   * @param abstractBaseRepository
   */
  @Autowired
  public AbstractBaseRepositoryImpl(AbstractBaseRepository<T, ID> abstractBaseRepository) {
    this.abstractBaseRepository = abstractBaseRepository;
  }

  @Override
  @Transactional
  public List<T> findAll() {
    return abstractBaseRepository.findAll();
  }

  @Override
  public T update(T entity) {
    return (T) abstractBaseRepository.save(entity);
  }

  @Override
  public T updateById(T entity, ID id) {
    Optional<T> optional = abstractBaseRepository.findById(id);
    if (optional.isPresent()) {
      return (T) abstractBaseRepository.save(entity);
    } else {
      return null;
    }
  }

  @Override
  @Transactional
  public Optional<T> findById(ID id) {
    return abstractBaseRepository.findById(id);
  }

  @Override
  public void delete(T entity) {
    abstractBaseRepository.delete(entity);
  }

  @Override
  public void delete(ID id) {
    abstractBaseRepository.deleteById(id);
  }

  @Override
  public T insert(T entity) {
    return (T) abstractBaseRepository.save(entity);
  }

  @Override
  @Transactional
  public boolean exists(ID id) {
    return abstractBaseRepository.existsById(id);
  }

  /**
   * Return the searching items organized by pages.
   *
   * @param index - desired page
   * @param offSet - quantity per pages
   * @return Page<T>
   */
  @Transactional
  public Page<T> findAllPaged(Integer index, Integer offSet) {
    Pageable pageable = PageRequest.of(index, offSet);
    return abstractBaseRepository.findAll(pageable);
  }
}
