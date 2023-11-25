import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.util.List;

public class XStreamEjercicio {
    public static void main(String[] args) {
        // Configuración de XStream para la serialización y deserialización XML
        XStream xs = new XStream(new DomDriver());
        try {

            // Configuración de permisos para tipos, nulos y tipos primitivos
            xs.addPermission(com.thoughtworks.xstream.security.NoTypePermission.NONE);
            xs.addPermission(com.thoughtworks.xstream.security.NullPermission.NULL);
            xs.addPermission(com.thoughtworks.xstream.security.PrimitiveTypePermission.PRIMITIVES);

            // Permitir los tipos específicos utilizados en la aplicación
            xs.allowTypes(new Class[]{ListaPaises.class, Pais_serializable.class});

            // Alias para las clases y configuración de colección implícita
            xs.alias("Listapaises", ListaPaises.class); // Alias para ListaPaises en XML
            xs.addImplicitCollection(ListaPaises.class, "lista", Pais_serializable.class); // Colección implícita en ListaPaises
            xs.alias("pais", Pais_serializable.class); // Alias para Pais_serializable en XML

            // Lectura del archivo XML
            FileReader fr = new FileReader("paises.xml");
            ListaPaises listaPaises = (ListaPaises) xs.fromXML(fr); // Deserialización del XML a ListaPaises
            List<Pais_serializable> ps = listaPaises.getPaises(); // Obtención de la lista de países

            // Escritura en un archivo binario
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Paises.dat"));

            // Pilla la lista la enseña por consola y la escribe en el fichero binario
            for (Pais_serializable ps1 : ps) {
                System.out.println("nombre: " + ps1.getNombre_pais());
                System.out.println("presi: " + ps1.getNombre_presi());
                System.out.println("PIB: " + ps1.getPib());
                System.out.println("Gini: " + ps1.getGini());
                System.out.println(" ");
                oos.writeObject(ps1);
            }

            // Cierre de recursos
            oos.close();
            fr.close();
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
        }
    }
}
