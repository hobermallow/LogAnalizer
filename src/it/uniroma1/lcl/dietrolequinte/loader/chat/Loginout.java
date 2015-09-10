package it.uniroma1.lcl.dietrolequinte.loader.chat;

import java.time.LocalDateTime;

import it.uniroma1.lcl.dietrolequinte.Utente;

/**
 * Classe che rappresenta il login o il logout fatto da un utente
 * @author Steve
 *
 */
public class Loginout extends InterrogazioneChat {

	/**
	 * 
	 * @param u utente in formato Utente
	 * @param stringaImmessa stringa immessa dall'utente
	 * @param ldt data in formato LocalDateTime 
	 * @param ipAddress stringa che rappresenta l'ip
	 */
	public Loginout(Utente u, String stringaImmessa, LocalDateTime ldt, String ipAddress) {
		super(u, stringaImmessa, ldt, ipAddress, null, null);
	}

	@Override
	public String stampaPerDietroLeQuinte() {
		return getStringaImmessa();
	}
}
