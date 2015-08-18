package it.uniroma1.lcl.dietrolequinte;

/**
 * @author onoda
 * Classe che rappresenta un generico utente, il cui "nome" 
 * compare come campo di tipo stringa all'interno della classe.
 * La classe implementa l'intefaccia Comparable<Utente>, in modo da
 * verificare l'uguaglianza di due utenti con esattamente lo stesso
 * nome
 *
 */
public class Utente implements Comparable<Utente> {
	
	private String nomeUtente;
	
	public Utente(String nomeUtente) {
		
		this.nomeUtente = nomeUtente;
		
	}

	/**
	 * @return il nome dell'utente
	 */
	public String getNomeUtente() {
		return nomeUtente;
	}

	@Override
	public int compareTo(Utente o) {
		return nomeUtente.compareTo(o.getNomeUtente());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Utente) {
			Utente u = (Utente)obj;
			return u.getNomeUtente().equals(this.getNomeUtente());
		}
		else return false;
	}
	
	@Override
	public String toString() {
		return getNomeUtente();
	}


	
	

}
