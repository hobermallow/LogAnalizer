package it.uniroma1.lcl.dietrolequinte;

import java.time.LocalDateTime;

public abstract class Interrogazione {
	
	private Utente user;
	private String stringaImmessa;
	private LocalDateTime time;
	
	public Interrogazione(Utente u, String stringaImmessa, LocalDateTime ldt) {
		
		this.user = u;
		this.stringaImmessa = stringaImmessa;
		this.time = ldt;
	}

}
