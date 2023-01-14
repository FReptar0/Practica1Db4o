import com.db4o.*;

public class App {
    public static void main(String[] args) throws Exception {
        String ruta = "libros.db4o";
        ObjectContainer db = Db4o.openFile(Db4o.newConfiguration(), ruta);

        try {
            /* Inicio de la insercion de libros */

            Libros libro = new Libros("El Señor de los Anillos", "J.R.R. Tolkien", 1954);
            Libros libro2 = new Libros("Cien años de soledad", "Gabriel García Márquez", 1967);
            Libros libro3 = new Libros("El Hobbit", "J.R.R. Tolkien", 1937);
            Libros libro4 = new Libros("1984", "George Orwell", 1949);
            Libros libro5 = new Libros("Orgullo y Prejuicio", "Jane Austen", 1813);
            Libros libro6 = new Libros("El Principito", "Antoine de Saint-Exupéry", 1943);
            Libros libro7 = new Libros("El Alquimista", "Paulo Coelho", 1988);
            Libros libro8 = new Libros("El Diario de Ana Frank", "Ana Frank", 1947);
            Libros libro9 = new Libros("El Perfume", "Patrick Süskind", 1985);
            Libros libro10 = new Libros("El Nombre de la Rosa", "Umberto Eco", 1980);

            db.store(libro);
            db.store(libro2);
            db.store(libro3);
            db.store(libro4);
            db.store(libro5);
            db.store(libro6);
            db.store(libro7);
            db.store(libro8);
            db.store(libro9);
            db.store(libro10);
            /* Fin de la insercion */

            /* Inicio de muestra de datos insertados */
            System.out.println("Datos insertados:");
            ObjectSet<Libros> result = db.queryByExample(new Libros(null, null, 0));
            while (result.hasNext()) {
                System.out.println(result.next());
            }
            /* Fin de muestra de datos insertados */

            /* Inicio de consultas especificas */
            System.out.println("Consultas especificas:");

            // Consulta 1
            ObjectSet<Libros> rs = db.queryByExample(new Libros("El Señor de los Anillos", null, 0));
            for (Libros l : rs) {
                System.out.println(l);
            }

            // Consulta 2
            ObjectSet<Libros> rs2 = db.queryByExample(new Libros("El Hobbit", null, 0));
            for (Libros l : rs2) {
                System.out.println(l);
            }

            // Consulta 3
            ObjectSet<Libros> rs3 = db.queryByExample(new Libros("El Nombre de la Rosa", null, 0));
            for (Libros l : rs3) {
                System.out.println(l);
            }
            /* Fin de consultas especificas */

            /* Actualización de los datos que se consultaron */
            System.out.println("Actualización de los datos que se consultaron:");

            // Actualización 1
            ObjectSet<Libros> rs4 = db.queryByExample(new Libros("El Señor de los Anillos", null, 0));
            for (Libros l : rs4) {
                l.setTitulo("El Señor de los Anillos: La Comunidad del Anillo");
                db.store(l);
            }

            // Actualización 2
            ObjectSet<Libros> rs5 = db.queryByExample(new Libros("El Hobbit", null, 0));
            for (Libros l : rs5) {
                l.setTitulo("El Hobbit: Un Viaje Inesperado");
                db.store(l);
            }

            // Actualización 3
            ObjectSet<Libros> rs6 = db.queryByExample(new Libros("El Nombre de la Rosa", null, 0));
            for (Libros l : rs6) {
                l.setTitulo("El Silmarillion");
                db.store(l);
            }

            /* Fin de actualización de los datos que se consultaron */

            /* Inicio Consultar todos los datos */
            System.out.println("Consultar todos los datos:");
            ObjectSet<Libros> result2 = db.queryByExample(new Libros(null, null, 0));
            while (result2.hasNext()) {
                System.out.println(result2.next());
            }
            /* Fin Consultar todos los datos */

            /* Inicio de la elimincacion */
            System.out.println("Eliminación de los datos");

            ObjectSet<Libros> rs7 = db.queryByExample(new Libros(null, "J.R.R. Tolkien", 0));
            for (Libros l : rs7) {
                db.delete(l);
            }

            /* Fin de la eliminacion */

            /* Inicio Consultar todos los datos */
            System.out.println("Consultar todos los datos:");
            ObjectSet<Libros> result3 = db.queryByExample(new Libros(null, null, 0));

            while (result3.hasNext()) {
                System.out.println(result3.next());
            }

            /* Fin Consultar todos los datos */

        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            db.close();
        }

    }
}
