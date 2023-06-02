package proyectomundial.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import proyectomundial.model.Gol;
import proyectomundial.model.PromedioGolesPorPartido;
import proyectomundial.util.BasedeDatos;

public class DashResultadosDAO {

    public DashResultadosDAO() {
        BasedeDatos.conectar();
    }

    // Numero de partidos cargados
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

    // promedio de goles por partido
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

    // Partido con menos goles 
    public List<Gol> getPartidosConMenosGoles() {
        String sql = "SELECT CONCAT_WS(' - ', local,visitante) as partido, MIN(goles_local + goles_visitante) "
                + "AS gol_minimo FROM s_rojas9.partidos p GROUP BY partido "
                + "HAVING SUM(goles_local + goles_visitante) = 0;";
        List<Gol> gol = new ArrayList<>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {
                while (result.next()) {
                    Gol golminimo = new Gol(result.getString("partido"), result.getString("gol_minimo"));
                    gol.add(golminimo);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Dash Resultados");
        }

        return gol;
    }

    public String[][] getPartidosConMenosGolesMatriz() {

        String[][] matrizPartidosConMenosGoles = null;
        List<Gol> golminimo = getPartidosConMenosGoles();

        if (golminimo != null && golminimo.size() > 0) {

            matrizPartidosConMenosGoles = new String[golminimo.size()][2];

            int x = 0;
            for (Gol gol : golminimo) {

                matrizPartidosConMenosGoles[x][0] = gol.getPartido();
                matrizPartidosConMenosGoles[x][1] = gol.getGol();
                x++;
            }
        }

        return matrizPartidosConMenosGoles;
    }

    // Partido con mas goles 
    public List<Gol> getPartidosConMasGoles() {
        String sql = "SELECT CONCAT_WS(' - ', local,visitante) as partido, MAX(goles_local + goles_visitante) "
                + "AS gol_maximo FROM s_rojas9.partidos p GROUP BY partido "
                + "HAVING SUM(goles_local + goles_visitante) = 7;";
        List<Gol> gol = new ArrayList<>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {
                while (result.next()) {
                    Gol golmaximo = new Gol(result.getString("partido"), result.getString("gol_maximo"));
                    gol.add(golmaximo);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Dash Resultados");
        }

        return gol;
    }

    public String[][] getPartidosConMasGolesMatriz() {

        String[][] matrizPartidosConMasGoles = null;
        List<Gol> golmaximo = getPartidosConMasGoles();

        if (golmaximo != null && golmaximo.size() > 0) {

            matrizPartidosConMasGoles = new String[golmaximo.size()][2];

            int x = 0;
            for (Gol gol : golmaximo) {

                matrizPartidosConMasGoles[x][0] = gol.getPartido();
                matrizPartidosConMasGoles[x][1] = gol.getGol();
                x++;
            }
        }

        return matrizPartidosConMasGoles;
    }

    // Número de partidos dónde hubo un ganador y número de partidos dónde hubo empate
    

}
