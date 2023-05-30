package proyectomundial.model;

public class NacionalidadesTecnicos {
    
    String nacionalidad;
    String cantidad;
    String numeracion;
    
    public NacionalidadesTecnicos() {
    }
    
    public NacionalidadesTecnicos(String numeracion, String nacionalidad, String cantidad) {
        this.nacionalidad = nacionalidad;
        this.cantidad = cantidad;
        this.numeracion = numeracion;
    }

    public String getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(String numeracion) {
        this.numeracion = numeracion;
    }
    
    

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
