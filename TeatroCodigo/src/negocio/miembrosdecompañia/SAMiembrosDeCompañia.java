package negocio.miembrosdecompañia;

import java.util.Collection;

public interface SAMiembrosDeCompañia {
	public int create(TMiembrosDeCompañia tMiembrosDeCompañia);

	public TMiembrosDeCompañia read(int id);

	public int update(TMiembrosDeCompañia tMiembrosDeCompañia);

	public int delete(int id);

	public Collection<TMiembrosDeCompañia> readAll();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idMiembro
	* @param idCompañia
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int addToCompany(int idMiembro, int idCompañia);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idCompañia
	* @param idMiembro
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int removeFromCompany(int idCompañia, int idMiembro);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idMiembro
	* @param idCompañia
	* @param numMeses
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int updateMeses(int idMiembro, int idCompañia, int numMeses);

	public int addToCompany(TLineaMiembro tLineaMiembro);

	public int removeFromCompany(TLineaMiembro tLineaMiembro);

	public int updateMeses(TLineaMiembro tLineaMiembro);

	public int deleteFisico(int id);
}