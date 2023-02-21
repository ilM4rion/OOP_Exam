package gestione_museo;

import java.util.TreeMap;

public class Sala {

	private String cod;
	private String nome;
	private int piano;
	private int numMaxOgg;
	
	private TreeMap<Integer, Oggetto > mappaEsposti = new TreeMap<>();

	public Sala(String cod, String nome, int piano, int numMassimoOggetti) {
		this.cod = cod;
		this.nome = nome;
		this.piano = piano;
		this.numMaxOgg = numMassimoOggetti;
	}

	public String getCodiceSala() {
		return cod;
	}

	public String getNomeSala() {
		return nome;
	}

	public int getPiano() {
		return piano;
	}

	public int getNumMassimoOggetti() {
		return numMaxOgg;
	}
	
	public boolean isEsponibile(){
		// c'è spazio
		if (numMaxOgg>mappaEsposti.size())
			return true;
		return false;
	}
	
	public void esponi(Oggetto o){
		mappaEsposti.put(o.getNumeroInventario(), o);
	}

	public TreeMap<Integer, Oggetto> getMappaEsposti() {
		return mappaEsposti;
	}
	
	
	
}
