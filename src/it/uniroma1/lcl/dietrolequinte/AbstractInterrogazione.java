package it.uniroma1.lcl.dietrolequinte;

import java.time.LocalDateTime;

public abstract class AbstractInterrogazione {
	
	private Utente user;
	private String stringaImmessa;
	private LocalDateTime time;
	
	public AbstractInterrogazione(Utente u, String stringaImmessa, LocalDateTime ldt) {
		
		this.user = u;
		this.stringaImmessa = stringaImmessa;
		this.time = ldt;
	}

	public Utente getUser() {
		return user;
	}

	public String getStringaImmessa() {
		return stringaImmessa;
	}

	public LocalDateTime getTime() {
		return time;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("Utente: "+user+"\n");
		sb.append("Stringa Immessa: "+stringaImmessa+"\n");
		sb.append("Tempo: "+time+"\n");
		return sb.toString();
	}
	
	abstract public String stampaPerDietroLeQuinte();

}
