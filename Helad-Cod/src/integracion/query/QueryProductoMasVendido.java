package integracion.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.producto.DAOProducto;
import integracion.transacciones.TransactionManager;
import negocio.producto.TProducto;
public class QueryProductoMasVendido implements Query {

	@Override
	public Object execute(String ini,String fin) {
		//String QUERYCLIENTE = "SELECT * FROM VENTA JOIN VENTA_PRODUCTO ON ID = VENTA_ID WHERE ACTIVO > 0 AND  FECHA BETWEEN \""+ini +"\" AND \""+fin+"\" ORDER BY PRODUCTO_ID DESC FOR UPDATE";
		String QUERYCLIENTE2 = "SELECT PRODUCTO_ID,SUM(CANTIDAD) AS cantidad FROM VENTA JOIN VENTA_PRODUCTO ON ID = VENTA_ID WHERE ACTIVO > 0 AND  FECHA BETWEEN \""+ini +"\" AND \""+fin+"\" GROUP BY PRODUCTO_ID ORDER BY SUM(CANTIDAD) DESC FOR UPDATE";

	Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
	//TProducto tProducto=null;
	ArrayList<TProducto> listaProductos=new ArrayList<>();
	DAOProducto daoproducto = FactoriaIntegracion.getInstance().generarDAOProducto();

	try{
		PreparedStatement ps = connection.prepareStatement(QUERYCLIENTE2, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = ps.executeQuery();
		int id= -1, cant=0;
		if(rs.next()){
			id = rs.getInt("producto_id");
			cant= rs.getInt("cantidad");
			listaProductos.add(daoproducto.readById(id));
		}
		//rs.next();
		while(rs.next() && cant==rs.getInt("cantidad")){
			id=rs.getInt("producto_id");
			listaProductos.add(daoproducto.readById(id));
			rs.next();
		}
		ps.close();
		rs.close();
		/*PreparedStatement ps = connection.prepareStatement(QUERYCLIENTE, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = ps.executeQuery();
		int id= -1, max =0, contMax=0,cont=0;
		if(rs.next()){
			id = rs.getInt("producto_id");
			cont= rs.getInt("cantidad");
		}
		while(rs.next()){
			if(id==rs.getInt("producto_id")) cont+=rs.getInt("cantidad");
			else {
				id=rs.getInt("producto_id");
				cont=rs.getInt("cantidad");
			}
			if(cont>contMax){
				contMax=cont;
				
				max=id;
			}
		}
		if(id!=-1)tProducto= daoproducto.readById(max);
		ps.close();*/
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	
	return listaProductos;


	}

}