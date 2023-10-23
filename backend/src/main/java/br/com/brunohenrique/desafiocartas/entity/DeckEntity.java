package br.com.brunohenrique.desafiocartas.entity;

import br.com.brunohenrique.desafiocartas.dto.DeckDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serial;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "deck")
public class DeckEntity extends AbstractBaseEntity {

  @Serial private static final long serialVersionUID = 1709184783624324800L;

  String deckId;

  @OneToOne(mappedBy = "deck")
  private MatchEntity match;

  public DeckDTO toDTO() {
    return new DeckDTO(this.getDeckId());
  }
}
