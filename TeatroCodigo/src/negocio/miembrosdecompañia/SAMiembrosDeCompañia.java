package negocio.miembrosdecompa�ia;

import java.util.Collection;

public interface SAMiembrosDeCompa�ia {
	public int create(TMiembrosDeCompa�ia tMiembrosDeCompa�ia);

	public TMiembrosDeCompa�ia read(int id);

	public int update(TMiembrosDeCompa�ia tMiembrosDeCompa�ia);

	public int delete(int id);

	public Collection<TMiembrosDeCompa�ia> readAll();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idMiembro
	* @param idCompa�ia
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int addToCompany(int idMiembro, int idCompa�ia);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idCompa�ia
	* @param idMiembro
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int removeFromCompany(int idCompa�ia, int idMiembro);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idMiembro
	* @param idCompa�ia
	* @param numMeses
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int updateMeses(int idMiembro, int idCompa�ia, int numMeses);

	public int addToCompany(TLineaMiembro tLineaMiembro);

	public int removeFromCompany(TLineaMiembro tLineaMiembro);

	public int updateMeses(TLineaMiembro tLineaMiembro);

	public int deleteFisico(int id);
}