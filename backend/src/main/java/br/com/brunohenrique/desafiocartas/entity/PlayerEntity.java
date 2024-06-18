package br.com.brunohenrique.desafiocartas.entity;

import br.com.brunohenrique.desafiocartas.dto.PlayerDTO;
import jakarta.persistence.*;
import java.io.Serial;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "player")
@Getter
@Setter
public class  PlayerEntity extends AbstractBaseEntity {

  @Serial private static final long serialVersionUID = 1709184783624324800L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String name;

  @OneToMany(mappedBy = "player")
  private List<CardEntity> cards;

  @ManyToOne private MatchEntity match;

  public PlayerDTO toDTO() {
    if (this.getMatch() == null) {
      return new PlayerDTO(
          this.getId(),
          this.getName(),
          this.getCards().stream().map(CardEntity::toDTO).collect(Collectors.toList()),
          null);
    } else {
      return new PlayerDTO(
          this.getId(),
          this.getName(),
          this.getCards().stream().map(CardEntity::toDTO).collect(Collectors.toList()),
          this.match.toDTO());
    }
  }
}
