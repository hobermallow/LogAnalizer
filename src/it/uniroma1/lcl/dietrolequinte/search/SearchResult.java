package it.uniroma1.lcl.dietrolequinte.search;

import it.uniroma1.lcl.dietrolequinte.AbstractInterrogazione;

public class SearchResult {

	AbstractInterrogazione i;
	
	public SearchResult(AbstractInterrogazione i){this.i=i;}
	
	public String toString(){return i.toString();}
}
