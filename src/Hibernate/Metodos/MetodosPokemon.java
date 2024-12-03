package Hibernate.Metodos;

import Datos.DatosAdestrador;
import Datos.DatosPokedex;
import Datos.DatosPokemon;
import Hibernate.Utilidad.Utilidad;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.swing.*;

public class MetodosPokemon {

    public void crearPokemon(DatosPokemon pokemon) {
        if (pokemon == null) {
            JOptionPane.showMessageDialog(null, "O pokemon non pode ser null");
            return;
        }

        try (Session session = Utilidad.getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.persist(pokemon);
            transaction.commit();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erro ao crear o pokemon: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public DatosPokedex buscarEntrada(int id) {
        try (Session session = Utilidad.getSessionFactory().openSession())
        {
            return session.get(DatosPokedex.class, id);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erro ao buscar a entrada: " + e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }

    public DatosAdestrador buscarAdestrador(int id) {
        try (Session session = Utilidad.getSessionFactory().openSession())
        {
            return session.get(DatosAdestrador.class, id);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o adestrador: " + e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void eliminarPokemon(int id) {
        try (Session session = Utilidad.getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();
            DatosPokemon pokemon = session.get(DatosPokemon.class, id);
            if (pokemon != null) {
                session.remove(pokemon);
                transaction.commit();
            } else {
                JOptionPane.showMessageDialog(null, "Non se atopou o pokemon con ese id ");
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erro ao eliminar o pokemon: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void modificarPokemon(int id, String nome) {
        try (Session session = Utilidad.getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();
            DatosPokemon pokemon = session.get(DatosPokemon.class, id);
            if (pokemon != null)
            {
                pokemon.setNome(nome);
                session.merge(pokemon);
                transaction.commit();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Non se atopou o pokemon con ese id ");
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erro ao modificar o pokemon: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void listarPokemon() {
        try (Session session = Utilidad.getSessionFactory().openSession())
        {
            session.createQuery("from Datos.DatosPokemon ", DatosPokemon.class).list().forEach(System.out::println);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erro ao listar os pokemons: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}