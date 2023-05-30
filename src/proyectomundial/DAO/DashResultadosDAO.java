package proyectomundial.DAO;

import java.sql.ResultSet;
import java.util.List;
import proyectomundial.model.PromedioGolesPorPartido;
import proyectomundial.util.BasedeDatos;

public class DashResultadosDAO {
    
    public DashResultadosDAO() {
        BasedeDatos.conectar();
    }
    
    public String numeroPartidosCargados() {
        String total = "";

        String sql = "SELECT COUNT(grupo) AS numero_partidos FROM s_rojas9.partidos;";
        
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {
                while (result.next()) {
                    total = result.getString("numero_partidos");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Dash Resultados");
        }

        return total;
    }
    
    public String promedioGolesPorPartido() {
        String total = "";
        
        String sql = "SELECT AVG(goles_local + goles_visitante) AS promedio_goles FROM s_rojas9.partidos;";
        
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {
                while (result.next()) {
                    total = result.getString("promedio_goles");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Dash Resultados");
        }

        return total;
    }
}
