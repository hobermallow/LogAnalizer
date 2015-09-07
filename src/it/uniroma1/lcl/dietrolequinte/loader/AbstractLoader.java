package it.uniroma1.lcl.dietrolequinte.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

import it.uniroma1.lcl.dietrolequinte.AbstractInterrogazione;
import it.uniroma1.lcl.dietrolequinte.Utente;

/**
 * Classe che definisce le caratteristiche di un Loader
 * @author Steve
 *
 */
public abstract class AbstractLoader {
	
	protected TreeSet<Utente> insiemeUtenti;
	protected List<AbstractInterrogazione> insiemeInterrogazioni;
	private File file;
	private String path = this.getClass().getName();
	private Integer linesNumber;
	
	/**
	 * @param nomeFile nome del file sul quale lavora il loader
	 * Richiama il metodo inizializzaLoader() che dovrà
	 * essere implementato dal loader specifico
	 * @throws IOException 
	 * 
	 */
	public AbstractLoader(File file) throws IOException {
		this.file=file;
		insiemeUtenti = new TreeSet<Utente>();
		insiemeInterrogazioni = new ArrayList<AbstractInterrogazione>();
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = checkIfZipped(file) ? new InputStreamReader(new GZIPInputStream(fis)) : new InputStreamReader(fis);
		BufferedReader bis = new BufferedReader(isr);
		List<String> lines = bis.lines().collect(Collectors.toList());
		linesNumber = lines.size();
		inizializzaLoader(lines);
	}
	
	/**
	 * @return lista delle parole permesse alla ricerca nella 
	 * the specific type of log file
	 */
	abstract protected  List<String> getListValidSearch();
	
	/**
	 * @param string the word passed by the Searcher
	 * @return
	 */
	public boolean checkValidSearch(String string) {
		
		return getListValidSearch().contains(string);
	}
	
	/**
	 * @return l'insieme degli utenti
	 */
	public TreeSet<Utente> getUsers() {
		return insiemeUtenti;
	}
	
	/**
	 * @param u Utente
	 * Aggiunge un utente all'insieme degli utenti
	 */
	protected void addUtente(Utente u) {
		insiemeUtenti.add(u);
	}
	
	/**
	 * @return L'insieme delle interrogazioni
	 */
	public List<AbstractInterrogazione> getInterrogazioni() {
		return insiemeInterrogazioni;
	}
	
	/**
	 * @param i interrogazione
	 * Aggiunge un'interrogazione all'insieme delle interrogazioni
	 */
	protected void addInterrogazione(AbstractInterrogazione i) {
		insiemeInterrogazioni.add(i);
	}
	
	
	
	/**
	 * @param list Lista delle righe del file.
	 * Il programmatore è poi obbligato, per ogni specifico loader, a 
	 * implementare l'analisi del file e il retrieving dei dati
	 * 
	 */
	abstract protected void inizializzaLoader(List<String> list);

	/**
	 * @return il nome del file su cui lavora il loader
	 */
	public String getNomeFile() {
		return file.getName();
	}
	
	/**
	 * @param f
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * 
	 * Trasforma un FileInputStream in un GZIPInputStream
	 */
	private InputStream unzipping(FileInputStream f) throws FileNotFoundException, IOException {
		return new GZIPInputStream(f);
	}
	
	/**
	 * @param f
	 * @return boolean, vero se il file è zippato, altrimenti falso
	 */
	private boolean checkIfZipped(File f) {
		return Arrays.asList(f.getName().split("\\.")).contains("gz");
	}
	
	/**
	 * @param riga Lista degli elementi presenti in una riga del log
	 * Deve creare Interrogazione e Utente a partire dalla lista
	 * degli elementi di una riga
	 */
	abstract protected void analizzaRiga(List<String> riga);

	/**
	 * @return il percorso del loader
	 */
	public String getPath() {
		String[] p = path.split("\\.");
		return String.join(".", Arrays.asList(path.split("\\.")).subList(0, p.length-1))+".";
	}
	
	@Override
	public String toString()  {
		StringBuilder sb = new StringBuilder();
		sb.append(getNomeFile()+" Tipo di file: "+getNomeFile().split("\\.")[0]+"\n");
		sb.append(getNomeFile()+" Numero totale di byte: "+getFileSize()+"\n");
		sb.append(getNomeFile()+" Numero totale di righe: "+getLinesNumber()+"\n");
		for(String s: getListValidSearch()) {
			try {
				sb.append(getNomeFile()+" Numero totale di "+s+": "+countSpecificAbstractInterrogazione(s)+"\n");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	/**
	 * @param s Tipo di interrogazione
	 * @return Il numero di interrogazioni di quel tipo contenute nel loader
	 * @throws ClassNotFoundException
	 */
	private int countSpecificAbstractInterrogazione(String s) throws ClassNotFoundException {
		List<AbstractInterrogazione> l = new ArrayList<>();
		Class c = Class.forName(getPath()+(Character.toUpperCase(s.charAt(0))+s.substring(1)));

		for(AbstractInterrogazione a: getInterrogazioni()) {
			if(c.isInstance(a)) {
				l.add(a);
				
			}
		}
		
		return l.size();
	}
	
	/**
	 * 
	 * @return la grandezza del file
	 */
	public int getFileSize() {
		return (int)file.length();
	}

	/**
	 * @return il numero delle linee
	 */
	public Integer getLinesNumber() {
		return linesNumber;
	}
	
}
