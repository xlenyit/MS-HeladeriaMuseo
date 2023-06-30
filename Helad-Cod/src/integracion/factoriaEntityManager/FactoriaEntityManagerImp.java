
package integracion.factoriaEntityManager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class FactoriaEntityManagerImp extends FactoriaEntityManager {
	private EntityManagerFactory entityManagerFactory;
	public FactoriaEntityManagerImp(){
		entityManagerFactory = Persistence.createEntityManagerFactory("heladeria-cod");
		
	}

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	@Override
	public void finalize() {
		this.entityManagerFactory.close();
	}
}


	