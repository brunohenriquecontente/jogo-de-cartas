package br.com.brunohenrique.desafiocartas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Entity
@Getter
@Setter
@Table(name="deck")
public class DeckEntity extends AbstractBaseEntity  {

    @Serial
    private static final long serialVersionUID = 1709184783624324800L;

    String deckId;
}
