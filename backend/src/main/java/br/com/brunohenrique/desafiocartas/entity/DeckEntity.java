package br.com.brunohenrique.desafiocartas.entity;

import br.com.brunohenrique.desafiocartas.dto.DeckDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@Entity
@Getter
@Setter
@Table(name = "deck")
@AllArgsConstructor
@NoArgsConstructor
public class DeckEntity extends AbstractBaseEntity {

  @Serial private static final long serialVersionUID = 1709184783624324800L;

  String deckId;

  @OneToOne(mappedBy = "deck")
  private MatchEntity match;

  public DeckDTO toDTO() {
    return new DeckDTO(this.getDeckId());
  }
}
