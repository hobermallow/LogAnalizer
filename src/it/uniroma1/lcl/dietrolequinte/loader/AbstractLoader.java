package it.uniroma1.lcl.dietrolequinte.loader;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import it.uniroma1.lcl.dietrolequinte.Interrogazione;
import it.uniroma1.lcl.dietrolequinte.Utente;


public abstract class AbstractLoader {
	
	protected TreeSet<Utente> insiemeUtenti;
	protected TreeSet<Interrogazione> insiemeInterrogazioni;
	
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
	
	TreeSet<Utente> getUsers() {
		return insiemeUtenti;
	}
	
	TreeSet<Interrogazione> getInterrogazioni() {
		return insiemeInterrogazioni;
	}
	
	

	

	
}
