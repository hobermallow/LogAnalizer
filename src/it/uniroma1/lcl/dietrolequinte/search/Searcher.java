package it.uniroma1.lcl.dietrolequinte.search;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import it.uniroma1.lcl.dietrolequinte.Utente;
import it.uniroma1.lcl.dietrolequinte.exception.DirectoryNotFoundException;
import it.uniroma1.lcl.dietrolequinte.exception.EmptyDirectoryException;
import it.uniroma1.lcl.dietrolequinte.exception.EndProgramException;
import it.uniroma1.lcl.dietrolequinte.exception.NotADirectoryException;
import it.uniroma1.lcl.dietrolequinte.loader.AbstractLoader;
import it.uniroma1.lcl.dietrolequinte.loader.Loader;

public class Searcher {

	static private Searcher istanza;
	List<AbstractLoader> listaLoader;
	private Loader loader;
	
	static public Searcher getIstanza(String s) throws EndProgramException
	{
		if(istanza==null)
			try {
				istanza= new Searcher(s);
			} catch (EndProgramException e) {
				throw new EndProgramException();
			}
		return istanza;
	}
	
	private Searcher(String s) throws EndProgramException 
	{
		try
		{
		loader = new Loader(s);
		}
		catch(DirectoryNotFoundException | NotADirectoryException | EmptyDirectoryException e)
		{
			throw new EndProgramException();
		}
		listaLoader = loader.getLoaders();
	}
	
	
	public Collection<Utente> getUsers()
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
	
	public Collection<Utente> getUsers(String s)
	{
		for (AbstractLoader l: loader.getLoaders())
		{
			if (l.getNomeFile().equals(s)){return l.getUsers();}
		}
		return null;
	}
	
	
//	public Collection<SearchResult> search(Utente u, String s)
//	{
//		
//	}
	
	
	
}
