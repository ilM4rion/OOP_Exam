package gestione_museo;

public class Ingressi extends Servizio {
	
	private int NumIngressiInteri;
	private int NumIngressiRidotti;
	private double sconto;

	public Ingressi(String emailAcquirente, String dataAcquisto,double prezzoIndividuale, int numIngressiInteri,int numIngressiRidotti, double sconto) {
		super(emailAcquirente, dataAcquisto, prezzoIndividuale);
		this.NumIngressiInteri = numIngressiInteri;
		this.NumIngressiRidotti = numIngressiRidotti;
		this.sconto = sconto;
	}

	public int getNumIngressiInteri() {
		return this.NumIngressiInteri;
	}

	public int getNumIngressiRidotti() {
		return this.NumIngressiRidotti;
	}

	public double getSconto() {
		return this.sconto;
	}
	
}
