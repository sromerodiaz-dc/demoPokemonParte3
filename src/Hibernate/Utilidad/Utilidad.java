package Hibernate.Utilidad;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Utilidad {

    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;

    static {
        initializeSessionFactory();
    }

    /**
     * Inicializa la SessionFactory utilizando la configuración de Hibernate.
     */
    private static void initializeSessionFactory() {
        try {
            // Configuración del registro de servicios estándar
            standardServiceRegistry = new StandardServiceRegistryBuilder()
                    .configure("Recursos/Recursos.xml")
                    .build();

            // Construcción de Metadata y SessionFactory
            Metadata metadata = new MetadataSources(standardServiceRegistry)
                    .getMetadataBuilder()
                    .build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (Exception e) {
            handleInitializationError(e);
        }
    }

    /**
     * Maneja los errores de inicialización del SessionFactory.
     *
     * @param exception la excepción capturada durante la inicialización.
     */
    private static void handleInitializationError(Exception exception) {
        System.err.println("Error al inicializar Hibernate SessionFactory: " + exception.getMessage());
        System.out.println(exception.getMessage());

        // Destruir el registro de servicios si ocurre un error
        if (standardServiceRegistry != null) {
            StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
        }
        throw new ExceptionInInitializerError(exception);
    }

    /**
     * Obtiene la SessionFactory configurada.
     *
     * @return la instancia única de SessionFactory.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            throw new IllegalStateException("SessionFactory no está inicializada.");
        }
        return sessionFactory;
    }

    /**
     * Cierra la SessionFactory y libera los recursos.
     */
    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
        if (standardServiceRegistry != null) {
            StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
        }
    }
}
