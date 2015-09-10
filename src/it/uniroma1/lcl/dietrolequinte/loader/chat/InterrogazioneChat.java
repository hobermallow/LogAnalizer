package it.uniroma1.lcl.dietrolequinte.loader.chat;

import java.time.LocalDateTime;

import it.uniroma1.lcl.dietrolequinte.AbstractInterrogazione;
import it.uniroma1.lcl.dietrolequinte.Utente;

/**
 * Classe astratta che definisce i log di chat del tipo IRC
 * @author Steve
 *
 */
public abstract class InterrogazioneChat extends AbstractInterrogazione {
	/**
	 * stringa che rappresenta l'indirizzo ip
	 */
	private String ipAddress;
	/**
	 * stringa che rappresenta l'azione
	 */
	private String azione;
	/**
	 * stringa che rappresenta il messaggio
	 */
	private String messaggio;

	/**
	 * 
	 * @param u utente in formato Utente 
	 * @param stringaImmessa Stringa che compare nella riga
	 * @param ldt data in formato LocalDateTime
	 * @param ipAddress stringa che rappresenta l'indirizzo ip
	 * @param azione stringa azione
	 * @param messaggio stringa messaggio
	 */
	public InterrogazioneChat(Utente u, String stringaImmessa, LocalDateTime ldt, String ipAddress, String azione, String messaggio ) {
		super(u, stringaImmessa, ldt);
		this.ipAddress = ipAddress;
		this.azione = azione;
		this.messaggio=messaggio;
	}

	
	/**
	 * 
	 * @return l'azione
	 */
	public String getAzione() {
		return azione;
	}

	/**
	 * 
	 * @return l'indirizzo ip
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * 
	 * @return il messaggio
	 */
	public String getMessaggio() {
		return messaggio;
	}
}
