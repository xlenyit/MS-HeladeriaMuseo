
package integración.factoriaEntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoriaEntityManagerSingleton {

	private static EntityManagerFactory instance;

	private static String NAME_ENTITY_MANAGER = "Teatro Codigo MS";

	public static EntityManagerFactory getInstance() {
		if (instance == null) {
			instance = Persistence.createEntityManagerFactory(NAME_ENTITY_MANAGER);
		}

		return instance;
	}
	
	public void finalize(){
		if(instance != null)
			instance.close();
		
		instance = null;
	}
}