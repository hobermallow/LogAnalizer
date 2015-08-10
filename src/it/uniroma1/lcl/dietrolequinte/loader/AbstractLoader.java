package it.uniroma1.lcl.dietrolequinte.loader;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.zip.GZIPInputStream;

import it.uniroma1.lcl.dietrolequinte.Interrogazione;
import it.uniroma1.lcl.dietrolequinte.Utente;


public abstract class AbstractLoader {
	
	protected TreeSet<Utente> insiemeUtenti;
	protected TreeSet<Interrogazione> insiemeInterrogazioni;
	private File file;
	
	/**
	 * @param nomeFile nome del file sul quale lavora il loader
	 * Richiama il metodo inizializzaLoader() che dovrà
	 * essere implementato dal loader specifico
	 * @throws IOException 
	 * 
	 */
	public AbstractLoader(File file) throws IOException {
		this.file=file;
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = checkIfZipped(file) ? new InputStreamReader(new GZIPInputStream(fis)) : new InputStreamReader(fis);
		BufferedReader bis = new BufferedReader(isr);
		inizializzaLoader(bis);
	}
	
	/**
	 * @return list of allowed search words for
	 * the specific type of log file
	 */
	abstract protected  List<String> getList();
	
	/**
	 * @param string the word passed by the Searcher
	 * @return
	 */
	public boolean checkValidSearch(String string) {
		
		return getList().contains(string);
	}
	
	/**
	 * @return l'insieme degli utenti
	 */
	public TreeSet<Utente> getUsers() {
		return insiemeUtenti;
	}
	
	/**
	 * @return L'insieme delle interrogazioni
	 */
	TreeSet<Interrogazione> getInterrogazioni() {
		return insiemeInterrogazioni;
	}
	
	/**
	 * @param br BufferedReader passato in input dal costruttore.
	 * Il programmatore è poi obbligato, per ogni specifico loader, a 
	 * implementare l'analisi del file e il retrieving dei dati
	 * 
	 */
	abstract protected void inizializzaLoader(BufferedReader br);

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
	 */
	private InputStream unzipping(FileInputStream f) throws FileNotFoundException, IOException {
		return new GZIPInputStream(f);
	}
	
	/**
	 * @param f
	 * @return
	 */
	private boolean checkIfZipped(File f) {
		return Arrays.asList(f.getName().split(".")).contains("zip");
	}
	
}
