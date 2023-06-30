package integracion.venta;

import integracion.transacciones.TransactionManager;
import negocio.producto.TProducto;
import negocio.trabajador.TTrabajador;
import negocio.venta.TLineaVenta;
import negocio.venta.TVenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DAOVentaImp implements DAOVenta {

	public Integer create(TVenta venta) {
		Connection conexion = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		int id = -1;
		String s1 = new String("INSERT INTO venta (total_precio, activo, trabajador_id) VALUES (?, ?, ?)");
		String s2 = new String("INSERT INTO venta_producto ( VENTA_ID, PRODUCTO_ID, CANTIDAD) VALUES (?, ?, ?)");

		try {

			PreparedStatement ps = conexion.prepareStatement(s1, java.sql.Statement.RETURN_GENERATED_KEYS);

			ps.setFloat(1, venta.getPrecioTotal());
			ps.setBoolean(2, true);
			ps.setInt(3, venta.getTrabajador().getId());
			ps.executeUpdate();

			// Creo el ID en la BBDD
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next())
				id = rs.getInt(1);

			for (TLineaVenta producto : venta.getProductos()) {
				ps = conexion.prepareStatement(s2);

				ps.setInt(1, id);
				ps.setInt(2, producto.getProducto().getId());
				ps.setInt(3, producto.getCantidad());

				ps.executeUpdate();
			}
			ps.close();
			rs.close();
			//conexion.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return id;
	}

	public TVenta readById(Integer Id) {
		Connection conexion = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		String s1 = new String("SELECT * FROM venta WHERE id = ? FOR UPDATE");
		String s2 = new String("SELECT * FROM VENTA_PRODUCTO WHERE VENTA_ID = ? FOR UPDATE");
		TVenta venta = null;
		
		try {
			PreparedStatement ps = conexion.prepareStatement(s1);
			ps.setInt(1, Id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				venta = new TVenta(rs.getInt("id"), rs.getBoolean("activo"), rs.getFloat("total_precio"),
						new TTrabajador(rs.getInt("trabajador_id")), rs.getTimestamp("fecha"));

				rs = ps.executeQuery(s2);

				while (rs.next()) {
					TLineaVenta p = new TLineaVenta(rs.getInt("CANTIDAD"), new TProducto(rs.getInt("PRODUCTO_ID")));
					venta.addProduct(p);
				}

			}
			ps.close();
			rs.close();
			//conexion.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return venta;
	}


	public Integer delete(Integer Id) {

		Integer deleted = -1;
		Connection conexion = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement ps = conexion.prepareStatement("UPDATE venta SET activo = ? WHERE id = ?", java.sql.Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, 0);
			ps.setInt(2, Id);

			ps.executeUpdate();

			deleted = 1;
			ps.close();
			//conexion.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return deleted;
	}

	

}
