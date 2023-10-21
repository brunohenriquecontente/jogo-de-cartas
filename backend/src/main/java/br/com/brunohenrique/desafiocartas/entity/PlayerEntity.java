package br.com.brunohenrique.desafiocartas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Entity
@Table(name = "Player")
@Getter
@Setter
public class PlayerEntity extends AbstractBaseEntity {

    @Serial
    private static final long serialVersionUID = 1709184783624324800L;

    private String name;
}
