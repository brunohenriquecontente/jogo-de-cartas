package br.com.brunohenrique.desafiocartas.entity;

import br.com.brunohenrique.desafiocartas.dto.CardDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "card")
@Getter
@Setter
public class CardEntity extends AbstractBaseEntity{

    private Integer rank;

    private String code;

    private String suit;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

    public CardDTO toDTO() {
        String rank;
        switch (this.rank) {
            case 13:
                rank = "KING";
                break;
            case 12:
                rank = "QUEEN";
                break;
            case 11:
                rank = "JACK";
                break;
            case 1:
                rank = "ACE";
                break;
            default:
                rank = String.valueOf(this.rank);
        }
        return new CardDTO(
                this.getId(),
                this.getCode(),
                rank,
                this.getSuit()
        );
    }
}
