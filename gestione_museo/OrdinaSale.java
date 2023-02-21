package gestione_museo;

import java.util.Comparator;

public class OrdinaSale implements Comparator<Sala> {

	@Override
	public int compare(Sala s1, Sala s2) {
		if (s1.getPiano()==s2.getPiano())
			return s1.getCodiceSala().compareTo(s2.getCodiceSala());
		return s1.getPiano()-s2.getPiano();
	}

}
