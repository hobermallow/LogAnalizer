package it.uniroma1.lcl.dietrolequinte.search;
import java.util.Collection;
import java.util.List;

import it.uniroma1.lcl.dietrolequinte.loader.AbstractLoader;
import it.uniroma1.lcl.dietrolequinte.loader.Loader;

public class Searcher {

	static private Searcher istanza;
	List<AbstractLoader> listaLoader;
	
	static public Searcher getIstanza(String s)
	{
		if(istanza==null) istanza= new Searcher(s);
		return istanza;
	}
	
	private Searcher(String s)
	{
		Loader main = new Loader(s);
		listaLoader = main.getLoaders();
	}
	
//	public Collection getUsers()
//	{
//		
//	}
	
}
