package br.com.brunohenrique.desafiocartas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serial;
import java.util.List;

@Entity
@Table(name = "player")
@Getter
@Setter
public class PlayerEntity extends AbstractBaseEntity {

    @Serial
    private static final long serialVersionUID = 1709184783624324800L;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "player")
    private List<CardEntity> cards;
}
