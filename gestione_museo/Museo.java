package gestione_museo;

import java.util.*;

public class Museo {
	
	private TreeMap<Integer, Oggetto > mappaOggetti = new TreeMap<>();
	private TreeMap<String, Sala > mappaSale = new TreeMap<>();
	private TreeMap<String, Integer> mappaDisponibilitaAudioguide = new TreeMap<>();
	private LinkedList<Servizio> listaServizi = new LinkedList<>();
	private LinkedList<Ingressi> listaIngressi = new LinkedList<>();
	private LinkedList<Audioguide> listaAudioguide = new LinkedList<>();
	
	private int codO = 1;
	
	private String nome;
	private String tipologia;
	private String citta;
	private String sito;
	private int numeroAudioguide;
	

	public void configuraMuseo(String nome, String tipologia, String citta, String sito, int numeroAudioguide) {
		this.nome = nome;
		this.tipologia = tipologia;
		this.citta = citta;
		this.sito = sito;
		this.numeroAudioguide = numeroAudioguide;
	}
	
	public String informazioniMuseo() {
		return nome+"\n"+tipologia+"\n"+citta+"\n"+sito+"\n"+numeroAudioguide;
	}
	
	public int aggiungiOggetto(String tipo, String descrizione, String dimensioni, String materiali) {
		Oggetto o = new Oggetto(codO, tipo, descrizione, dimensioni, materiali);
		mappaOggetti.put(codO, o);
		codO++;
		return codO-1;
	}
	
	public Oggetto cercaOggettoPerNumeroInventario(int numeroInventario) {
		return mappaOggetti.get(numeroInventario);
	}
	
	public Collection<Oggetto> elencoOggetti() {
		return new LinkedList<>(mappaOggetti.values());
	}
	
	public Collection<Oggetto> elencoOggetti(String daCercare) {
		LinkedList<Oggetto> temp = new LinkedList<>();
		daCercare = daCercare.toLowerCase();
		for (Oggetto o : mappaOggetti.values()) {
			if (o.getDescrizione().toLowerCase().contains(daCercare) || o.getDimensioni().toLowerCase().contains(daCercare) || o.getMateriali().toLowerCase().contains(daCercare) || o.getTipo().toLowerCase().contains(daCercare)){
				temp.add(o);
			}
		}
		return temp;
	}
	
	public String aggiungiSala(String nome, int piano, int numMassimoOggetti) {
		String cod = nome.toUpperCase();
		int cc = 0;
		for (String s : mappaSale.keySet()){
			if (s.contains(cod))
				cc++;
		}
		cod = cod+"-"+(cc+1);
		Sala sala = new Sala(cod, nome, piano, numMassimoOggetti);
		mappaSale.put(cod, sala);
		return cod;
	}

	public Collection<Sala> elencoSale() {
		LinkedList<Sala> temp = new LinkedList<>(mappaSale.values());
		Collections.sort(temp, new OrdinaSale());
		return temp;
	}
	
	public boolean esponiOggettoInSala(int numeroInventario, String codiceSala) {
		Sala sala = mappaSale.get(codiceSala);
		Oggetto o = mappaOggetti.get(numeroInventario);
		boolean suc = false;
		
		if (sala!=null && o!=null){
			suc = sala.isEsponibile();
			if (suc) {
				sala.esponi(o);
				o.setSala(sala);
			}
				
		}
		return suc;
	}
	
	public String numeriInventarioOggettiInSala(String codiceSala) {
		Sala s = mappaSale.get(codiceSala);
		String ret = "";
		for (Oggetto o : s.getMappaEsposti().values()){
			if (ret.equals(""))
				ret = ""+o.getNumeroInventario();
			else ret = ret +" "+o.getNumeroInventario();
		}
		if (ret.compareTo("")==0)
			ret = null;
		return ret;
	}

	public String codiceSalaOggetto(int numeroInventario) {
		
		Oggetto o = mappaOggetti.get(numeroInventario);
		String ret = null;
		if (o!=null) {
			Sala s = o.getSala();
			if (s!=null) 
				ret = s.getCodiceSala();
		}
			
			
			
		
		return ret;
	}
	
	public Servizio acquistaServizio(String emailAcquirente, String dataAcquisto, double prezzoIndividuale, int numIngressiInteri, int numIngressiRidotti, double sconto) throws EccezioneSuperatoNumeroAcquistiGiornalieri {
		
		Ingressi i = new Ingressi(emailAcquirente, dataAcquisto, prezzoIndividuale, numIngressiInteri, numIngressiRidotti, sconto);
		
		for (Ingressi ing : listaIngressi){
			if (ing.getDataAcquisto().compareTo(dataAcquisto)==0 && ing.getEmailAcquirente().compareTo(emailAcquirente)==0)
				throw new EccezioneSuperatoNumeroAcquistiGiornalieri();
		}
		listaIngressi.add(i);
		listaServizi.add(i);
		
		return (Servizio)i;
	}
	
	public Servizio acquistaServizio(String emailAcquirente, String dataAcquisto, double prezzoIndividuale, int numAudioGuide, String dataVisita) throws EccezioneSuperatoNumeroAudioguide, EccezioneSuperatoNumeroAcquistiGiornalieri {
		Audioguide i = new Audioguide(emailAcquirente, dataAcquisto, prezzoIndividuale, dataVisita, numAudioGuide);
		if (mappaDisponibilitaAudioguide.get(dataVisita)==null)
			mappaDisponibilitaAudioguide.put(dataVisita, numeroAudioguide);
		for (Audioguide a : listaAudioguide){
			if (a.getEmailAcquirente().compareTo(emailAcquirente)==0 && a.getDataAcquisto().compareTo(dataAcquisto)==0)
				throw new EccezioneSuperatoNumeroAcquistiGiornalieri();
		}
		boolean suc = i.prenota(numAudioGuide, mappaDisponibilitaAudioguide.get(dataVisita));
		if (suc == false)
			throw new EccezioneSuperatoNumeroAudioguide();
		mappaDisponibilitaAudioguide.put(dataVisita, numeroAudioguide-numAudioGuide);
		listaAudioguide.add(i);
		listaServizi.add(i);
		
		return (Servizio)i;
	}
	
	public Collection<Servizio>elencoServiziAcquistati() {
		LinkedList<Servizio> temp = new LinkedList<>();
		Collections.sort(listaIngressi, new OrdinaIngressi());
		Collections.sort(listaAudioguide, new OrdinaAudioGuide());
		temp.addAll(listaIngressi);
		temp.addAll(listaAudioguide);
		return temp;
	}
	
	public double incassoPerIngressiDaAcquirenteInData(String email, String dataAcquisto) {
		double tot = 0.0;
		double prezzo = 0;
		int interi = 0;
		int ridotti = 0;
		double sconto = 0.0;
		for (Servizio s : listaServizi){
			if (s instanceof Ingressi && s.getEmailAcquirente().compareTo(email)==0 && s.getDataAcquisto().compareTo(dataAcquisto)==0){
				prezzo = s.getPrezzoIndividuale();
				interi = ((Ingressi) s).getNumIngressiInteri();
				ridotti = ((Ingressi) s).getNumIngressiRidotti();
				sconto = ((Ingressi) s).getSconto();
				tot += prezzo * interi + prezzo * (1-(sconto/100)) * ridotti;
			}
		}
		return tot;
	}
	
	
	
	public double incassoPerAudioguideDaAcquirenteInData(String email, String dataAcquisto) {
		double tot = 0.0;
		double prezzo = 0;
		int numeroAudioguide = 0;
		for (Servizio s : listaServizi){
			if (s instanceof Audioguide && s.getEmailAcquirente().compareTo(email)==0 && s.getDataAcquisto().compareTo(dataAcquisto)==0){
				prezzo = s.getPrezzoIndividuale();
				numeroAudioguide = ((Audioguide) s).getNumeroAudioguideAcquistate();
				
				tot += prezzo * numeroAudioguide;
			}
		}
		return tot;
	}
	
	public double incassoPerData(String dataAcquisto) {
		double tot = 0.0;
		double prezzo = 0;
		int interi = 0;
		int ridotti = 0;
		double sconto = 0.0;
		for (Servizio s : listaServizi){
			if (s instanceof Ingressi &&  s.getDataAcquisto().compareTo(dataAcquisto)==0){
				prezzo = s.getPrezzoIndividuale();
				interi = ((Ingressi) s).getNumIngressiInteri();
				ridotti = ((Ingressi) s).getNumIngressiRidotti();
				sconto = ((Ingressi) s).getSconto();
				tot += prezzo * interi + prezzo * (1-(sconto/100)) * ridotti;
			}
			if (s instanceof Audioguide && s.getDataAcquisto().compareTo(dataAcquisto)==0){
				prezzo = s.getPrezzoIndividuale();
				
				tot += prezzo * ((Audioguide) s).getNumeroAudioguideAcquistate();
			}
		}
		return tot;
	}
	
}





