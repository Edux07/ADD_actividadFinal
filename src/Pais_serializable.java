import java.io.Serializable;

public class Pais_serializable implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre_pais;
    private String nombre_presi;
    private String pib;
    private String gini;

    public String getNombre_pais() {
        return nombre_pais;
    }

    public Pais_serializable(String nombrepais, String presidente, String PIB, String gini) {
        this.nombre_pais = nombrepais;
        this.nombre_presi = presidente;
        this.pib = PIB;
        this.gini = gini;
    }

    public void setNombre_pais(String nombre_pais) {
        this.nombre_pais = nombre_pais;
    }

    public String getNombre_presi() {
        return nombre_presi;
    }

    public void setNombre_presi(String nombre_presi) {
        this.nombre_presi = nombre_presi;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getGini() {
        return gini;
    }

    public void setGini(String gini) {
        this.gini = gini;
    }
}
