package br.com.brunohenrique.desafiocartas.entity;

import br.com.brunohenrique.desafiocartas.dto.MatchDTO;
import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "match")
@NoArgsConstructor
public class MatchEntity extends AbstractBaseEntity {

  @Serial private static final long serialVersionUID = -3678957801372784015L;

  private String winner;

  @OneToOne private DeckEntity deck;

  @OneToMany(mappedBy = "match")
  private List<PlayerEntity> players;

  public MatchEntity(DeckEntity deck) {
    this.deck = deck;
  }

  public MatchDTO toDTO() {
    List<PlayerDTO> playerDTOs = this.getPlayers().stream().map(PlayerEntity::toDTO).toList();

    return new MatchDTO(this.getId(), this.getWinner(), this.getDeck().toDTO(), playerDTOs);
  }
}
