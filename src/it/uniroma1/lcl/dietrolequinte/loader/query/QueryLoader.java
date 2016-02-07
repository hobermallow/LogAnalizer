package it.uniroma1.lcl.dietrolequinte.loader.query;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import it.uniroma1.lcl.dietrolequinte.Utente;
import it.uniroma1.lcl.dietrolequinte.loader.AbstractLoader;

/**
 * Classe che implementa il Loader per i file log di tipo query, per il motore di
 * ricerca AOL
 * 
 * @author Andrea
 *
 */
public class QueryLoader extends AbstractLoader {



	public QueryLoader(File file) throws IOException {
		super(file);
	}

	/* (non-Javadoc)
	 * @see it.uniroma1.lcl.dietrolequinte.loader.AbstractLoader#getListValidSearch()
	 */
	@Override
	protected List<String> getListValidSearch() {
		return Arrays.asList("interrogazione");
	}

	/* (non-Javadoc)
	 * @see it.uniroma1.lcl.dietrolequinte.loader.AbstractLoader#inizializzaLoader(java.util.List)
	 */
	@Override
	protected void inizializzaLoader(List<String> list) {
		list.remove(0);
		list.stream().filter(s -> s != null).map(s -> Arrays.asList(s.split("\t"))).forEach(this::analizzaRiga);
	}

	/* (non-Javadoc)
	 * @see it.uniroma1.lcl.dietrolequinte.loader.AbstractLoader#analizzaRiga(java.util.List)
	 */
	@Override
	protected void analizzaRiga(List<String> riga) {
		if(riga.size() == 5) {
			addInterrogazione(new Interrogazione(new Utente(riga.get(0)), riga.get(1), LocalDateTime.parse(riga.get(2).replace(" ", "T"), DateTimeFormatter.ISO_LOCAL_DATE_TIME) , riga.get(4), Integer.valueOf(riga.get(3))));	
	
		}
		else if(riga.size() == 3) {
			addInterrogazione(new Interrogazione(new Utente(riga.get(0)), riga.get(1), LocalDateTime.parse(riga.get(2).replace(" ", "T"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)));		
		}
		
		addUtente(new Utente(riga.get(0)));
	}
	@Override
	public String modifystr(String s) {
		switch(s){
			case "interrogazione":
				return "interrogazioni";
		}
		return s;
	}
}
