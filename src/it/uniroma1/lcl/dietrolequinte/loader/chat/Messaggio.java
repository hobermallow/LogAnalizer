package it.uniroma1.lcl.dietrolequinte.loader.chat;

import java.time.LocalDateTime;

import it.uniroma1.lcl.dietrolequinte.Utente;

/**
 * Classe che rappresenta un messaggio inviato da un utente su una chat IRC
 * @author Steve
 *
 */
public class Messaggio extends InterrogazioneChat {

	/**
	 * 
	 * @param u utente in formato Utente
	 * @param stringaImmessa stringa immessa dall'utente
	 * @param ldt data in formato LocalDateTime 
	 * @param messaggio stringa che rappresenta il messaggio immesso dall'utente
	 */
	public Messaggio(Utente u, String stringaImmessa, LocalDateTime ldt, String messaggio) {
		super(u, stringaImmessa, ldt, null, null, messaggio);
	}

	@Override
	public String stampaPerDietroLeQuinte() {
		return getMessaggio();
	}

}
