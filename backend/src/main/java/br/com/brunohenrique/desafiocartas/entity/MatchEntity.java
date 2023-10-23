package br.com.brunohenrique.desafiocartas.entity;

import br.com.brunohenrique.desafiocartas.dto.MatchDTO;
import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serial;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "match")
public class MatchEntity extends AbstractBaseEntity {

  @Serial private static final long serialVersionUID = -3678957801372784015L;

  String winner;

  @OneToOne private DeckEntity deck;

  @OneToMany(mappedBy = "match")
  private List<PlayerEntity> players;

  public MatchDTO toDTO() {
    List<PlayerDTO> playerDTOs = this.getPlayers().stream().map(PlayerEntity::toDTO).toList();

    return new MatchDTO(this.getId(), this.getWinner(), this.getDeck().toDTO(), playerDTOs);
  }
}
