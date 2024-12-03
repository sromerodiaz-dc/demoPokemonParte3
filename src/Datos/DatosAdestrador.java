package Datos;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Date;

@Entity
@Table(name = "adestrador")
@XmlRootElement
public class DatosAdestrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "nacemento")
    private Date nacemento;

    // Constructors, getters, setters, and toString() method
    public DatosAdestrador() {

    }

    public DatosAdestrador(String nome, Date nacemento) {
        this.nome = nome;
        this.nacemento = nacemento;
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

    @Override
    public String toString() {
        return "Adestrador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nacemento=" + nacemento +
                '}';
    }
}
