import java.util.ArrayList;
import java.util.List;

public class ListaPaises {

    private List<Pais_serializable> lista = new ArrayList<Pais_serializable>();

    public ListaPaises() {}

    // Método para agregar un país a la lista
    public void add(Pais_serializable a) {
        lista.add(a);
    }

    // Método para obtener la lista de países
    public List<Pais_serializable> getPaises() {
        return lista;
    }

    // Método para establecer la lista de países
    public void setLista(List<Pais_serializable> lista) {
        this.lista = lista;
    }
}
