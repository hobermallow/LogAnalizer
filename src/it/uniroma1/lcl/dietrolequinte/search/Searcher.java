package it.uniroma1.lcl.dietrolequinte.search;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import it.uniroma1.lcl.dietrolequinte.Utente;
import it.uniroma1.lcl.dietrolequinte.exception.DirectoryNotFoundException;
import it.uniroma1.lcl.dietrolequinte.exception.EmptyDirectoryException;
import it.uniroma1.lcl.dietrolequinte.exception.NotADirectoryException;
import it.uniroma1.lcl.dietrolequinte.loader.AbstractLoader;
import it.uniroma1.lcl.dietrolequinte.loader.Loader;

public class Searcher {

	static private Searcher istanza;
	List<AbstractLoader> listaLoader;
	private Loader loader;
	
	static public Searcher getIstanza(String s)
	{
		if(istanza==null)istanza= new Searcher(s);
		return istanza;
	}
	
	private Searcher(String s) 
	{
		try
		{
		loader = new Loader(s);
		}
		catch(DirectoryNotFoundException | NotADirectoryException | EmptyDirectoryException e)
		{
			e.printStackTrace();
		}
		listaLoader = loader.getLoaders();
	}
	
	
	public Collection getUsers()
	{
		TreeSet<Utente> users = new TreeSet<Utente>();
		
		for (AbstractLoader l: loader.getLoaders())
		{
			for (Utente u: l.getUsers())
			{
				users.add(u);
			}
		}
		return users;
	}
	
	public Collection getUsers(String s)
	{
		TreeSet<Utente> users = new TreeSet<Utente>();
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
}
