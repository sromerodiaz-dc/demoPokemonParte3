import Datos.DatosAdestrador;
import Datos.DatosPokedex;
import Datos.DatosPokemon;
import Hibernate.Metodos.MetodosAdestrador;
import Hibernate.Metodos.MetodosPokedex;
import Hibernate.Metodos.MetodosPokemon;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {

        MetodosAdestrador metodosAdestrador = new MetodosAdestrador();
        MetodosPokedex metodosPokedex = new MetodosPokedex();
        MetodosPokemon metodosPokemon = new MetodosPokemon();

        metodosAdestrador.crearAdestrador(new DatosAdestrador("Pepe", toSqlDate(LocalDate.of(1990, Month.JUNE, 5))));
        metodosAdestrador.crearAdestrador(new DatosAdestrador("Manolo", toSqlDate(LocalDate.of(1995, Month.JULY, 6))));

        metodosPokedex.crearEntrada(new DatosPokedex("Pikachu", 6.0, "Electrico"));
        metodosPokedex.crearEntrada(new DatosPokedex("Charmander", 8.5, "Fuego"));
        metodosPokedex.crearEntrada(new DatosPokedex("Squirtle", 9.0, "Agua"));
        metodosPokedex.crearEntrada(new DatosPokedex("Bulbasaur", 7.0, "Planta"));
        metodosPokedex.crearEntrada(new DatosPokedex("Jigglypuff", 5.5, "Normal"));
        metodosPokedex.crearEntrada(new DatosPokedex("Meowth", 4.5, "Normal"));
        metodosPokedex.crearEntrada(new DatosPokedex("Psyduck", 6.5, "Agua"));
        metodosPokedex.crearEntrada(new DatosPokedex("Geodude", 8.0, "Roca"));
        metodosPokedex.crearEntrada(new DatosPokedex("Gastly", 6.0, "Fantasma"));
        metodosPokedex.crearEntrada(new DatosPokedex("Onix", 10.0, "Roca"));
        metodosPokedex.crearEntrada(new DatosPokedex("Drowzee", 7.0, "Psiquico"));
        metodosPokedex.crearEntrada(new DatosPokedex("Krabby", 6.5, "Agua"));


        // Crear pokemons usando LocalDate y convertirlo a java.sql.Date
        metodosPokemon.crearPokemon(new DatosPokemon("Pikachu", toSqlDate(LocalDate.of(1990, 6, 5)), metodosPokemon.buscarEntrada(1), metodosPokemon.buscarAdestrador(1)));
        metodosPokemon.crearPokemon(new DatosPokemon("Charmander", toSqlDate(LocalDate.of(1995, 7, 6)), metodosPokemon.buscarEntrada(2), metodosPokemon.buscarAdestrador(1)));
        metodosPokemon.crearPokemon(new DatosPokemon("Squirtle", toSqlDate(LocalDate.of(1996, 8, 7)), metodosPokemon.buscarEntrada(3), metodosPokemon.buscarAdestrador(1)));
        metodosPokemon.crearPokemon(new DatosPokemon("Bulbasaur", toSqlDate(LocalDate.of(1997, 9, 8)), metodosPokemon.buscarEntrada(4), metodosPokemon.buscarAdestrador(1)));
        metodosPokemon.crearPokemon(new DatosPokemon("Jigglypuff", toSqlDate(LocalDate.of(1998, 10, 9)), metodosPokemon.buscarEntrada(5), metodosPokemon.buscarAdestrador(1)));
        metodosPokemon.crearPokemon(new DatosPokemon("Meowth", toSqlDate(LocalDate.of(1999, 11, 10)), metodosPokemon.buscarEntrada(6), metodosPokemon.buscarAdestrador(1)));

        metodosPokemon.crearPokemon(new DatosPokemon("Psyduck", toSqlDate(LocalDate.of(2000, 12, 11)), metodosPokemon.buscarEntrada(7), metodosPokemon.buscarAdestrador(1)));
        metodosPokemon.crearPokemon(new DatosPokemon("Geodude", toSqlDate(LocalDate.of(2001, 12, 12)), metodosPokemon.buscarEntrada(8), metodosPokemon.buscarAdestrador(1)));
        metodosPokemon.crearPokemon(new DatosPokemon("Gastly", toSqlDate(LocalDate.of(2002, 1, 1)), metodosPokemon.buscarEntrada(9), metodosPokemon.buscarAdestrador(1)));
        metodosPokemon.crearPokemon(new DatosPokemon("Onix", toSqlDate(LocalDate.of(2003, 2, 2)), metodosPokemon.buscarEntrada(10), metodosPokemon.buscarAdestrador(1)));
        metodosPokemon.crearPokemon(new DatosPokemon("Drowzee", toSqlDate(LocalDate.of(2004, 3, 3)), metodosPokemon.buscarEntrada(11), metodosPokemon.buscarAdestrador(1)));
        metodosPokemon.crearPokemon(new DatosPokemon("Krabby", toSqlDate(LocalDate.of(2005, 4, 4)), metodosPokemon.buscarEntrada(12), metodosPokemon.buscarAdestrador(1)));

       // metodosAdestrador.exportarAdestradoresXML();
      //  metodosPokedex.exportarPokedex();
      /*
        metodosAdestrador.modificarAdestrador(1, "Pep", new Date(1990, 5, 5));
        metodosAdestrador.modificarAdestrador(2, "Manoloo", new Date(1995, 6, 6));
        metodosPokedex.modificarEntrada(1, "Pikachuu");
        metodosPokedex.modificarEntrada(2, "Charmanderr");

        metodosPokemon.modificarPokemon(1,"Pikachuu");
        metodosPokemon.modificarPokemon(2, "Charmanderr");
        metodosPokemon.modificarPokemon(3, "Squirtlee");
        metodosPokemon.modificarPokemon(4, "Bulbasaure");
*/

        metodosPokemon.listarPokemon();
        metodosPokedex.listarEntradas();
        metodosAdestrador.listarAdestradores();

        // ** Ejemplo 1: Listar los adestradores existentes **
        System.out.println("Listado de adestradores:");
        metodosAdestrador.listarAdestradores();

        // ** Ejemplo 2: Exportar adestradores a XML **
        System.out.println("\nExportando adestradores a XML...");
        metodosAdestrador.exportarAdestradoresXML();

        // ** Ejemplo 3: Importar adestradores desde un archivo XML **
        System.out.println("\nImportando adestradores desde XML...");
        metodosAdestrador.importarAdestradoresXML();

        // ** Ejemplo 4: Listar los adestradores después de la importación **
        System.out.println("\nListado de adestradores después de la importación:");
        metodosAdestrador.listarAdestradores();

       //  metodosPokedex.importarPokedex();
       // metodosAdestrador.importarAdestradoresXML();
        }

    // Método auxiliar para convertir LocalDate a java.sql.Date
    private static Date toSqlDate(LocalDate localDate) {
        return Date.valueOf(localDate); // Date es de java.sql
    }
}
