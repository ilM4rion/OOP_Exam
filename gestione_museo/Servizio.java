package gestione_museo;

public class Servizio{
	
	

	private String emailAcquirente;
	private String dataAcquisto;
	private double prezzoIndividuale;
	
	
	public Servizio(String emailAcquirente, String dataAcquisto,
			double prezzoIndividuale) {
		super();
		this.emailAcquirente = emailAcquirente;
		this.dataAcquisto = dataAcquisto;
		this.prezzoIndividuale = prezzoIndividuale;
	}


	public String getEmailAcquirente() {
		return this.emailAcquirente;
	}

	public String getDataAcquisto() {
		return this.dataAcquisto;
	}

	public double getPrezzoIndividuale() {
		return this.prezzoIndividuale;
	}
	
	
}
