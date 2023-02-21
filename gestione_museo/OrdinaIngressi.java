package gestione_museo;

import java.util.Comparator;

public class OrdinaIngressi implements Comparator<Ingressi> {

	@Override
	public int compare(Ingressi i1, Ingressi i2) {
		return i1.getDataAcquisto().compareTo(i2.getDataAcquisto());
	}

}
