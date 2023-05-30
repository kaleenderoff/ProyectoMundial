package proyectomundial.DAO;

import java.sql.ResultSet;
import proyectomundial.util.BasedeDatos;

public class DashResultadosDAO {
    
    public DashResultadosDAO() {
        BasedeDatos.conectar();
    }
    
    public String numeroPartidosCargados() {
        String total = "";

        String sql = "SELECT COUNT(grupo) AS count FROM s_rojas9.partidos;";
        
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {
                while (result.next()) {
                    total = result.getString("count");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Dash Resultados");
        }

        return total;
    }
    
    
}
