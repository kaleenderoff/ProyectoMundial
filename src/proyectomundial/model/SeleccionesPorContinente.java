package proyectomundial.model;

public class SeleccionesPorContinente {

    String continente;
    String cantidad;

    public SeleccionesPorContinente() {
    }

    public SeleccionesPorContinente(String continente, String cantidad) {
        this.continente = continente;
        this.cantidad = cantidad;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
