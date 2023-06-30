package integraci�n.miembrosdecompa�ia;

import java.util.Collection;

import negocio.miembrosdecompa�ia.TMiembrosDeCompa�ia;

public interface DAOMiembrosDeCompa�ia {

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tMiembrosDeCompa�ia
	* @param Parameter1
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void create(TMiembrosDeCompa�ia tMiembrosDeCompa�ia, int Parameter1);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @param Parameter1
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void delete(int id, int Parameter1);

	public int create(TMiembrosDeCompa�ia tMiembrosDeCompa�ia);

	public int delete(int id);

	public TMiembrosDeCompa�ia read(int id);

	public Collection<TMiembrosDeCompa�ia> readAll();

	public int update(TMiembrosDeCompa�ia tMiembrosDeCompa�ia);

	public TMiembrosDeCompa�ia readByName(String nombre);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idMiembro
	* @param idCompa�ia
	* @return
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int addToCompany(int idMiembro, int idCompa�ia);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idMiembro
	* @param idCompa�ia
	* @return
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int removeFromCompany(int idMiembro, int idCompa�ia);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idCompa�ia
	* @param idMiembro
	* @param numMeses
	* @return
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int updateMeses(int idCompa�ia, int idMiembro, int numMeses);

	public int deleteFisico(int idMiembro);
}