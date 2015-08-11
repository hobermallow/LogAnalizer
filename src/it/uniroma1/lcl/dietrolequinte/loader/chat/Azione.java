package it.uniroma1.lcl.dietrolequinte.loader.chat;

import java.time.LocalDateTime;

import it.uniroma1.lcl.dietrolequinte.Utente;

public class Azione extends InterrogazioneChat {

	public Azione(Utente u, String stringaImmessa, LocalDateTime ldt, String ipAddress, String azione) {
		super(u, stringaImmessa, ldt, ipAddress, azione, null);
	}

}
