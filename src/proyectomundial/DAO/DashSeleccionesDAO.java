package proyectomundial.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import proyectomundial.model.NacionalidadesTecnicos;
import proyectomundial.model.SeleccionesPorContinente;
import proyectomundial.util.BasedeDatos;

public class DashSeleccionesDAO {

    public DashSeleccionesDAO() {
        BasedeDatos.conectar();
    }

    public String totalSeleccionesCargadas() {

        String total = "";

        String sql = "SELECT COUNT(nombre) AS count FROM s_rojas9.seleccion;";

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {
                while (result.next()) {
                    total = result.getString("count");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Dash Selecciones");
        }

        return total;
    }

    public List<SeleccionesPorContinente> getSeleccionesPorContinente() {
        String sql = "SELECT continente, COUNT(continente) AS Cantidad_selecciones from s_rojas9.seleccion s GROUP BY 1 HAVING COUNT(continente) > 0;";
        
        List<SeleccionesPorContinente> seleccionesPorContinentes = new ArrayList<SeleccionesPorContinente>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    SeleccionesPorContinente seleccionesPorContinente = new SeleccionesPorContinente(result.getString("continente"), result.getString("Cantidad_selecciones"));
                    seleccionesPorContinentes.add(seleccionesPorContinente);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Dash Selecciones");
        }

        return seleccionesPorContinentes;
    }

    public String[][] getSeleccionesPorContinenteMatriz() {

        String[][] matrizSeleccionesPorContinente = null;
        List<SeleccionesPorContinente> seleccionesPorContinentes = getSeleccionesPorContinente();

        if (seleccionesPorContinentes != null && seleccionesPorContinentes.size() > 0) {

            matrizSeleccionesPorContinente = new String[seleccionesPorContinentes.size()][2];

            int x = 0;
            for (SeleccionesPorContinente seleccionesPorContinente : seleccionesPorContinentes) {

                matrizSeleccionesPorContinente[x][0] = seleccionesPorContinente.getContinente();
                matrizSeleccionesPorContinente[x][1] = seleccionesPorContinente.getCantidad();
                x++;
            }
        }

        return matrizSeleccionesPorContinente;
    }

    public List<NacionalidadesTecnicos> getNacionalidadesTecnicos() {
        

        String sql = "SELECT nacionalidad, COUNT(nacionalidad) AS Cantidad_Directores_Tecnicos FROM s_rojas9.seleccion s GROUP BY 1 HAVING COUNT(nacionalidad) > 0;";


        List<NacionalidadesTecnicos> nacTecnicos = new ArrayList<NacionalidadesTecnicos>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if (result != null) {

                while (result.next()) {
                    NacionalidadesTecnicos nacionalidadesTecnicos = new NacionalidadesTecnicos("", result.getString("nacionalidad"), result.getString("Cantidad_Directores_Tecnicos"));
                    nacTecnicos.add(nacionalidadesTecnicos);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Dash Selecciones");
        }
        
        return nacTecnicos;
    }
    
    public String[][] getCantidadNacionalidadesTecnicosMatriz() {
        String[][] matrizNacionalidadesTecnicos = null;
        List<NacionalidadesTecnicos> nacionalidadesTecnicos = getNacionalidadesTecnicos();

        if (nacionalidadesTecnicos != null && nacionalidadesTecnicos.size() > 0) {

            matrizNacionalidadesTecnicos = new String[nacionalidadesTecnicos.size()][2];

            int x = 0;
            for (NacionalidadesTecnicos nacionalidadesTecnico : nacionalidadesTecnicos) {

                matrizNacionalidadesTecnicos[x][0] = nacionalidadesTecnico.getNacionalidad();
                matrizNacionalidadesTecnicos[x][1] = nacionalidadesTecnico.getCantidad();
                x++;
            }
        }

        return matrizNacionalidadesTecnicos;
    }
    
    public List<NacionalidadesTecnicos> getRankingNacionalidades() {
        String sql = "SELECT nacionalidad, COUNT(nacionalidad) AS Cantidad_Directores_Tecnicos FROM s_rojas9.seleccion s GROUP BY 1 HAVING COUNT(nacionalidad) > 0 ORDER BY Cantidad_Directores_Tecnicos DESC;";
        List<NacionalidadesTecnicos> rankingNacionalidades = new ArrayList<NacionalidadesTecnicos>();
        
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result != null) {
            int cont = 0;
                while (result.next()) { 
                    cont++;
                    NacionalidadesTecnicos ranking = new NacionalidadesTecnicos( String.valueOf(cont),result.getString("nacionalidad"), result.getString("Cantidad_Directores_Tecnicos"));
                    rankingNacionalidades.add(ranking);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Dash Selecciones");
        }
        
        return rankingNacionalidades;
    }

    public String[][] getRankingNacionalidadesMatriz() {
        String[][] matrizRankingNacionalidades = null;
        List<NacionalidadesTecnicos> rankingNacionalidades = getRankingNacionalidades();

        if (rankingNacionalidades != null && rankingNacionalidades.size() > 0) {

            matrizRankingNacionalidades = new String[rankingNacionalidades.size()][3];

            int x = 0;
            for (NacionalidadesTecnicos rankingNacionalidad : rankingNacionalidades) {

                matrizRankingNacionalidades[x][0] = rankingNacionalidad.getNumeracion();
                matrizRankingNacionalidades[x][1] = rankingNacionalidad.getNacionalidad();
                matrizRankingNacionalidades[x][2] = rankingNacionalidad.getCantidad();
                x++;
            }
        }

        return matrizRankingNacionalidades;
    }
}
