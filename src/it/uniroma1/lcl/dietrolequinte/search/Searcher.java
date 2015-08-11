package it.uniroma1.lcl.dietrolequinte.search;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import it.uniroma1.lcl.dietrolequinte.AbstractInterrogazione;
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
	
	/**
	 * 
	 * @return return a collection Iterable of Users
	 */
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
	
	/**
	 * 
	 * @param u User to search
	 * @param Information to search
	 * @return return a collection iterable of SearchResult
	 */
	public Collection<SearchResult> search(Utente u, String info) 
	{
		ArrayList<SearchResult> output= new ArrayList<SearchResult>();
		
		for (AbstractLoader l: loader.getLoaders())
		{
			if(l.checkValidSearch(info))
			{
				for(AbstractInterrogazione i : l.getInterrogazioni())
					{
						String infoCapitalized = info.substring(0, 1).toUpperCase() + info.substring(1);
						//Class.forName(infoCapitalized);
						
						
						if(i.getUser().equals(u))output.add(new SearchResult(i));
					}
			}
		}
		return output;
		
	}
	
	public Collection<SearchResult> search(Utente u, String info, String file)
	{
		return null;
		
	} 
	
	public Collection<SearchResult> search(Utente u, String info, LocalDateTime inizio, LocalDateTime fine)
	{
		return null;
		
	}
	
	public Collection<SearchResult> search(Utente u, String info, String file, LocalDateTime inizio, LocalDateTime fine)
	{
		return null;
		
	}
	
}
