package it.uniroma1.lcl.dietrolequinte.loader.chat;

import java.time.LocalDateTime;

import it.uniroma1.lcl.dietrolequinte.AbstractInterrogazione;
import it.uniroma1.lcl.dietrolequinte.Utente;

public abstract class InterrogazioneChat extends AbstractInterrogazione {
	
	private String ipAddress, azione, messaggio;

	public InterrogazioneChat(Utente u, String stringaImmessa, LocalDateTime ldt, String ipAddress, String azione, String messaggio ) {
		super(u, stringaImmessa, ldt);
		this.ipAddress = ipAddress;
		this.azione = azione;
		this.messaggio=messaggio;
	}

	public String getAzione() {
		return azione;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getMessaggio() {
		return messaggio;
	}
}
