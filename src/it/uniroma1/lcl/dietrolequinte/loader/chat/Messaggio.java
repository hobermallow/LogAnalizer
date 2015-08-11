package it.uniroma1.lcl.dietrolequinte.loader.chat;

import java.time.LocalDateTime;

import it.uniroma1.lcl.dietrolequinte.Utente;

public class Messaggio extends InterrogazioneChat {

	public Messaggio(Utente u, String stringaImmessa, LocalDateTime ldt, String messaggio) {
		super(u, stringaImmessa, ldt, null, null, messaggio);
	}

}
