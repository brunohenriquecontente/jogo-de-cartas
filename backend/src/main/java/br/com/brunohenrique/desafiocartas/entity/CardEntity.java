package br.com.brunohenrique.desafiocartas.entity;

import br.com.brunohenrique.desafiocartas.dto.CardDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "card")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardEntity extends AbstractBaseEntity {

  private Integer rank;

  private String code;

  private String suit;

  @ManyToOne
  @JoinColumn(name = "player_id")
  private PlayerEntity player;

  public CardDTO toDTO() {
    String rankValue =
        switch (this.rank) {
          case 13 -> "KING";
          case 12 -> "QUEEN";
          case 11 -> "JACK";
          case 1 -> "ACE";
          default -> String.valueOf(this.rank);
        };
    return new CardDTO(this.getId(), this.getCode(), rankValue, this.getSuit());
  }
}
