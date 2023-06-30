package integraci�n.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import integración.transacciones.TransactionManager;
import negocio.cliente.TCliente;

public class QueryClienteMasFacturacion implements Query {
		private final String QUERYCLIENTE = "SELECT id_cliente, dni, nombre, apellidos, socio, activo" +
			" FROM CLIENTE join FACTURA using(id_cliente, activo) " +
			"WHERE activo = true AND precio_tot >= (SELECT MAX(precio_tot) FROM FACTURA F)";
	
	@Override
	public Object execute() {
		ArrayList<TCliente> lista = new ArrayList<TCliente>();
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		
		try{
			PreparedStatement ps = connection.prepareStatement("LOCK TABLES CLIENTE READ, FACTURA READ, FACTURA AS F READ");
			ps.execute();
			ps.close();
			ps = connection.prepareStatement(QUERYCLIENTE, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			ps.close();
			
			while(rs.next()) {
				lista.add(new TCliente(rs.getInt("id_cliente"), rs.getString("dni"), 
				rs.getString("nombre"), rs.getString("apellidos"), rs.getBoolean("socio"), rs.getBoolean("activo")));
			}	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return new ArrayList<TCliente>();
		}
		
		return lista;
	}

}
