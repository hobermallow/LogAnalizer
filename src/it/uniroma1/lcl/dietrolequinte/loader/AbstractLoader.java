package it.uniroma1.lcl.dietrolequinte.loader;

import java.util.List;
import java.util.TreeSet;

import it.uniroma1.lcl.dietrolequinte.Interrogazione;
import it.uniroma1.lcl.dietrolequinte.Utente;


public abstract class AbstractLoader {
	
	protected TreeSet<Utente> insiemeUtenti;
	protected TreeSet<Interrogazione> insiemeInterrogazioni;
	private String nomeFile;
	
	/**
	 * @param nomeFile nome del file sul quale lavora il loader
	 * Richiama il metodo inizializzaLoader() che dovrà
	 * essere implementato dal loader specifico
	 * 
	 */
	public AbstractLoader(String nomeFile) {
		this.nomeFile=nomeFile;
		inizializzaLoader();
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
	
	public TreeSet<Utente> getUsers() {
		return insiemeUtenti;
	}
	
	TreeSet<Interrogazione> getInterrogazioni() {
		return insiemeInterrogazioni;
	}
	
	abstract protected void inizializzaLoader();

	public String getNomeFile() {
		return nomeFile;
	}
	
}
