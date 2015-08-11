package it.uniroma1.lcl.dietrolequinte;

public class Utente implements Comparable<Utente> {
	
	private String nomeUtente;
	
	public Utente(String nomeUtente) {
		
		this.nomeUtente = nomeUtente;
		
	}

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
