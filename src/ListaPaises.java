import java.util.ArrayList;
import java.util.List;

public class ListaPaises {

    private List<Pais_serializable> lista = new ArrayList<Pais_serializable>();

    public ListaPaises() {}

    public void add(Pais_serializable a) {
        lista.add(a);
    }

    public List<Pais_serializable> getPaises() {
        return lista;
    }



    public void setLista(List<Pais_serializable> lista) {
        this.lista = lista;
    }
}
