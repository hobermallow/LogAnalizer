package it.uniroma1.lcl.dietrolequinte.loader.chat;

import java.time.LocalDateTime;

import it.uniroma1.lcl.dietrolequinte.Utente;
/**
 * Classe che rappresenta il logout fatto da un utente
 * @author Steve
 *
 */
public class Logout extends Loginout {

	/**
	 * 
	 * @param u utente in formato Utente
	 * @param stringaImmessa stringa immessa dall'utente
	 * @param ldt data in formato LocalDateTime 
	 * @param ipAddress stringa che rappresenta l'ip
	 */
	public Logout(Utente u, String stringaImmessa, LocalDateTime ldt, String ipAddress) {
		super(u, stringaImmessa, ldt, ipAddress);
	}

}
