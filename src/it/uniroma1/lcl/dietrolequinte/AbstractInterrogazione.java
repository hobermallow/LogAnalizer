package it.uniroma1.lcl.dietrolequinte;

import java.time.LocalDateTime;

/**
 * @author onoda
 * La classe rappresenta una generica "interrogazione", intesa come riga
 * di un file dilog, contente un generico utente, la stringa immessa
 * e una data
 *
 */
public abstract class AbstractInterrogazione {
	
	private Utente user;
	private String stringaImmessa;
	private LocalDateTime time;
	
	/**
	 * @param u Utente
	 * @param stringaImmessa Stringa che compare nela riga
	 * @param ldt Oggetto LocalDateTime
	 */
	public AbstractInterrogazione(Utente u, String stringaImmessa, LocalDateTime ldt) {
		
		this.user = u;
		this.stringaImmessa = stringaImmessa;
		this.time = ldt;
	}

	/**
	 * @return l'utente
	 */
	public Utente getUser() {
		return user;
	}

	/**
	 * @return la stringa immessa
	 */
	public String getStringaImmessa() {
		return stringaImmessa;
	}

	/**
	 * @return la data dell'interrogazione
	 */
	public LocalDateTime getTime() {
		return time;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Utente: "+user+"\n");
		sb.append("Stringa Immessa: "+stringaImmessa+"\n");
		sb.append("Tempo: "+time+"\n");
		return sb.toString();
	}
	
	/**
	 * @return la stringa che deve essere stampata, a riga di comando, dall'eseguibile
	 * che interroga l'analizzatore di log
	 */
	abstract public String stampaPerDietroLeQuinte();

}
