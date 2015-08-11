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
		{
			if(l.checkValidSearch(info))
			{
				for(AbstractInterrogazione i : l.getInterrogazioni())
					{
						String infoCapitalized = info.substring(0, 1).toUpperCase() + info.substring(1);
						
						String estratto=i.getClass().getName();
						String[] a=estratto.split("\\.");
						String nomeClasse=a[a.length-1];
//						System.out.println(i.getUser()+" "+u);
//						System.out.println(i.getUser().equals(u));
						infoCapitalized = l.getPath()+infoCapitalized;
						if(i.getUser().equals(u) && Class.forName(infoCapitalized).isInstance(i) ){
							
							output.add(new SearchResult(i));
//							System.out.println("BIG GAY");
							
						}
					}
			}
		}
		return output;
		
	}
	/**
	 * 
	 * @param u User to look
	 * @param info info to look
	 * @param file file to look
	 * @return return an Iterable Collection of the search
	 */
	public Collection<SearchResult> search(Utente u, String info, String file)
	{
		return null;
		
	} 
	/**
	 * 
	 * @param u User to look
	 * @param info info to look
	 * @param begin 
	 * @param end
	 * @return
	 */
	public Collection<SearchResult> search(Utente u, String info, LocalDateTime begin, LocalDateTime end)
	{
		return null;
		
	}
	/**
	 * 
	 * @param u User to look
	 * @param info 
	 * @param file
	 * @param begin
	 * @param end
	 * @return
	 */
	public Collection<SearchResult> search(Utente u, String info, String file, LocalDateTime begin, LocalDateTime end)
	{
		return null;
		
	}
	
	public static void main(String[] args) {
		try {
			Searcher sea=getIstanza("/Users/Steve/Documents/DietroLeQuinte/progetto_metodologie2015/IRC");
			System.out.println(sea.search(new Utente("BigRig"), "logout"));
//			System.out.println(sea.getUsers().contains(new Utente("BigRig")));
			
		} catch (EndProgramException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
