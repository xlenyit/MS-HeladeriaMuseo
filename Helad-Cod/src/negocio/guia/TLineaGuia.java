package negocio.guia;

public class TLineaGuia {

	private int idGuia;
	private int idExpo;
	private Integer horaIni;
	
	public TLineaGuia(int idGuia, int idExpo, Integer horaIni) {
		super();
		this.idGuia = idGuia;
		this.idExpo = idExpo;
		this.horaIni = horaIni;
	}
	public TLineaGuia() {
		
	}

	public int getIdGuia() {
		return idGuia;
	}

	public void setIdGuia(int idGuia) {
		this.idGuia = idGuia;
	}

	public int getIdExpo() {
		return idExpo;
	}

	public void setIdExpo(int idExpo) {
		this.idExpo = idExpo;
	}

	public Integer getHoraIni() {
		return horaIni;
	}

	public void setHoraIni(Integer horaIni) {
		this.horaIni = horaIni;
	}


}
