import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.util.List;

public class XStreamEjercicio {
    public static void main(String[] args) {
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
            List<Pais_serializable> ps= listaPaises.getPaises();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Paises.dat"));


            for (Pais_serializable ps1: ps) {
                System.out.println("nombre: " + ps1.getNombre_pais());
                System.out.println("presi: "+ps1.getNombre_presi());
                System.out.println("PIB: "+ ps1.getPib());
                System.out.println("Gini: "+ps1.getGini());
                System.out.println(" ");
                oos.writeObject(ps1);
            }
            oos.close();
            fr.close();
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}
