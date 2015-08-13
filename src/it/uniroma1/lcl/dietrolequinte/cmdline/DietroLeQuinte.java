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
import it.uniroma1.lcl.dietrolequinte.loader.query.Interrogazione;;

public class DietroLeQuinte {
	
	static private String nomeDirectory;
	static private Searcher searcher;
	static private String nomeFileComandi;
	static private boolean batchModeActive = false;
	
	private DietroLeQuinte(String nomeDirectory, String nomeFileComandi) throws EndProgramException {
		this(nomeDirectory);
		this.nomeFileComandi = nomeFileComandi;

	}
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

	private static void richiestaGenerica(List<String> l) throws ClassNotFoundException {
		Collection<SearchResult> list;
		String info = l.get(0);
		if(l.size()==3) {
			list = searcher.search(new Utente(l.get(1)), info);
		}
		else if(l.size()==5) {
			LocalDateTime ldt_prima = LocalDateTime.of(LocalDate.parse(l.get(3)), LocalTime.MIN);
			LocalDateTime ldt_dopo = LocalDateTime.of(LocalDate.parse(l.get(4)), LocalTime.MIN);
			if(ldt_prima.compareTo(ldt_dopo)>0) {
				System.out.println("Date nell'ordine sbagliato");
				return;
			}
			list = searcher.search(new Utente(l.get(1)), info, ldt_prima , ldt_dopo);

		}
		else if(l.get(0).equals("interrogazione")){
			if(l.size()==4) {
				list = searcher.search(new Utente(l.get(1)), "interrogazione");
			}
			else {
				list = searcher.search(new Utente(l.get(1)), "interrogazione", LocalDateTime.of(LocalDate.parse(l.get(4)), LocalTime.MIN), LocalDateTime.of(LocalDate.parse(l.get(5)), LocalTime.MIN));
			}
			List<SearchResult> searchValide = new ArrayList<>();
			for(SearchResult s: list) {
				Interrogazione i = (Interrogazione)s.getInterrogazione();
				if((i.getPosizioneLink() == Integer.valueOf(l.get(3)) && (i.getPosizioneLink() != 0))) {
					searchValide.add(s);
				}
			}
			list = searchValide;
		}
		
		else {
			list = new ArrayList<>();
		}
		
		if(!list.isEmpty()) {
			List<SearchResult> sr = new ArrayList<SearchResult>(list);
			sr.sort((a,b)-> a.getInterrogazione().getTime().compareTo(b.getInterrogazione().getTime()));
			try {
				System.out.println(sr.get(Integer.valueOf(l.get(2))-1));
			}
			catch(IndexOutOfBoundsException e) {
				System.out.println("Indice troppo alto");
				return;
			}
		}
		else {
			System.out.println("Non ci sono "+info);
		}
	}

	private static void richiestaData(List<String> l) throws ClassNotFoundException {
		Collection<SearchResult> list;
		try {
			String info = l.get(0).split("_")[2];
		
		
		
		if(l.size()==3) {
			list = searcher.search(new Utente(l.get(1)), info);
		}
		else if(l.size()==5) {
			LocalDateTime ldt_prima = LocalDateTime.of(LocalDate.parse(l.get(3)), LocalTime.MIN);
			LocalDateTime ldt_dopo = LocalDateTime.of(LocalDate.parse(l.get(4)), LocalTime.MIN);
			if(ldt_prima.compareTo(ldt_dopo)>0) {
				System.out.println("Date nell'ordine sbagliato");
				return;
			}
			list = searcher.search(new Utente(l.get(1)), info, ldt_prima , ldt_dopo);
		}
		else {
			list = new ArrayList<>();
		}
		if(!list.isEmpty()) {
			List<SearchResult> sr = new ArrayList<SearchResult>(list);
			sr.sort((a,b)-> a.getInterrogazione().getTime().compareTo(b.getInterrogazione().getTime()));
			try {
				System.out.println(sr.get(Integer.valueOf(l.get(2))-1).getInterrogazione().getTime());
			}
			catch(IndexOutOfBoundsException e) {
				System.out.println("Indice troppo alto");
				return;
			}
		}
		else {
			System.out.println("Non ci sono "+info);
		}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Comando errato");
			return;
		}
	}	

	private static void richiestaNum(List<String> l) throws ClassNotFoundException {
		Collection<SearchResult> list;
		if(l.size()==2) {
			list = searcher.search(new Utente(l.get(1)), l.get(0).split("_")[1]);
		}
		else if(l.size()==4) {
			list = searcher.search(new Utente(l.get(1)), l.get(0).split("_")[1], LocalDateTime.of(LocalDate.parse(l.get(2)), LocalTime.MIN), LocalDateTime.of(LocalDate.parse(l.get(3)), LocalTime.MIN));
		}
		else if(l.get(0).contains("interrogazione")){
			if(l.size()==3) {
				list = searcher.search(new Utente(l.get(1)), "interrogazione");
			}
			else {
				list = searcher.search(new Utente(l.get(1)), "interrogazione", LocalDateTime.of(LocalDate.parse(l.get(3)), LocalTime.MIN), LocalDateTime.of(LocalDate.parse(l.get(4)), LocalTime.MIN));
			}
			List<SearchResult> searchValide = new ArrayList<>();
			for(SearchResult s: list) {
				Interrogazione i = (Interrogazione)s.getInterrogazione();
				if((i.getPosizioneLink() == Integer.valueOf(l.get(2)) && (i.getPosizioneLink() != 0))) {
					searchValide.add(s);
				}
			}
			list = searchValide;
		}
		else {
			list = new ArrayList<>();
		}
		System.out.println(list.size());
	}

	private static List<String> prendiInput() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		return Arrays.asList(br.readLine().replace("query", "interrogazione").split("\\s+"));
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		DietroLeQuinte dlq;
		System.out.println(args.length);
		try {
			dlq = new DietroLeQuinte(args[0], args[1]);
		} 
		catch (ArrayIndexOutOfBoundsException e) {
				try {
					dlq = new DietroLeQuinte(args[0]);
				} catch (EndProgramException e1) {
					e1.printStackTrace();
				}
		} 
		catch (EndProgramException e) {
			e.printStackTrace();
		}
		
		cicloProgramma();
		
		
	}
}
