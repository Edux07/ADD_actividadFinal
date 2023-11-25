import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DOM {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docubuilder = dbf.newDocumentBuilder();
        //Crear documento
        Document doc = docubuilder.newDocument();
        //Crear elemento raiz
        Element rootElement = doc.createElement("Listapaises");
        //Añadir al doc el elemento
        doc.appendChild(rootElement);

        //Meter todod
        String[] nombres = {"Belice", "El Salvador", "Guatemala", "Honduras", "México", "Panamá", "Costa Rica"};
        String[] presidentes = {"Froyla Tzalam", "Nayib Bukele", "Alejandro Giammattei", "Xiomara Castro", "Daniel Ortega", "Andres Manuel Lopez Obrador", "Laurentino Cortizo", "Rodrigo Chaves"};
        int[] pibValues = {1987, 74818, 185473, 85625, 47770, 2890685, 128500, 129950};
        double[] giniValues = {53.3, 38.8, 48.3, 48.2, 46.2, 45.4, 50.9, 47.2};

        ListaPaises lista = new ListaPaises();

        for (int i = 0; i < nombres.length; i++) {
            Pais_serializable pais = new Pais_serializable(nombres[i], presidentes[i], String.valueOf(pibValues[i]), String.valueOf(giniValues[i]));

            lista.add(pais);
            //creamos elementp raiz
            Element paisElement = doc.createElement("pais");

            Element nombre_pais = doc.createElement("nombre_pais");

            nombre_pais.appendChild(doc.createTextNode(nombres[i]));
            paisElement.appendChild(nombre_pais);

            Element nombre_presi= doc.createElement("nombre_presi");
            nombre_presi.appendChild(doc.createTextNode(presidentes[i]));
            paisElement.appendChild(nombre_presi);

            Element PIB_valor= doc.createElement("pib");
            PIB_valor.appendChild(doc.createTextNode(String.valueOf(pibValues[i])));
            paisElement.appendChild(PIB_valor);

            Element gini_valor= doc.createElement("gini");
            gini_valor.appendChild(doc.createTextNode(String.valueOf(giniValues[i])));
            paisElement.appendChild(gini_valor);

            rootElement.appendChild(paisElement);
        }
        //Guardar cotenido en XML
        TransformerFactory tf= TransformerFactory.newInstance();
        Transformer trans= tf.newTransformer();
        DOMSource DS= new DOMSource(doc);
        StreamResult res= new StreamResult("paises.xml");
        trans.transform(DS,res);
        System.out.println("doc creado");



    }

}
