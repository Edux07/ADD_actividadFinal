import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Maindao {
    protected static Connection connection;

    public static void main(String[] args) throws SQLException {
        List<Pais_serializable> ps = new ArrayList<>();
        DAO d1 = new DAO();
        d1.createTable();
        XStream xs = new XStream(new DomDriver());
        try {
            // Permissions and allowed type configuration
            xs.addPermission(com.thoughtworks.xstream.security.NoTypePermission.NONE);
            xs.addPermission(com.thoughtworks.xstream.security.NullPermission.NULL);
            xs.addPermission(com.thoughtworks.xstream.security.PrimitiveTypePermission.PRIMITIVES);

            xs.allowTypes(new Class[]{ListaPaises.class, Pais_serializable.class});
            xs.alias("Listapaises",ListaPaises.class); //De la clase DOM
            xs.addImplicitCollection(ListaPaises.class,"lista",Pais_serializable.class); //De la clase ListaPaises
            xs.alias("pais", Pais_serializable.class);

            FileReader fr= new FileReader("paises.xml");
            ListaPaises listaPaises = (ListaPaises) xs.fromXML(fr);
            ps= listaPaises.getPaises();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Paises.dat"));


            for (Pais_serializable ps1: ps) {
                System.out.println("nombre: " + ps1.getNombre_pais());
                System.out.println("presi: "+ps1.getNombre_presi());
                System.out.println("PIB: "+ ps1.getPib());
                System.out.println("Gini: "+ps1.getGini());
                System.out.println(" ");
                oos.writeObject(ps1);
                d1.insertar(ps1);

            }
            oos.close();
            fr.close();
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }

        // Modificar el PIB de todos los países en 10 millones de dólares
        d1.modificarPIB(10000000);
        d1.buscar("El Salvador");
        d1.buscar("Honduras");
        d1.buscar("México");
        d1.buscar("Guatemala");
        d1.buscar("Belice");
        d1.buscar("Panamá");
        d1.buscar("Costa Rica");

        System.out.println("--------------------------------------------");

        // Reducir el coeficiente de Gini de algunos países
        d1.reducirGini("El Salvador");
        d1.reducirGini("Honduras");
        d1.reducirGini("México");
        d1.buscar("Honduras");
        d1.buscar("México");
        d1.buscar("El Salvador");
    }
}
