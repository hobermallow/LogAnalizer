package it.uniroma1.lcl.dietrolequinte.loader.query;

import java.time.LocalDateTime;

import it.uniroma1.lcl.dietrolequinte.Interrogazione;
import it.uniroma1.lcl.dietrolequinte.Utente;

public class InterrogazioneQuery extends Interrogazione {
	
	private String linkCliccato;
	private Integer posizioneLink;
	
	public InterrogazioneQuery(Utente u, String stringaImmessa, LocalDateTime ldt, String linkCliccato, Integer posizioneLink) {
		super(u, stringaImmessa, ldt);
		this.linkCliccato = linkCliccato;
		this.posizioneLink = posizioneLink;
	}
	
	public InterrogazioneQuery(Utente u, String stringaImmessa, LocalDateTime ldt) {
		super(u, stringaImmessa, ldt);
	}

	public String getLinkCliccato() {
		return linkCliccato;
	}

	public int getPosizioneLink() {
		return posizioneLink;
	}

}
