package integración.transacciones.imp;

import java.sql.Connection;
import java.sql.DriverManager;

import integración.transacciones.Transaction;
import integración.transacciones.TransactionManager;

public class TransactionMySQL implements Transaction {
	private static String url = "jdbc:mysql://teatro-database.cp7iwtc61ifu.eu-west-3.rds.amazonaws.com:3306/teatro";
	private static String usuario = "admin";
	private static String clave = "adminteatro";

	Connection con;

	/** 
	* @return the con
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Connection getCon() {
		// begin-user-code
		return con;
		// end-user-code
	}

	/** 
	* @param con the con to set
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setCon(Connection con) {
		// begin-user-code
		this.con = con;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void TransacationMySQL() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public TransactionMySQL() {
		try {
			con = DriverManager.getConnection(url, usuario, clave);
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
	public void commit() throws Exception {
		try {
			con.commit();
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		TransactionManager.getInstance().eliminaTransaccion();
	}

	@Override
	public void rollback() {
		try {
			con.rollback();
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		TransactionManager.getInstance().eliminaTransaccion();
	}

	@Override
	public Object getResource() {
		return con;
	}
}
