package proyectomundial.model;

public class PromedioGolesPorPartido {
    
    String partido;
    String promedioGoles;
    
    public PromedioGolesPorPartido() {
        
    }
    
    public PromedioGolesPorPartido(String partido, String promedioGoles) {
        this.partido = partido;
        this.promedioGoles = promedioGoles;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String getPromedioGoles() {
        return promedioGoles;
    }

    public void setPromedioGoles(String promedioGoles) {
        this.promedioGoles = promedioGoles;
    }
    
}
