package it.uniroma1.lcl.dietrolequinte.cmdline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

/**
 * Classe eseguibile, che implementa un sistema di interogazione dell'analizzatore
 * di file di log, sia in modalità batch sia in modalit� interattiva da terminale
 * 
 * @author onoda
 *
 */
public class DietroLeQuinte {
	
	/**
	 * Stringa rappresentante il nome completo del percorso della cartella
	 */
	private String nomeDirectory;
	
	/**
	 * Campo statico rappresentante il Searcher, cui questa classe far� riferimento
	 * per cercare attraverso i file di log
	 */
	static private Searcher searcher;
	
	/**
	 * Percorso completo del file dei comandi
	 */
	private String nomeFileComandi;
	
	/**
	 * File dei comandi
	 */
	private File fileComandi;
	
	/**
	 * Variabile booleana vera se modalità batch attivata
	 * altrimenti falsa
	 */
	private boolean batchModeActive = false;
	
	/**
	 * Stream e Reader di supporto
	 */
	private FileInputStream fis;
	private InputStreamReader isr;
	private BufferedReader bis;
	
	/**
	 * @param nomeDirectory percorso completo della directory in cui cercare
	 * @param nomeFileComandi percorso completo del file dei comandi
	 * @throws EndProgramException fine del programma (es cartella dei file di log
	 * non esistente, non e' una cartella...)
	 * @throws FileNotFoundException file dei comandi non trovato
	 */
	private DietroLeQuinte(String nomeDirectory, String nomeFileComandi) throws EndProgramException, FileNotFoundException {
		this(nomeDirectory);
		this.nomeFileComandi=nomeFileComandi;
		this.fileComandi = new File(nomeFileComandi);
		if(!this.fileComandi.exists()) throw new FileNotFoundException();
		fis = new FileInputStream(fileComandi);
		isr = new InputStreamReader(fis);
		bis = new BufferedReader(isr);
		
		batchModeActive = true;

	}
	
	/**
	 * @param nomeDirectory percorso completo della directory in cui cercare
	 * @throws EndProgramException
	 */
	private DietroLeQuinte(String nomeDirectory) throws EndProgramException {
		this.nomeDirectory = nomeDirectory;
		searcher = Searcher.getIstanza(nomeDirectory);
	}
	
	/**
	 * Ciclo infinito all'interno del quale si svolge il programma,
	 * ovvero la gestione dell'input, l'eventuale uscita dal programma
	 * e l'esecuzione del comando
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws EndProgramException
	 * 
	 * 
	 */
	private void cicloProgramma() throws IOException, ClassNotFoundException, EndProgramException {
		
		for(;;) {
			List<String> l = prendiInput();
			/*
			 * Verifica il caso di uscita dal programma
			 */
			if(l.get(0).equals("exit")) return;
			analizzaInput(l);
			
		}
		
	}
	
	/**
	 * Analizza il comando inserito, richiamando il metodo specifico a seconda del tipo
	 * di comando inserito (richiesta numerica, richiesta data e richiesta generica)
	 * 
	 * @param l lista di stringhe rappresentanti i vari elementi della linea di comando
	 * inserita
	 * @throws ClassNotFoundException
	 * @throws EndProgramException
	 * 
	 */
	private void analizzaInput(List<String> l) throws ClassNotFoundException, EndProgramException {
		if(l.get(0).contains("num")) {
			richiestaNum(l);
		}
		else if(l.get(0).contains("data")) {
			richiestaData(l);
		}
		else richiestaGenerica(l);
	}

	/**
	 * Il metodo analizza un comando generico, restituendo la versione stampabile della
	 * specifica informazione richiesta nel comando
	 * 
	 * @param l lista delle stringhe del comando inserito
	 * @throws ClassNotFoundException
	 * @throws EndProgramException
	 * 
	 */
	private void richiestaGenerica(List<String> l) throws ClassNotFoundException, EndProgramException {
		Collection<SearchResult> list;
		String info = l.get(0);
		if(l.size()==3) {
			list = searcher.search(info, new Utente(l.get(1)));
		}
		else if(l.size()==5) {
			LocalDateTime ldt_prima = parseDataOra(l.get(3));
			LocalDateTime ldt_dopo = parseDataOra(l.get(4));
			if(ldt_prima.compareTo(ldt_dopo)>0) {
				System.out.println("Date nell'ordine sbagliato");
				return;
			}
			list = searcher.search(info, new Utente(l.get(1)), ldt_prima , ldt_dopo);

		}
		else if(l.get(0).equals("interrogazione")){
			if(l.size()==4) {
				list = searcher.search("interrogazione", new Utente(l.get(1)));
			}
			else {
				list = searcher.search("interrogazione", new Utente(l.get(1)), parseDataOra(l.get(4)), parseDataOra(l.get(5)));
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
				System.out.println(sr.get(Integer.valueOf(l.get(2))-1).getInterrogazione().stampaPerDietroLeQuinte());
			}
			catch(IndexOutOfBoundsException e) {
				System.out.println("Nessuno");
				return;
			}
		}
		else {
			System.out.println("Nessuno");
		}
	}

	/**
	 * Gestione delle richieste di tipo data. Stampa, se disponibile, la data
	 * dell'informazione cercata
	 * 
	 * @param l lista delle stringhe del comando inserito
	 * @throws ClassNotFoundException
	 * @throws EndProgramException
	 * 
	 */
	private void richiestaData(List<String> l) throws ClassNotFoundException, EndProgramException {
		Collection<SearchResult> list;
		try {
			String info = l.get(0).split("_")[2];
		
		
		
		if(l.size()==3) {
			list = searcher.search(info, new Utente(l.get(1)));
		}
		else if(l.size()==5) {
			LocalDateTime ldt_prima = parseDataOra(l.get(3));
			LocalDateTime ldt_dopo = parseDataOra(l.get(4));
			if(ldt_prima.compareTo(ldt_dopo)>0) {
				System.out.println("Date nell'ordine sbagliato");
				return;
			}
			list = searcher.search(info, new Utente(l.get(1)), ldt_prima , ldt_dopo);
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
				System.out.println("Nessuno");
				return;
			}
		}
		else {
			System.out.println("Nessuno");
		}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Comando errato");
			return;
		}
	}	

	/**
	 * Gestione delle richieste di tipo numerico. Crea una lista dei risultati di
	 * ricerca corrispondenti ai parametri presi in argomento e stampa a video la
	 * size della lista
	 * 
	 * @param l lista delle stringhe del comando inserito
	 * @throws ClassNotFoundException
	 * @throws EndProgramException
	 * 
	 */
	private void richiestaNum(List<String> l) throws ClassNotFoundException, EndProgramException {
		Collection<SearchResult> list;
		if(l.size()==2) {
			list = searcher.search(l.get(0).split("_")[1], new Utente(l.get(1)));
		}
		else if(l.size()==4) {
			list = searcher.search(l.get(0).split("_")[1], new Utente(l.get(1)), parseDataOra(l.get(2)), parseDataOra(l.get(3)));
		}
		else if(l.get(0).contains("interrogazione")){
			if(l.size()==3) {
				list = searcher.search("interrogazione", new Utente(l.get(1)), "interrogazione");
			}
			else {
				list = searcher.search("interrogazione", new Utente(l.get(1)), parseDataOra(l.get(3)), parseDataOra(l.get(4)));
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

	/**
	 * Gestisce l'input del comando da utilizzare. Implementa sia il caso in cui si stia
	 * utilizzando la modalit� interattiva, sia la modalit� batch
	 * 
	 * @return la  lista delle "parole" contenute nella linea di comando
	 * @throws IOException
	 * 
	 */
	private List<String> prendiInput() throws IOException {
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
	
	/**
	 * Il metodo � d'ausilio all'uso della reflection. Modifica il tipo
	 * d'informazione del comando in modo da farlo corrispondere a quello di una
	 * delle classi che ereditano da AbstractInterrogzione (ad esempio, "azioni" diviene "azione"). Da modificare nel caso 
	 * s'aggiungano altri comandi
	 * 
	 * @param s stringa da modificare
	 * @return
	 * 
	 */
	private String modificaStringaEntrante(String s) {
		return s.replace("query", "interrogazione").replace("azioni", "azione").replace("messaggi", "messaggio").replace("messaggioo", "messaggio");
	}
	
	/**
	 * Metodo che gestisce il parsing di una stringa, restituendo un oggetto LocalDateTime
	 * corrispondente alla stringa in input. Gestisce due tipi di formato,
	 * "2014-02-02" o "2013-02-22T18:03:03"
	 * 
	 * @param s Stringa da cui trarre la data e/o ora
	 * @return
	 * @throws EndProgramException
	 * 
	 */
	private LocalDateTime parseDataOra(String s) throws EndProgramException {
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

	/**
	 * Getter per il nome della directory su cui si sta lavorando
	 * 
	 * @return il nome della directory dei file di log
	 */
	public  String getNomeDirectory() {
		return nomeDirectory;
	}

	/**
	 * Getter per il file dei comandi utilizzato
	 * 
	 * @return nome del file dei comandi per la modalità batch
	 */
	public  String getNomeFileComandi() {
		return nomeFileComandi;
	}

	/**
	 * Prova a costruire un oggetto di tipo DietroLeQuinte, a partire da due argomenti
	 * Altrimenti, prova a costruirlo con un solo argomento (modalit� interattiva).
	 * A qual punto richiama il metodo cicloProgramma()
	 * 
	 * @param args argomenti in input da linea di comando
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws EndProgramException
	 * 
	 */
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
		
		dlq.cicloProgramma();
		
		
	}
}
