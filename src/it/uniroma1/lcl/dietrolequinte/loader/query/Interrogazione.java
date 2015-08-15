package it.uniroma1.lcl.dietrolequinte.loader.query;

import java.time.LocalDateTime;

import it.uniroma1.lcl.dietrolequinte.AbstractInterrogazione;
import it.uniroma1.lcl.dietrolequinte.Utente;

public class Interrogazione extends AbstractInterrogazione {
	
	private String linkCliccato;
	private Integer posizioneLink;
	
	public Interrogazione(Utente u, String stringaImmessa, LocalDateTime ldt, String linkCliccato, Integer posizioneLink) {
		super(u, stringaImmessa, ldt);
		this.linkCliccato = linkCliccato;
		this.posizioneLink = posizioneLink;
	}
	
	public Interrogazione(Utente u, String stringaImmessa, LocalDateTime ldt) {
		super(u, stringaImmessa, ldt);
		this.linkCliccato = "";
		this.posizioneLink = 0;
	}

	public String getLinkCliccato() {
		return linkCliccato;
	}

	public int getPosizioneLink() {
		return posizioneLink;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Link Cliccato: "+linkCliccato+"\n");
		sb.append("Posizione Link: "+posizioneLink+"\n");
		return super.toString()+sb.toString();
	}

	@Override
	public String stampaPerDietroLeQuinte() {
		return getStringaImmessa();
	}

}
