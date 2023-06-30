package negocio.miembrosdecompañia;

public class TLineaMiembro {
	
	private int idCompañia;
	
	private int idMiembro;

	private int meses;

	public TLineaMiembro(int idComp, int idMiem, int meses) {
		idCompañia = idComp;
		idMiembro = idMiem;
		this.meses = meses;
	}

	public int getIdCompañia() {
		return this.idCompañia;
	}
	
	public void setIdCompañia(int idCompañia) {
		this.idCompañia = idCompañia;
	}

	public int getIdMiembro() {
		return this.idMiembro;
	}
	
	public void setIdMiembro(int idMiembro) {
		this.idMiembro = idMiembro;
	}

	public int getNMeses() {
		return this.meses;
	}
	
	public void setNMeses(int meses) {
		this.meses = meses;
	}


}