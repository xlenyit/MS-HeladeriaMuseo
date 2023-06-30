/**
 * 
 */

package negocio.obra;

import jakarta.persistence.Entity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.NamedQuery;


@Entity
@NamedQuery(name = "negocio.obra.Escultura.findBymaterial", query = "select obj from Escultura obj where :material = obj.material ")
public class Escultura extends Obra implements Serializable {

	private static final long serialVersionUID = 0;
	public static final Map<String, Double> PORCENTAJE_ESCULTURAS = new HashMap<String, Double>();
	static{
		
		PORCENTAJE_ESCULTURAS.put("Marmol", 3.0);
		PORCENTAJE_ESCULTURAS.put("Madera", 1.2);
		PORCENTAJE_ESCULTURAS.put("Arcilla", 1.5);
	}
	private String material;
	public Map<String, Double> getPorcentaje(){
		return PORCENTAJE_ESCULTURAS;
	}

	public String getMaterial() {
		
		return material;
	}

	public void setMaterial(String material) {
		this.material=material;
	}

	public Escultura() {
		
	}
	public Escultura(TEscultura e) {
		super(e);
		this.material=e.getMaterial();
	}
	public TEscultura toTransfer()
	{
		return new TEscultura(super.getId(),material,super.getActivo(),super.getNombre(),super.getAutor(),super.getCoste(),super.getExposicion().getId());

	}
	@Override
	public double calcularCoste() {
		return coste*PORCENTAJE_ESCULTURAS.get(material);
	}
}