package it.uniroma1.lcl.dietrolequinte.loader.chat;

import java.time.LocalDateTime;

import it.uniroma1.lcl.dietrolequinte.Utente;

public class Loginout extends InterrogazioneChat {

	public Loginout(Utente u, String stringaImmessa, LocalDateTime ldt, String ipAddress) {
		super(u, stringaImmessa, ldt, ipAddress, null, null);
	}
}