package integracion.transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionMySQL implements Transaction {
	private static String bd = "databasehelad";
	private static String url = "jdbc:mysql://localhost:3306/"+bd+"?allowMultiQueries=true";
	private static String usuario = "root";
	private static String clave = "root";

	Connection con;

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public TransactionMySQL() {
		try {
			con = DriverManager.getConnection(url, usuario, clave);
			System.out.println("Conexion con " + bd + " creada correctamente.\n");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void start() {
		try {
			con.setAutoCommit(false);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void commit() throws Exception, SQLException {
		if(con != null) {
			try {
				con.commit();
				//con.close();
				TransactionManager.getInstance().eliminaTransaccion();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	@Override
	public void rollback() throws Exception, SQLException {
		if(con != null) {
			try {
				con.rollback();
				TransactionManager.getInstance().eliminaTransaccion();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public Object getResource() {
		return con;
	}
}
