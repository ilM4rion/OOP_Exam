package gestione_museo;

public class Oggetto {

	private String tipo;
	private int cod;
	private String bio;
	private String mat;
	private String dim;
	
	private Sala s;

	public Oggetto(int codO, String tipo, String descrizione, String dimensioni, String materiali) {
		this.cod = codO;
		this.tipo = tipo;
		this.bio = descrizione;
		this.dim = dimensioni;
		this.mat = materiali;
	}

	public int getNumeroInventario() {
		return cod;
	}

	public String getTipo() {
		return tipo;
	}

	public String getDescrizione() {
		return bio;
	}

	public String getDimensioni() {
		return dim;
	}

	public String getMateriali() {
		return mat;
	}

	public Sala getSala() {
		return s;
	}

	public void setSala(Sala s) {
		this.s = s;
	} 
	
	
	
}
