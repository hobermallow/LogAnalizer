package it.uniroma1.lcl.dietrolequinte.loader.chat;

import java.time.LocalDateTime;

import it.uniroma1.lcl.dietrolequinte.Utente;

/**
 * 
 * @author Steve
 *
 */
public class Azione extends InterrogazioneChat {

	public Azione(Utente u, String stringaImmessa, LocalDateTime ldt, String azione) {
		super(u, stringaImmessa, ldt, null, azione, null);
	}

	@Override
	public String stampaPerDietroLeQuinte() {
		return getAzione();
	}
	

}
