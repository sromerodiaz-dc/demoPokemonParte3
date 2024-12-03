package Hibernate.Metodos;

import Datos.DatosPokedex;
import Hibernate.Utilidad.Utilidad;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.*;
import java.util.List;


public class MetodosPokedex {

    public void crearEntrada(DatosPokedex pokedex) {
        if (pokedex == null)
        {
            System.out.println("No se puede crear una entrada vacía");
            return;
        }

        try (Session session = Utilidad.getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.persist(pokedex);
            transaction.commit();
        }
        catch (Exception e)
        {
            System.out.println("Error al crear la entrada: " + e.getMessage());
            System.out.println(e.getMessage());
        }

    }

    public void eliminarEntrada(int id) {
        try (Session session = Utilidad.getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();
            DatosPokedex pokedex = session.get(DatosPokedex.class, id);

            if (pokedex != null)
            {
                session.remove(pokedex);
                transaction.commit();
            }
            else
            {
                System.out.println("No se encontró la entrada con ese id");
            }
        }
        catch (Exception e)
        {
            System.out.println("Error al eliminar la entrada: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void modificarEntrada(int id, String nome) {
        try (Session session = Utilidad.getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();
            DatosPokedex pokedex = session.get(DatosPokedex.class, id);

            if (pokedex != null)
            {
                pokedex.setNome(nome);
                session.merge(pokedex);
                transaction.commit();
            }
            else
            {
                System.out.println("No se encontró la entrada con ese id");
            }
        }
        catch (Exception e)
        {
            System.out.println("Error al modificar la entrada: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void listarEntradas() {
        try (Session session = Utilidad.getSessionFactory().openSession())
        {
            session.createQuery("from Datos.DatosPokedex ", DatosPokedex.class).list().forEach(System.out::println);
        }
        catch (Exception e)
        {
            System.out.println("Error al listar las entradas: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void exportarPokedex() {
        try (Session session = Utilidad.getSessionFactory().openSession())
        {
            List<DatosPokedex> pokedexList = session.createQuery("FROM DatosPokedex", DatosPokedex.class)
                    .setMaxResults(2)
                    .list();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pokedex.dat")))
            {
                oos.writeObject(pokedexList);
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void importarPokedex() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pokedex.dat")))
        {
            @SuppressWarnings("unchecked")
            List<DatosPokedex> pokedexList = (List<DatosPokedex>) ois.readObject();

            try (Session session = Utilidad.getSessionFactory().openSession())
            {
                Transaction tx = session.beginTransaction();

                for (DatosPokedex pokedex : pokedexList) {
                    session.merge(pokedex);
                }
                tx.commit();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
