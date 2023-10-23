package br.com.brunohenrique.desafiocartas.entity;

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

}
