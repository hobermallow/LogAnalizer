package it.uniroma1.lcl.dietrolequinte.search;

import it.uniroma1.lcl.dietrolequinte.Interrogazione;

public class SearchResult {

	Interrogazione i;
	
	public SearchResult(Interrogazione i){this.i=i;}
	
	public String toString(){return i.toString();}
}
