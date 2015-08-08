package it.uniroma1.lcl.dietrolequinte.search;
import java.util.List;
import it.uniroma1.lcl.dietrolequinte.loader.Loadable;
import it.uniroma1.lcl.dietrolequinte.loader.Loader;

public class Searcher {

	static private Searcher istanza;
	List<Loadable> listaLoader;
	
	static public Searcher getIstanza(String s)
	{
		if(istanza==null) istanza= new Searcher(s);
		return istanza;
	}
	
	static public Searcher getIstanza()
	{
		return istanza;
	}
	
	
	
	private Searcher(String s)
	{
		Loader main = new Loader(s);
		listaLoader = main.getLoaders();
		
	}
	
}
