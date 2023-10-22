package br.com.brunohenrique.desafiocartas.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Mapped base entity for entities.
 *
 */
@MappedSuperclass
public class AbstractBaseEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = -6556453805270108437L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



}
