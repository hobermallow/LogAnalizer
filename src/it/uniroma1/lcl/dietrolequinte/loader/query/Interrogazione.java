package it.uniroma1.lcl.dietrolequinte.loader.query;

import java.time.LocalDateTime;

import it.uniroma1.lcl.dietrolequinte.AbstractInterrogazione;
import it.uniroma1.lcl.dietrolequinte.Utente;
/**
 * Classe che rappresenta un'interrogazione fatta da un utente su 
 * un motore di ricerca
 * @author Steve
 *
 */
public class Interrogazione extends AbstractInterrogazione {
	
	/**
	 * stringa che rappresenta l'indirizzo cliccato dall'utente
	 */
	private String linkCliccato;
	/**
	 * intero che rappresenta la posizione del link nella pagina rispetto agli altri
	 */
	private Integer posizioneLink;
	
	/**
	 * 
	 * @param u
	 * @param stringaImmessa
	 * @param ldt
	 * @param linkCliccato
	 * @param posizioneLink
	 */
	public Interrogazione(Utente u, String stringaImmessa, LocalDateTime ldt, String linkCliccato, Integer posizioneLink) {
		super(u, stringaImmessa, ldt);
		this.linkCliccato = linkCliccato;
		this.posizioneLink = posizioneLink;
	}
	
	/**
	 * 
	 * @param u
	 * @param stringaImmessa
	 * @param ldt
	 */
	public Interrogazione(Utente u, String stringaImmessa, LocalDateTime ldt) {
		super(u, stringaImmessa, ldt);
		this.linkCliccato = "";
		this.posizioneLink = 0;
	}

	/**
	 * 
	 * @return il link cliccato dall'utente
	 */
	public String getLinkCliccato() {
		return linkCliccato;
	}

	
	/**
	 * 
	 * @return 
	 */
	public int getPosizioneLink() {
		return posizioneLink;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Link Cliccato: "+linkCliccato+"\n");
		sb.append("Posizione Link: "+posizioneLink+"\n");
		return super.toString()+sb.toString();
	}

	@Override
	public String stampaPerDietroLeQuinte() {
		return getStringaImmessa();
	}

}
