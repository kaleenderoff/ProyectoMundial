package proyectomundial.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import proyectomundial.model.Resultado;
import proyectomundial.util.BasedeDatos;

public class ResultadoDAO {

    public ResultadoDAO() {
        BasedeDatos.conectar();
    }

    public List<Resultado> buscarResultados(String grupo, String local, 
            String visitante, String continente_local, String continente_visitante, 
            String goles_local, String goles_visitante) {
        String sql = "SELECT grupo, local, visitante, continente_local, continente_visitante, "
                + "goles_local, goles_visitante FROM s_rojas9.partidos "
                + "WHERE grupo ILIKE '%" + grupo + "%' "
                + "OR local ILIKE '%" + local + "%' "
                + "OR visitante ILIKE '%" + visitante + "%' "
                + "OR continente_local ILIKE '%" + continente_local + "%' "
                + "OR continente_visitante ILIKE '%" + continente_visitante + "%' "
                + "OR goles_local ILIKE '%" + goles_local + "%' "
                + "OR goles_visitante ILIKE '%" + goles_visitante + "%';";

        List<Resultado> resultados = new ArrayList<Resultado>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Resultado resultado = new Resultado(result.getString("grupo"), result.getString("local"), result.getString("visitante"), result.getString("continente_local"), result.getString("continente_visitante"), result.getString("goles_local"), result.getString("goles_visitante"));
                    resultados.add(resultado);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return resultados;
    }

    public boolean registrarResultado(Resultado resultado) {

        String sql = "INSERT INTO s_rojas9.partidos (grupo, local, visitante, continente_local, continente_visitante, goles_local, goles_visitante) values("
                + "'" + resultado.getGrupo() + "', "
                + "'" + resultado.getLocal() + "', "
                + "'" + resultado.getVisitante() + "', "
                + "'" + resultado.getContinente_local() + "', "
                + "'" + resultado.getContinente_visitante() + "', "
                + "'" + resultado.getGoles_local() + "', "
                + "'" + resultado.getGoles_visitante() + "')";

        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }

    public List<Resultado> getResultados() {

        String sql = "SELECT grupo, local, visitante, continente_local, continente_visitante, goles_local, goles_visitante FROM s_rojas9.partidos";
        List<Resultado> resultados = new ArrayList<Resultado>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Resultado resultado = new Resultado(result.getString("grupo"), result.getString("local"), result.getString("visitante"), result.getString("continente_local"), result.getString("continente_visitante"), result.getString("goles_local"), result.getString("goles_visitante"));
                    resultados.add(resultado);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return resultados;
    }

    public String[][] getResultadosMatriz() {

        String[][] matrizResultados = null;
        List<Resultado> resultados = getResultados();

        if (resultados != null && resultados.size() > 0) {

            matrizResultados = new String[resultados.size()][7];

            int x = 0;
            for (Resultado resultado : resultados) {

                matrizResultados[x][0] = resultado.getGrupo();
                matrizResultados[x][1] = resultado.getLocal();
                matrizResultados[x][2] = resultado.getVisitante();
                matrizResultados[x][3] = resultado.getContinente_local();
                matrizResultados[x][4] = resultado.getContinente_visitante();
                matrizResultados[x][5] = resultado.getGoles_local();
                matrizResultados[x][6] = resultado.getGoles_visitante();
                x++;
            }
        }

        return matrizResultados;
    }

    public String[][] getResultadosMatriz(String grupo, String local, 
            String visitante, String continente_local, String continente_visitante, 
            String goles_local, String goles_visitante) {

        String[][] matrizResultados = null;
        List<Resultado> resultados = buscarResultados(grupo, local, visitante, continente_local, continente_visitante, goles_local, goles_visitante);

        if (resultados != null && resultados.size() > 0) {

            matrizResultados = new String[resultados.size()][7];

            int x = 0;
            for (Resultado resultado : resultados) {

                matrizResultados[x][0] = resultado.getGrupo();
                matrizResultados[x][1] = resultado.getLocal();
                matrizResultados[x][2] = resultado.getVisitante();
                matrizResultados[x][3] = resultado.getContinente_local();
                matrizResultados[x][4] = resultado.getContinente_visitante();
                matrizResultados[x][5] = resultado.getGoles_local();
                matrizResultados[x][6] = resultado.getGoles_visitante();
                x++;
            }
        }

        return matrizResultados;
    }
}
