package br.com.brunohenrique.desafiocartas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Card")
@Getter
@Setter
public class CardEntity extends AbstractBaseEntity{

    private String rank;

    private String suit;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

}
