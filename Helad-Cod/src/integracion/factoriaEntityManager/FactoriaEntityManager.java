package integracion.factoriaEntityManager;

import jakarta.persistence.EntityManagerFactory;

public abstract class FactoriaEntityManager {
	
	private static FactoriaEntityManager instance;
	private String NAME_ENTITY_MANAGER;
	public static synchronized FactoriaEntityManager getInstance() {
		if (instance == null) {
			instance = new FactoriaEntityManagerImp();
		}
		return instance;
	}

	public abstract EntityManagerFactory getEntityManagerFactory();
	public abstract void finalize();
}