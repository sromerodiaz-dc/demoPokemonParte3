package Datos;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "pokemon")
public class DatosPokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nacemento")
    private Date nacemento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokedexentry", referencedColumnName = "id")
    private DatosPokedex pokedexentry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adestrador", referencedColumnName = "id")
    private DatosAdestrador adestrador;

    // Constructors, getters, setters, and toString() method
    public DatosPokemon() {}

    public DatosPokemon(String nome, Date nacemento, DatosPokedex pokedexentry, DatosAdestrador adestrador) {
        this.nome = nome;
        this.nacemento = nacemento;
        this.pokedexentry = pokedexentry;
        this.adestrador = adestrador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNacemento() {
        return nacemento;
    }

    public void setNacemento(Date nacemento) {
        this.nacemento = nacemento;
    }

    public DatosPokedex getPokedexentry() {
        return pokedexentry;
    }

    public void setPokedexentry(DatosPokedex pokedexentry) {
        this.pokedexentry = pokedexentry;
    }

    public DatosAdestrador getAdestrador() {
        return adestrador;
    }

    public void setAdestrador(DatosAdestrador adestrador) {
        this.adestrador = adestrador;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nacemento=" + nacemento +
                ", pokedexentry=" + pokedexentry +
                ", adestrador=" + adestrador +
                '}';
    }
}
