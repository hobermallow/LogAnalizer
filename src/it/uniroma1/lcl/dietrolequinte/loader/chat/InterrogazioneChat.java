package it.uniroma1.lcl.dietrolequinte.loader.chat;

import java.time.LocalDateTime;

import it.uniroma1.lcl.dietrolequinte.Interrogazione;
import it.uniroma1.lcl.dietrolequinte.Utente;

public class InterrogazioneChat extends Interrogazione {
	
	private String 

	public InterrogazioneChat(Utente u, String stringaImmessa, LocalDateTime ldt, String ipAddress, String azione ) {
		super(u, stringaImmessa, ldt);
		this.ipAddress  = ipAddress;
		this.azione = azione;
	}
	
	

}
