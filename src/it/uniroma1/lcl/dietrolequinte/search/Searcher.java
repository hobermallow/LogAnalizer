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

/**
 * 
 * @author Steve
 *
 */
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
	 * @return return a collection Iterable of all Users
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
	/**
	 * 
	 * @param s file to look
	 * @return return a collection Iterable of all Users in the file 
	 */
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
	 * @throws ClassNotFoundException 
	 */
	public Collection<SearchResult> search(Utente u, String info) throws ClassNotFoundException 
	{
		ArrayList<SearchResult> output= new ArrayList<SearchResult>();
		
		for (AbstractLoader l: loader.getLoaders())
			metodo(u, info, l, output);
		
		return output;
	}
	/**
	 * 
	 * @param u User to look
	 * @param info info to look
	 * @param file file to look
	 * @return return an Iterable Collection of the search
	 * @throws ClassNotFoundException 
	 */
	public Collection<SearchResult> search(Utente u, String info, String file) throws ClassNotFoundException
	{
		
		ArrayList<SearchResult> output= new ArrayList<SearchResult>();
		
		for (AbstractLoader l: loader.getLoaders())
		{
			if(l.getNomeFile().equals(file))
				metodo(u, info, l, output);
		}
		return output;
		
	} 
	/**
	 * 
	 * @param u User to look
	 * @param info info to look
	 * @param begin 
	 * @param end
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public Collection<SearchResult> search(Utente u, String info, LocalDateTime begin, LocalDateTime end) throws ClassNotFoundException
	{
		ArrayList<SearchResult> output= new ArrayList<SearchResult>();
	
		for (AbstractLoader l: loader.getLoaders())
			metodo(u, info, l, output);
		
		return filterDate(output, begin, end);
	}
	/**
	 * 
	 * @param u User to look
	 * @param info 
	 * @param file
	 * @param begin
	 * @param end
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public Collection<SearchResult> search(Utente u, String info, String file, LocalDateTime begin, LocalDateTime end) throws ClassNotFoundException
	{
		ArrayList<SearchResult> output= (ArrayList<SearchResult>) search(u, info, file);
		
		return filterDate(output, begin, end);
		
	}
	
	private Collection<SearchResult> filterDate(List<SearchResult> risultati, LocalDateTime begin, LocalDateTime end)
	{
		ArrayList<SearchResult> ris= new ArrayList<SearchResult>();
		
		for ( SearchResult r: risultati)
		{
			if(r.getInterrogazione().getTime().compareTo(begin)>=0 && r.getInterrogazione().getTime().compareTo(end)<=0)
				ris.add(r);
		}
		return ris;
	}
	
	
	private void metodo(Utente u, String info, AbstractLoader l, Collection<SearchResult> output) throws ClassNotFoundException
	{
		if(l.checkValidSearch(info))
		{
			for(AbstractInterrogazione i : l.getInterrogazioni())
				{
					String infoCapitalized = info.substring(0, 1).toUpperCase() + info.substring(1);
					
					infoCapitalized = l.getPath()+infoCapitalized;
					if(i.getUser().equals(u) && Class.forName(infoCapitalized).isInstance(i) )
						output.add(new SearchResult(i));
				}
		}
	}
	
}
