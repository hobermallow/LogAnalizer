package it.uniroma1.lcl.dietrolequinte.loader.query;

import java.time.LocalDateTime;

import it.uniroma1.lcl.dietrolequinte.Interrogazione;
import it.uniroma1.lcl.dietrolequinte.Utente;

public class InterrogazioneQuery extends Interrogazione {
	
	private String linkCliccato;
	private int posizioneLink;
	
	public InterrogazioneQuery(Utente u, String stringaImmessa, LocalDateTime ldt, String linkCliccato, int posizioneLink) {
		super(u, stringaImmessa, ldt);
		this.linkCliccato = linkCliccato;
		this.posizioneLink = posizioneLink;
	}

	public String getLinkCliccato() {
		return linkCliccato;
	}

	public int getPosizioneLink() {
		return posizioneLink;
	}

}
