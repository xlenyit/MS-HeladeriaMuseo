package integraciķn.miembrosdecompaņia;

import java.util.Collection;

import negocio.miembrosdecompaņia.TMiembrosDeCompaņia;

public interface DAOMiembrosDeCompaņia {

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tMiembrosDeCompaņia
	* @param Parameter1
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void create(TMiembrosDeCompaņia tMiembrosDeCompaņia, int Parameter1);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @param Parameter1
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void delete(int id, int Parameter1);

	public int create(TMiembrosDeCompaņia tMiembrosDeCompaņia);

	public int delete(int id);

	public TMiembrosDeCompaņia read(int id);

	public Collection<TMiembrosDeCompaņia> readAll();

	public int update(TMiembrosDeCompaņia tMiembrosDeCompaņia);

	public TMiembrosDeCompaņia readByName(String nombre);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idMiembro
	* @param idCompaņia
	* @return
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int addToCompany(int idMiembro, int idCompaņia);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idMiembro
	* @param idCompaņia
	* @return
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int removeFromCompany(int idMiembro, int idCompaņia);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idCompaņia
	* @param idMiembro
	* @param numMeses
	* @return
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int updateMeses(int idCompaņia, int idMiembro, int numMeses);

	public int deleteFisico(int idMiembro);
}