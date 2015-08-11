package it.uniroma1.lcl.dietrolequinte.loader.query;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import it.uniroma1.lcl.dietrolequinte.Utente;
import it.uniroma1.lcl.dietrolequinte.loader.AbstractLoader;

public class QueryLoader extends AbstractLoader {



	public QueryLoader(File file) throws IOException {
		super(file);
	}

	@Override
	protected List<String> getListValidSearch() {
		return Arrays.asList("interrogazione");
	}

	@Override
	protected void inizializzaLoader(List<String> list) {
		list.remove(0);
		list.stream().filter(s -> s != null).map(s -> Arrays.asList(s.split("\t"))).forEach(this::analizzaRiga);
	}

	@Override
	protected void analizzaRiga(List<String> riga) {
		if(riga.size() == 5) {
			addInterrogazione(new Query(new Utente(riga.get(0)), riga.get(1), LocalDateTime.parse(riga.get(2).replace(" ", "T"), DateTimeFormatter.ISO_LOCAL_DATE_TIME) , riga.get(4), Integer.valueOf(riga.get(3))));	
	
		}
		else if(riga.size() == 3) {
			addInterrogazione(new Query(new Utente(riga.get(0)), riga.get(1), LocalDateTime.parse(riga.get(2).replace(" ", "T"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)));		
		}
		
		addUtente(new Utente(riga.get(0)));
	}
	
	public static void main(String[] args) throws IOException {
		File f = new File("/home/onoda/Documents/progetto_metodologie2015/AOL/query.user-ct-test-collection-03.txt.gz");
		QueryLoader ql = new QueryLoader(f);
		ql.getInterrogazioni().forEach(System.out::println);
	}

	

}
