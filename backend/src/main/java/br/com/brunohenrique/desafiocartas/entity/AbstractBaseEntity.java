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
	private static final long serialVersionUID = 5471728425287213854L;

	@Id
	@GeneratedValue
	private Long id;

}
