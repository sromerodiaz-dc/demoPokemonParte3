package Hibernate.Metodos;

import Datos.DatosAdestrador;
import Datos.Wrapper;
import Hibernate.Utilidad.Utilidad;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.File;
import java.util.Date;
import java.util.List;

public class MetodosAdestrador {

    public void crearAdestrador(DatosAdestrador adestrador) {
        if (adestrador == null) {
            System.out.println("O adestrador non pode ser null");
            return;
        }

        try (Session session = Utilidad.getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.persist(adestrador);
            transaction.commit();
        }
        catch (Exception e)
        {
            System.out.println("Erro ao crear o adestrador: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void eliminarAdestrador(int id) {
        try (Session session = Utilidad.getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();
            DatosAdestrador adestrador = session.get(DatosAdestrador.class, id);

            if (adestrador != null)
            {
                session.remove(adestrador);
                transaction.commit();
            }
            else
            {
                System.out.println("Non se atopou o adestrador con ese id ");
            }
        }
        catch (Exception e)
        {
            System.out.println("Erro ao eliminar o adestrador: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void modificarAdestrador(int id, String nome, Date nacemento) {
        try (Session session = Utilidad.getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();
            DatosAdestrador adestrador = session.get(DatosAdestrador.class, id);

            if (adestrador != null)
            {
                adestrador.setNome(nome);
                adestrador.setNacemento(nacemento);
                session.merge(adestrador);
                transaction.commit();
            }
            else
            {
                System.out.println("Non se atopou o adestrador con ese id ");
            }
        }
        catch (Exception e)
        {
            System.out.println("Erro ao modificar o adestrador: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    // Método para listar todos los adestradores
    public void listarAdestradores() {
        try (Session session = Utilidad.getSessionFactory().openSession()) {
            List<DatosAdestrador> adestradores = session.createQuery("from DatosAdestrador", DatosAdestrador.class).list();
            // Mostrar los resultados por consola
            adestradores.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Erro ao listar os adestradores: " + e.getMessage());
        }
    }

    // Método para exportar adestradores a un archivo XML
    public void exportarAdestradoresXML() {
        try (Session session = Utilidad.getSessionFactory().openSession())
        {
            List<DatosAdestrador> adestradorList = session.createQuery("FROM DatosAdestrador", DatosAdestrador.class).list();

            try
            {
                // Crear el contexto de JAXB
                JAXBContext context = JAXBContext.newInstance(Wrapper.class);
                Marshaller marshaller = context.createMarshaller();
                Wrapper wrapper = new Wrapper();

                // Formato de salida bonito
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                // Envolvemos la lista de adestradores en un wrapper
                wrapper.setAdestradores(adestradorList);

                // Guardamos el resultado en un archivo XML
                marshaller.marshal(wrapper, new File("adestradores.xml"));
                System.out.println("Adestradores exportados correctamente a adestradores.xml");
            }
            catch (JAXBException e)
            {
                System.out.println("Erro ao exportar a XML: " + e.getMessage());
            }
        }
        catch (Exception e)
        {
            System.out.println("Erro ao listar os adestradores: " + e.getMessage());
        }
    }

    // Método para importar adestradores desde un archivo XML
    public void importarAdestradoresXML() {
        try {
            // Crear el contexto de JAXB
            JAXBContext context = JAXBContext.newInstance(Wrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Deserializamos el XML en un objeto Wrapper
            Wrapper wrapper = (Wrapper) unmarshaller.unmarshal(new File("adestradores.xml"));

            List<DatosAdestrador> adestradorList = wrapper.getAdestradores();

            try (Session session = Utilidad.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();

                // Guardamos los adestradores importados en la base de datos
                for (DatosAdestrador adestrador : adestradorList) {
                    session.merge(adestrador);  // Usamos merge para evitar duplicados
                }
                tx.commit();
                System.out.println("Adestradores importados correctamente.");
            } catch (Exception e) {
                System.out.println("Erro ao importar adestradores: " + e.getMessage());
            }
        } catch (JAXBException e) {
            System.out.println("Erro ao importar o arquivo XML: " + e.getMessage());
        }
    }
}
