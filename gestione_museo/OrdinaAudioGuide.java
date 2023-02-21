package gestione_museo;

import java.util.Comparator;

public class OrdinaAudioGuide implements Comparator<Audioguide> {

	@Override
	public int compare(Audioguide a1, Audioguide a2) {
		
		return a1.getDataAcquisto().compareTo(a2.getDataAcquisto());
	}

}
