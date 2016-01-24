package it.uniroma1.lcl.dietrolequinte;

import java.time.LocalDateTime;

/**
 * La classe rappresenta una generica "interrogazione", intesa come riga
 * di un file di log, contente un generico utente, la stringa immessa
 * e una data
 * 
 * @author onoda
 */
public abstract class AbstractInterrogazione {
	
	/**
	 * Utente che effettua l'interrogazione
	 */
	private Utente user;
	
	/**
	 * Stringa immessa dall'utente
	 */
	private String stringaImmessa;
	
	/**
	 * Data-Ora dell'interrogazione
	 */
	private LocalDateTime time;
	
	/**
	 * @param u Utente
	 * @param stringaImmessa Stringa che compare nella riga
	 * @param ldt Oggetto LocalDateTime
	 */
	public AbstractInterrogazione(Utente u, String stringaImmessa, LocalDateTime ldt) {
		
		this.user = u;
		this.stringaImmessa = stringaImmessa;
		this.time = ldt;
	}

	/**
	 * Getter per l'Utente dell'interrogazione
	 * 
	 * @return l'utente
	 */
	public Utente getUser() {
		return user;
	}

	/**
	 * Getter per la stringa immessa dall'utente
	 * 
	 * @return la stringa immessa
	 */
	public String getStringaImmessa() {
		return stringaImmessa;
	}

	/**
	 * Getter per la data dell'interrogazione
	 * 
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
	 * Ciascuna "interrogazione" implementer� il metodo in maniera diversa, a seconda delle
	 * necessit� del programmatore
	 * 
	 * @return la stringa che deve essere stampata, a riga di comando, dall'eseguibile
	 * che interroga l'analizzatore di log
	 */
	abstract public String stampaPerDietroLeQuinte();

}
