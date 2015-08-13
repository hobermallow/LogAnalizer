package it.uniroma1.lcl.dietrolequinte.cmdline;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import it.uniroma1.lcl.dietrolequinte.Utente;
import it.uniroma1.lcl.dietrolequinte.exception.EndProgramException;
import it.uniroma1.lcl.dietrolequinte.search.SearchResult;
import it.uniroma1.lcl.dietrolequinte.search.Searcher;

public class DietroLeQuinte {
	
	static private String nomeDirectory;
	static private Searcher searcher;
	
	public DietroLeQuinte(String nomeDirectory) throws EndProgramException {
		this.nomeDirectory = nomeDirectory;
		searcher = Searcher.getIstanza(nomeDirectory);
	}
	
	private static void cicloProgramma() throws IOException, ClassNotFoundException {
		
		for(;;) {
			List<String> l = prendiInput();
			/*
			 * Verifica il caso di uscita dal programma
			 */
			if(l.get(0).equals("exit")) return;
			analizzaInput(l);
			
		}
		
	}
	
	private static void analizzaInput(List<String> l) throws ClassNotFoundException {
		if(l.get(0).contains("num")) {
			richiestaNum(l);
		}
		else if(l.get(0).contains("data")) {
			richiestaData(l);
		}
		else richiestaGenerica(l);
	}

	private static void richiestaGenerica(List<String> l) {
	
	}

	private static void richiestaData(List<String> l) {
		
	}

	private static void richiestaNum(List<String> l) throws ClassNotFoundException {
		Collection<SearchResult> list;
		if(l.size()==2) {
			list = searcher.search(new Utente(l.get(1)), l.get(0).split("_")[1]);
		}
		else if(l.size()==4) {
			list = searcher.search(new Utente(l.get(1)), l.get(0).split("_")[1], LocalDateTime.of(LocalDate.parse(l.get(2)), LocalTime.MIN), LocalDateTime.of(LocalDate.parse(l.get(3)), LocalTime.MIN));
		}
		else if(l.get(0).contains("query")){
			if(l.size()==3) {
				list = searcher.search(new Utente(l.get(1)), "query");
			}
			else {
				list = searcher.search(new Utente(l.get(1)), "query", LocalDateTime.of(LocalDate.parse(l.get(3)), LocalTime.MIN), LocalDateTime.of(LocalDate.parse(l.get(4)), LocalTime.MIN));
			}
			list = list.stream().filter(s -> (Query)(s.getInterrogazione()).)
		}
		System.out.println(list.size());
	}

	private static List<String> prendiInput() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		return Arrays.asList(br.readLine().split("\\s+"));
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		try {
			DietroLeQuinte dlq = new DietroLeQuinte(args[0]);
		} catch (EndProgramException e) {
			e.printStackTrace();
			return;
		}
		cicloProgramma();
		
		
		
	}
}
