package it.uniroma1.lcl.dietrolequinte.cmdline;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
	static private File fileComandi;
	static private boolean batchModeActive = false;
	static private FileInputStream fis;
	static private InputStreamReader isr;
	static private BufferedReader bis;
	
	private DietroLeQuinte(String nomeDirectory, String nomeFileComandi) throws EndProgramException, FileNotFoundException {
		this(nomeDirectory);
		this.nomeFileComandi = nomeFileComandi;
		this.fileComandi = new File(nomeFileComandi);
		if(!this.fileComandi.exists()) throw new FileNotFoundException();
		fis = new FileInputStream(fileComandi);
		isr = new InputStreamReader(fis);
		bis = new BufferedReader(isr);
		
		batchModeActive = true;

	}
	private DietroLeQuinte(String nomeDirectory) throws EndProgramException {
		this.nomeDirectory = nomeDirectory;
		searcher = Searcher.getIstanza(nomeDirectory);
	}
	
	private static void cicloProgramma() throws IOException, ClassNotFoundException, EndProgramException {
		
		for(;;) {
			List<String> l = prendiInput();
			/*
			 * Verifica il caso di uscita dal programma
			 */
			if(l.get(0).equals("exit")) return;
			analizzaInput(l);
			
		}
		
	}
	
	private static void analizzaInput(List<String> l) throws ClassNotFoundException, EndProgramException {
		if(l.get(0).contains("num")) {
			richiestaNum(l);
		}
		else if(l.get(0).contains("data")) {
			richiestaData(l);
		}
		else richiestaGenerica(l);
	}

	private static void richiestaGenerica(List<String> l) throws ClassNotFoundException, EndProgramException {
		Collection<SearchResult> list;
		String info = l.get(0);
		if(l.size()==3) {
			list = searcher.search(new Utente(l.get(1)), info);
		}
		else if(l.size()==5) {
			LocalDateTime ldt_prima = parseDataOra(l.get(3));
			LocalDateTime ldt_dopo = parseDataOra(l.get(4));
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
				list = searcher.search(new Utente(l.get(1)), "interrogazione", parseDataOra(l.get(4)), parseDataOra(l.get(5)));
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

	private static void richiestaData(List<String> l) throws ClassNotFoundException, EndProgramException {
		Collection<SearchResult> list;
		try {
			String info = l.get(0).split("_")[2];
		
		
		
		if(l.size()==3) {
			list = searcher.search(new Utente(l.get(1)), info);
		}
		else if(l.size()==5) {
			LocalDateTime ldt_prima = parseDataOra(l.get(3));
			LocalDateTime ldt_dopo = parseDataOra(l.get(4));
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

	private static void richiestaNum(List<String> l) throws ClassNotFoundException, EndProgramException {
		Collection<SearchResult> list;
		if(l.size()==2) {
			list = searcher.search(new Utente(l.get(1)), l.get(0).split("_")[1]);
		}
		else if(l.size()==4) {
			list = searcher.search(new Utente(l.get(1)), l.get(0).split("_")[1], parseDataOra(l.get(2)), parseDataOra(l.get(3)));
		}
		else if(l.get(0).contains("interrogazione")){
			if(l.size()==3) {
				list = searcher.search(new Utente(l.get(1)), "interrogazione");
			}
			else {
				list = searcher.search(new Utente(l.get(1)), "interrogazione", parseDataOra(l.get(3)), parseDataOra(l.get(4)));
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
		if(!batchModeActive) {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			return Arrays.asList(modificaStringaEntrante(br.readLine()).split("\\s+"));

		}
		else {
			try {
				return Arrays.asList(modificaStringaEntrante(bis.readLine()).split("\\s+"));
			}
			catch(NullPointerException e) {
				return Arrays.asList("exit");
			}
		}
	}
	
	private static String modificaStringaEntrante(String s) {
		return s.replace("query", "interrogazione").replace("azioni", "azione").replace("messaggi", "messaggio").replace("messaggioo", "messaggio");
	}
	
	private static LocalDateTime parseDataOra(String s) throws EndProgramException {
		try {
			return LocalDateTime.parse(s);
		}
		catch(DateTimeParseException e) {
			try {
				return LocalDateTime.of(LocalDate.parse(s), LocalTime.MIN);
			}
			catch(Exception ee) {
				System.out.println("Data nel formato sbagliato");
				throw new EndProgramException();
			}
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, EndProgramException {
		DietroLeQuinte dlq;
		try {
			dlq = new DietroLeQuinte(args[0], args[1]);
		} 
		catch (ArrayIndexOutOfBoundsException e) {
				try {
					dlq = new DietroLeQuinte(args[0]);
				} catch (EndProgramException e1) {
					e1.printStackTrace();
					return;
				}
		} 
		catch (FileNotFoundException e) {
			System.out.println("File comandi non esiste");
			return;
		}
		catch (EndProgramException e) {
			e.printStackTrace();
			return;
		}
		
		cicloProgramma();
		
		
	}
}
