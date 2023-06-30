package integracion.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.trabajador.DAOTrabajador;
import integracion.transacciones.TransactionManager;
import negocio.trabajador.TTrabajador;
public class QueryTrabajadorConMasVentas implements Query {
DAOTrabajador daoTrabajador = FactoriaIntegracion.getInstance().generarDAOTrabajador();


	
	@Override
	public Object execute(String ini,String fin) {
		String QUERYCLIENTE = "SELECT T.ID, COUNT(*) AS CONT FROM TRABAJADOR AS T JOIN VENTA AS V ON T.id = V.trabajador_id "
				+ "WHERE t.activo>0 AND FECHA BETWEEN \"" +ini+ "\" AND \""+ fin +"\" GROUP BY trabajador_id FOR UPDATE";
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		TTrabajador tTrabajador = null;
		try{
			PreparedStatement ps = connection.prepareStatement(QUERYCLIENTE, java.sql.Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			//_ps.close();
			int id = -1, max = 0;
			while(rs.next()) {
				int aux = rs.getInt("cont");
				if(aux > max) {
					max = aux;
					id = rs.getInt("id");
				}
			}	
			if(id!= -1)	tTrabajador = daoTrabajador.readById(id);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return tTrabajador;
	}

}