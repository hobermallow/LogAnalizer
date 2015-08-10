package it.uniroma1.lcl.dietrolequinte.loader.query;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import it.uniroma1.lcl.dietrolequinte.Utente;
import it.uniroma1.lcl.dietrolequinte.loader.AbstractLoader;

public class QueryLoader extends AbstractLoader {



	public QueryLoader(File file) throws IOException {
		super(file);
	}

	@Override
	protected List<String> getList() {
		return null;
	}

	@Override
	protected void inizializzaLoader(List<String> list) {
		list.forEach(System.out::println);
		list.parallelStream().map(s -> Arrays.asList(s.split("\t"))).forEach(this::analizzaRiga);
	}

	@Override
	protected void analizzaRiga(List<String> riga) {
		if(riga.size() > 3) {
			addInterrogazione(new InterrogazioneQuery(new Utente(riga.get(0)), String.join("\t", riga), LocalDateTime.now() , riga.get(4), Integer.getInteger(riga.get(3))));	
	
		}
		else {
			addInterrogazione(new InterrogazioneQuery(new Utente(riga.get(0)), String.join("\t", riga), LocalDateTime.now()));		
		}
	}
	
	public static void main(String[] args) throws IOException {
		File f = new File("/home/onoda/Documents/progetto_metodologie2015/AOL/query.user-ct-test-collection-02.txt");
		QueryLoader ql = new QueryLoader(f);
		System.out.println(ql.getInterrogazioni().get(0));
	}

	

}
