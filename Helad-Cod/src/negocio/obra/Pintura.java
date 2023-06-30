package negocio.obra;

import jakarta.persistence.Entity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(name = "negocio.obra.Pintura.findBytipo", query = "select obj from Pintura obj where :tipo = obj.tipo ")
public class Pintura extends Obra implements Serializable {

	private static final long serialVersionUID = 0;
	
	public static final Map<String, Double> PORCENTAJE_PINTURAS = new HashMap<String, Double>();
	static {
		PORCENTAJE_PINTURAS.put("Oleo", 2.0);
		PORCENTAJE_PINTURAS.put("Acuarela", 1.5);
	}
	private String tipo;
	
	public Map<String, Double> getPorcentaje(){
		return PORCENTAJE_PINTURAS;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Pintura() {

	}

	public Pintura(TPintura p) {
		super(p);
		tipo = this.getTipo();
	}

	public TPintura toTransfer() {
		return new TPintura(super.getId(), tipo, super.getActivo(), super.getNombre(), super.getAutor(),
				super.getCoste(), super.getExposicion().getId());

	}

	@Override
	public double calcularCoste() {
		return coste * PORCENTAJE_PINTURAS.get(tipo);
	}
}