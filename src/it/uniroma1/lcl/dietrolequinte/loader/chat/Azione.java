package it.uniroma1.lcl.dietrolequinte.loader.chat;

import java.time.LocalDateTime;

import it.uniroma1.lcl.dietrolequinte.Utente;

/**
 * Classe che rappresenta un'azione fatta da un utente
 * @author Steve
 *
 */
public class Azione extends InterrogazioneChat {

	/**
	 * 
	 * @param u utente in formato Utente
	 * @param stringaImmessa stringa immessa dall'utente
	 * @param ldt data in formato LocalDateTime
	 * @param azione stringa azione
	 */
	public Azione(Utente u, String stringaImmessa, LocalDateTime ldt, String azione) {
		super(u, stringaImmessa, ldt, null, azione, null);
	}

	@Override
	public String stampaPerDietroLeQuinte() {
		return getAzione();
	}
	

}
