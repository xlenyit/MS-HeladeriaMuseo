package negocio.miembrosdecompa�ia;

public class TLineaMiembro {
	
	private int idCompa�ia;
	
	private int idMiembro;

	private int meses;

	public TLineaMiembro(int idComp, int idMiem, int meses) {
		idCompa�ia = idComp;
		idMiembro = idMiem;
		this.meses = meses;
	}

	public int getIdCompa�ia() {
		return this.idCompa�ia;
	}
	
	public void setIdCompa�ia(int idCompa�ia) {
		this.idCompa�ia = idCompa�ia;
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