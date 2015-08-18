package it.uniroma1.lcl.dietrolequinte.search;

import it.uniroma1.lcl.dietrolequinte.AbstractInterrogazione;

/**
 * Classe che definisce un risultato  
 * @author Steve
 *
 */
public class SearchResult {

	AbstractInterrogazione i;
	
	/**
	 * Costruttore, un SearchResult e' costruito a partire da un oggetto AbstractInterrogazione
	 * @param i AbstractInterrogazione
	 */
	public SearchResult(AbstractInterrogazione i){this.i=i;}
	
	/**
	 * 
	 * @return ritorna AbstractInterrogazione su cui e' costruito l'oggetto
	 */
	public AbstractInterrogazione getInterrogazione(){return i;}
	
	/**
	 * rappresentazione a stringa del risultato
	 */
	public String toString(){return i.toString();}
}
