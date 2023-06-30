package integración.miembrosdecompañia;

import java.util.Collection;

import negocio.miembrosdecompañia.TMiembrosDeCompañia;

public interface DAOMiembrosDeCompañia {

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tMiembrosDeCompañia
	* @param Parameter1
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void create(TMiembrosDeCompañia tMiembrosDeCompañia, int Parameter1);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @param Parameter1
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void delete(int id, int Parameter1);

	public int create(TMiembrosDeCompañia tMiembrosDeCompañia);

	public int delete(int id);

	public TMiembrosDeCompañia read(int id);

	public Collection<TMiembrosDeCompañia> readAll();

	public int update(TMiembrosDeCompañia tMiembrosDeCompañia);

	public TMiembrosDeCompañia readByName(String nombre);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idMiembro
	* @param idCompañia
	* @return
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int addToCompany(int idMiembro, int idCompañia);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idMiembro
	* @param idCompañia
	* @return
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int removeFromCompany(int idMiembro, int idCompañia);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idCompañia
	* @param idMiembro
	* @param numMeses
	* @return
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int updateMeses(int idCompañia, int idMiembro, int numMeses);

	public int deleteFisico(int idMiembro);
}