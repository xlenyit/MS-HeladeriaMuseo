package negocio.ingrediente;

import java.util.List;

import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.ingrediente.DAOIngrediente;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import presentacion.controlador.Evento;

public class SAIngredienteImp implements SAIngrediente{

	DAOIngrediente daoIngrediente = FactoriaIntegracion.getInstance().generarDAOIngrediente();

	public Integer altaIngrediente(TIngrediente tIngrediente, String tipo) {
		int id = -1;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction trans;
		try {
			trans = tm.nuevaTransaccion();
		} catch(Exception e) {
			trans = tm.getTransaccion();
		}
		try{
			if (tIngrediente != null) {
				trans.start();
				if (daoIngrediente.readByCodigo(tIngrediente.getCodigo()) != null && daoIngrediente.readByCodigo(tIngrediente.getCodigo()).getActivo()) {
					trans.rollback();
					return Evento.CODIGO_EXISTENTE;
				}
				if (daoIngrediente.readByCodigo(tIngrediente.getCodigo()) == null) {
					id= daoIngrediente.create(tIngrediente);
					trans.commit();
				}
				else {
					TIngrediente aux = daoIngrediente.readByCodigo(tIngrediente.getCodigo());
					id = aux.getId();
					aux.setActivo(true);
					daoIngrediente.update(aux);
					trans.commit();
				}
					
			}
		}
		catch (Exception e) {
		System.out.println(e.getMessage());
		}
		return id;
	}

	public TIngrediente mostrarIngrediente(Integer Id) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction trans;
		try {
			trans = tm.nuevaTransaccion();
		} catch(Exception e) {
			trans = tm.getTransaccion();
		}
		TIngrediente aux=null;
		try {
			trans.start();
			aux=daoIngrediente.readById(Id);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return aux;
	}

	public Integer bajaIngrediente(Integer Id) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction trans;
		try {
			trans = tm.nuevaTransaccion();
		} catch(Exception e) {
			trans = tm.getTransaccion();
		}
		int del = 1;
		try {

			if (Id > 0) {
				trans.start();

				if (daoIngrediente.readById(Id) == null)
					return Evento.ID_NO_EXISTENTE;
				if (!daoIngrediente.readById(Id).getActivo())
					return Evento.YA_INACTIVO;
				del = daoIngrediente.delete(Id);

			if (del == -1) trans.rollback();
			else trans.commit();
			}
		} catch (Exception e) {
			return -1;
		}

		return del;
	}

	public List<TIngrediente> listarIngredientes() {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction trans;
		try {
			trans = tm.nuevaTransaccion();
		} catch(Exception e) {
			trans = tm.getTransaccion();
		}
		List<TIngrediente> lista=null;
		try {
			trans.start();
			lista=daoIngrediente.readAll();
			trans.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	public Integer actualizarIngrediente(TIngrediente tIngrediente) {
		int mod = 0;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction trans;
		try {
			trans = tm.nuevaTransaccion();
		} catch(Exception e) {
			trans = tm.getTransaccion();
		}
		try {
			if (tIngrediente != null) {
				trans.start();
				String tipo = tIngrediente.getTipo();
				if (daoIngrediente.readById(tIngrediente.getId()) == null) {
					trans.rollback();
					return Evento.ID_NO_EXISTENTE;
				}
				if (daoIngrediente.readByCodigo(tIngrediente.getNombre()) != null
						&& daoIngrediente.readByCodigo(tIngrediente.getNombre()).getActivo()){
					trans.rollback();
					return Evento.NOMBRE_EXISTENTE;
				}
				if (daoIngrediente.readById(tIngrediente.getId()).getTipo() != tipo) {
					trans.rollback();
					return Evento.TIPO_EXISTENTE;
				}
				mod = daoIngrediente.update(tIngrediente);
				trans.commit();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return mod;
	}

	
}