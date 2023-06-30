package integración.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import integración.transacciones.TransactionManager;
import negocio.obra.TObra;

public class QueryObraRepresentacionMasVista implements Query {
	
	private final String QUERY_OBRA = 
			"SELECT id_obra, sinopsis, genero, titulo, autor, año, activo " +
			"FROM REPRESENTACION JOIN LINEA_FACT USING(id_actividad, activo) " +
			"	  JOIN OBRA USING(id_obra, activo) " +
			"WHERE activo = true " +
			"GROUP BY id_obra " +
			"HAVING SUM(num_entradas) >= ALL (SELECT SUM(L.num_entradas) " +
			"							  	  FROM REPRESENTACION R JOIN LINEA_FACT L " +
			"								   		ON R.id_actividad = L.id_actividad " +
			"	   							  WHERE R.activo = true " +
			"	   						  	  GROUP BY R.id_obra)";
	
	@Override
	public Object execute() {
		ArrayList<TObra> lista = new ArrayList<TObra>();
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		
		try{
			PreparedStatement ps =
					connection.prepareStatement(
							"LOCK TABLES OBRA READ, LINEA_FACT READ, REPRESENTACION READ,"
							+ "REPRESENTACION AS R READ,"
							+ "LINEA_FACT AS L READ");
			ps.execute();
			ps.close();
			ps = connection.prepareStatement(QUERY_OBRA, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			ps.close();
			while(rs.next()) {
				lista.add(new TObra(rs.getInt("id_obra"), rs.getString("genero"), 
					rs.getString("sinopsis"), rs.getString("titulo"), 
					rs.getString("autor"), rs.getInt("año"), rs.getBoolean("activo")));
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return new ArrayList<TObra>();
		}
		return lista;
	}

}
