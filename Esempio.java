import java.util.*;

import gestione_museo.*;

public class Esempio {

	public static void main(String[] args) throws EccezioneSuperatoNumeroAudioguide, EccezioneSuperatoNumeroAcquistiGiornalieri {
				
		System.out.println("/******************************/");
		System.out.println("/**   R1. MUSEO ED OGGETTI   **/");
		System.out.println("/******************************/\n");
		
		Museo m = new Museo();
		
		System.out.println("Configurazione museo\n");
		
		m.configuraMuseo("Museo Egizio di Torino", "Collezioni Egizie", "Torino", "https://museoegizio.it/", 50);
		
		System.out.println("Informazioni museo:\n");
		
		String info = m.informazioniMuseo();
		
		System.out.println(info);
		
		System.out.println("\nAggiunta di un oggetto alla collezione\n");

		int num = m.aggiungiOggetto("Statua", "Statua di Ramesse II", "196 x 70 x 105", "Pietra / Granodiorite");

		System.out.println("Numero di inventario dell'oggetto aggiunto:\n");
		System.out.println(num);

		System.out.println("\nRicerca oggetto con numero di inventario '1'\n");
		
		Oggetto o = m.cercaOggettoPerNumeroInventario(1);
		
		System.out.println("Tipologia: "+o.getTipo());
		System.out.println("Descrizione: "+o.getDescrizione());
		System.out.println("Dimensioni: "+o.getDimensioni());
		System.out.println("Materiali: "+o.getMateriali());
		
		System.out.println("\nAggiunta di altri oggetti alla collezione (ordine di numero di inv.)\n");
		m.aggiungiOggetto("Papiro", "Papiro dei Re", "40 x 183", "Cyperus papyrus / Inchiostro");
		m.aggiungiOggetto("Amuleto", "Amuleto raffigurante il pilastro Djed", "13 x 5.5 x 1", "Legno dorato / Vetro / Pigmento blu");
		m.aggiungiOggetto("Stele", "Stele di Huy", "45.5 x 31 x 4", "Pietra / Calcare");

		System.out.println("Elenco oggetti nella collezione:\n");

		LinkedList<Oggetto> listaOggetti = new LinkedList<Oggetto>(m.elencoOggetti());
		for(Oggetto oTemp : listaOggetti) {
			System.out.println(oTemp.getNumeroInventario()+", "+oTemp.getTipo()+", "+oTemp.getDescrizione()+", "+oTemp.getDimensioni()+", "+oTemp.getMateriali());
		}

		System.out.println("\nElenco oggetti che contengono 'Pilastro' (ordine di numero di inv.):\n");

		                    listaOggetti = new LinkedList<Oggetto>(m.elencoOggetti("Pilastro"));
		for(Oggetto oTemp : listaOggetti) {
			System.out.println(oTemp.getNumeroInventario()+", "+oTemp.getTipo()+", "+oTemp.getDescrizione()+", "+oTemp.getDimensioni()+", "+oTemp.getMateriali());
		}
		
		System.out.println("\n");
		System.out.println("/******************************/");
		System.out.println("/**         R2. SALE         **/");
		System.out.println("/******************************/\n");

		System.out.println("Aggiunta sala\n");
		
		String codiceSala = m.aggiungiSala("Ramesse II", 1, 10);
		m.aggiungiSala("Ramesse II", 1, 10);
		m.aggiungiSala("Ramesse II", 1, 10);
		m.aggiungiSala("Ramesse II", 4, 10);
		
		System.out.println("Codice sala aggiunta:\n");
		System.out.println(codiceSala);

		System.out.println("\nAggiunte altre sale\n");
		m.aggiungiSala("Kha", 3, 10);
		m.aggiungiSala("Kha", 1, 10);
		
		System.out.println("Elenco sale (ordine di piano e codice sala):\n");

		LinkedList<Sala> listaSale = new LinkedList<Sala>(m.elencoSale());
		for(Sala sTemp : listaSale) {
			System.out.println(sTemp.getCodiceSala()+", "+sTemp.getNomeSala()+", piano "+sTemp.getPiano()+", "+sTemp.getNumMassimoOggetti());
		}

		System.out.println("\nEsposizione dell'oggetto '1' nella sala 'RAMESSE II-1'\n");
		
		boolean risultato = m.esponiOggettoInSala(1, "RAMESSE II-1");
		if(risultato==true)
			System.out.println("Esposizione avvenuta con successo");
		else
			System.out.println("Errore nell'esposizione !!!");
			
		System.out.println("\nEsposizione di altri oggetti nella sala\n");
		m.esponiOggettoInSala(2, "RAMESSE II-1");

		System.out.println("Oggetti epsosti nella sala 'RAMESSE II-1' (ordine di numero di inv.)\n");
		
		String stringaNumeriInventario = m.numeriInventarioOggettiInSala("RAMESSE II-1");
		System.out.println(stringaNumeriInventario);
		
		System.out.println("\nSala in cui è esposto l'oggetto '1'\n");

		codiceSala = m.codiceSalaOggetto(1);
		System.out.println(codiceSala);
		
		
		System.out.println("\n");
		System.out.println("/******************************/");
		System.out.println("/**   R3. ACQUISTO SERVIZI   **/");
		System.out.println("/******************************/\n");
		
		System.out.println("Acquisto di 3 ingressi interi ed 1 ridotto, prezzo ingresso singolo 10 euro, sconto ridotto 20%\n");
		try{ m.acquistaServizio("mario.rossi@gmail.com", "20230202", 10, 3, 1, 20); }
		catch(Exception e) { System.out.println("Errore nell'acquisto del servizio !!!\n"); }
		
		System.out.println("Acquisto di 2 audioguide, prezzo singolo 25 euro, per il 20230425\n");
		try{ m.acquistaServizio("mario.rossi@gmail.com", "20230202", 25, 2, "20230425"); }
		catch(Exception e) { System.out.println("Errore nell'acquisto del servizio !!!\n"); }

		System.out.println("Acquisto di altri servizi\n");
		try{ m.acquistaServizio("gianni.verdi@yahoo.com", "20230126", 20, 1, 0, 0); 
		     m.acquistaServizio("luisa.neri@tim.it", "20230202", 40, 1, "20230522");}
		catch(Exception e) { System.out.println("Errore nell'acquisto del servizio !!!\n"); }
		
		
		System.out.println("Elenco servizi acquistati:\n");
		
		LinkedList<Servizio> elencoServizi = new LinkedList<Servizio>(m.elencoServiziAcquistati());
		for(Servizio sTemp : elencoServizi) {
			if(sTemp instanceof Ingressi) {
				Ingressi iTemp = (Ingressi)sTemp;
				System.out.println("Ingressi, "+iTemp.getEmailAcquirente()+", "+iTemp.getDataAcquisto()+", "+iTemp.getPrezzoIndividuale()+", "+iTemp.getNumIngressiInteri()+" "+iTemp.getNumIngressiRidotti()+", "+iTemp.getSconto());
			}
			if(sTemp instanceof Audioguide) {
				Audioguide aTemp = (Audioguide)sTemp;
				System.out.println("Audioguide, "+aTemp.getEmailAcquirente()+", "+aTemp.getDataAcquisto()+", "+aTemp.getPrezzoIndividuale()+", "+aTemp.getNumeroAudioguide()+", "+aTemp.getDataVisita());
			}
		}

		System.out.println("\n");
		System.out.println("/******************************/");
		System.out.println("/**        R4. INCASSI       **/");
		System.out.println("/******************************/\n");

		System.out.println("Incasso ingressi acquistati da mario.rossi@gmail.com in data 20230202:\n");

		double incasso = m.incassoPerIngressiDaAcquirenteInData("mario.rossi@gmail.com", "20230202");
		System.out.println(incasso+" euro");

		System.out.println("\nIncasso audioguide acquistate da mario.rossi@gmail.com in data 20230202:\n");

		       incasso = m.incassoPerAudioguideDaAcquirenteInData("mario.rossi@gmail.com", "20230202");
		System.out.println(incasso+" euro");

		System.out.println("\nIncasso in data 20230202:\n");

		       incasso = m.incassoPerData("20230202");
		System.out.println(incasso+" euro");

	}
}

