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
 * Questa classe legge la struttura dati creata dalla classe Loader e risponde ad una determinata sequenza di query
 * @author Steve
 *
 */
public class Searcher {

	static private Searcher istanza;
	List<AbstractLoader> listaLoader;
	private Loader loader;
	
	/**
	 * 
	 * @param s percorso del File
	 * @return ritorna l'unica istanza ammessa dalla classe
	 * @throws EndProgramException
	 */
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
	/**
	 * Costruttore, un Searcher e' costruito dando in input un percorso
	 * @param s stringa rappresentante il percorso
	 * @throws EndProgramException
	 */
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
	 * @return ritorna una collezione iterabile di tutti Utenti
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
	 * @param s File da cui cercare
	 * @return ritorna una collezione iterabile di tutti Utenti all'interno del file fornito in input
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
	 * Cerca i risultati secondo alcuni parametri
	 * @param u Utente da cercare
	 * @param informazione da cercare
	 * @return ritorna una collezione iterabile di SearchResult
	 * @throws ClassNotFoundException 
	 */
	public Collection<SearchResult> search(String info, Utente u) throws ClassNotFoundException 
	{
		ArrayList<SearchResult> output= new ArrayList<SearchResult>();
		
		for (AbstractLoader l: loader.getLoaders())
			searchGenerico(info, u, l, output);
		
		return output;
	}
	/**
	 * Cerca i risultati secondo alcuni parametri
	 * @param u Utente da cercare
	 * @param info informazione da cercare
	 * @param file file in cui cercare
	 * @return ritorna una collezione iterabile di SearchResult
	 * @throws ClassNotFoundException 
	 */
	public Collection<SearchResult> search(String info, Utente u, String file) throws ClassNotFoundException
	{
		
		ArrayList<SearchResult> output= new ArrayList<SearchResult>();
		
		for (AbstractLoader l: loader.getLoaders())
		{
			if(l.getNomeFile().equals(file))
				searchGenerico(info, u, l, output);
		}
		return output;
		
	} 
	/**
	 * Cerca i risultati secondo alcuni parametri entro un intervallo di date
	 * @param u Utente da cercare
	 * @param info informazione da cercare
	 * @param begin data iniziale
	 * @param end data finale
	 * @return ritorna una collezione iterabile di SearchResult
	 * @throws ClassNotFoundException 
	 */
	public Collection<SearchResult> search(String info, Utente u, LocalDateTime begin, LocalDateTime end) throws ClassNotFoundException
	{
		ArrayList<SearchResult> output= new ArrayList<SearchResult>();
	
		for (AbstractLoader l: loader.getLoaders())
			searchGenerico(info, u, l, output);
		
		return filterDate(output, begin, end);
	}
	/**
	 * Cerca i risultati secondo alcuni parametri entro un intervallo di date
	 * @param u Utente da cercare
	 * @param info informazione da cercare
	 * @param file file in cui cercare
	 * @param begin data iniziale
	 * @param end data finale
	 * @return ritorna una collezione iterabile di SearchResult
	 * @throws ClassNotFoundException 
	 */
	public Collection<SearchResult> search(String info, Utente u, String file, LocalDateTime begin, LocalDateTime end) throws ClassNotFoundException
	{
		ArrayList<SearchResult> output= (ArrayList<SearchResult>) search(info, u, file);
		
		return filterDate(output, begin, end);
		
	}
	
	/**
	 * Filtra i risulati entro un intervallo di date 
	 * @param risultati lista di SearchResult
	 * @param begin data iniziale
	 * @param end data finale
	 * @return ritorna una collezione iterabile di SearchResult
	 */
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
	
	/**
	 * Filtra una lista di SearchResult secondo alcuni parametri
	 * @param u Utente da cercare
	 * @param info informazione da cercare
	 * @param l Loader in cui cercare
	 * @param output ritorna una collezione iterabile di SearchResult
	 * @throws ClassNotFoundException
	 */
	private void searchGenerico(String info,Utente u,  AbstractLoader l, Collection<SearchResult> output) throws ClassNotFoundException
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
