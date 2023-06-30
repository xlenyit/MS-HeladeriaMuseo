package integraci�n.cliente;

import java.util.Collection;

import negocio.cliente.TCliente;

public interface DAOCliente {

	public int create(TCliente tCliente);

	public int delete(int id);

	public int deleteFisico(int id);

	public TCliente read(int id);

	public Collection<TCliente> readAll();

	public int update(TCliente tCliente);

	public TCliente readByName(String dni);
}