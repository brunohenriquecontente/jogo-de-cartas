package br.com.brunohenrique.desafiocartas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="match")
public class MatchEntity extends AbstractBaseEntity  {

    @Serial
    private static final long serialVersionUID = -3678957801372784015L;

    String winner;

    @OneToOne
    private DeckEntity deck;

    @OneToMany(mappedBy = "match")
    private List<PlayerEntity> players;
}
