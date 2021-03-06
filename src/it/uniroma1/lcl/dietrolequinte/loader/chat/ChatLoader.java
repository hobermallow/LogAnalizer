package it.uniroma1.lcl.dietrolequinte.loader.chat;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.uniroma1.lcl.dietrolequinte.Utente;
import it.uniroma1.lcl.dietrolequinte.loader.AbstractLoader;
import it.uniroma1.lcl.dietrolequinte.loader.chat.Messaggio;

/**
 * Classe che si occupa di leggere il file in input di tipo IRC, caricarlo in memoria e analizzarlo
 * @author Steve
 *
 */
public class ChatLoader extends AbstractLoader {
	
	/**
	 * 
	 * @param file file di log IRC
	 * @throws IOException
	 */
	public ChatLoader(File file) throws IOException {
		super(file);
	}

	@Override
	protected List<String> getListValidSearch() {
		return Arrays.asList("loginout","messaggio","azione","login", "logout");
	}

	@Override
	protected void inizializzaLoader(List<String> list) {
		list.stream().filter(s -> s != null).map(s -> Arrays.asList(s.split("\\s++"))).forEach(this::analizzaRiga);
	}

	@Override
	protected void analizzaRiga(List<String> riga) {
		switch (riga.get(1)) {
		
		case "***": {
			if(riga.contains("quit") || riga.contains("left")) {
				addInterrogazione(new Logout(new Utente(riga.get(2)), String.join(" ",riga.subList(2, riga.size())), LocalDateTime.parse(riga.get(0)), ""));
				addUtente(new Utente(riga.get(2)));
			}
			else if(riga.contains("known")){}
			else {
				addInterrogazione(new Login(new Utente(riga.get(2)), String.join(" ",riga.subList(2, riga.size())), LocalDateTime.parse(riga.get(0)), ""));
				addUtente(new Utente(riga.get(2)));
			}
			break;
		}
		
		case "*": {
			addInterrogazione(new Azione(new Utente(riga.get(2)), String.join(" ",riga.subList(2, riga.size())), LocalDateTime.parse(riga.get(0)), String.join(" ", riga.subList(3, riga.size()))));
			addUtente(new Utente(riga.get(2)));
			break;
			
		}
		
		default: {
			addInterrogazione(new Messaggio(new Utente(riga.get(1).replace("<", " ").replace(">", " ").trim()), String.join(" ", riga.subList(2, riga.size())), LocalDateTime.parse(riga.get(0)), String.join(" ", riga.subList(2, riga.size()))));
			addUtente(new Utente(riga.get(1).replace("<", " ").replace(">", " ").trim()));
		}
		}
	}
	@Override
	public String modifystr(String s) {
		switch(s){
			case "messaggio":
				return "messaggi";
			case "loginout":
				return "login/logout";
			case "azione":
				return "azioni";
		}
		return s;
	}
	@Override
	public List<String> modifyValidSearchList(List<String> ss) {
		List<String> newValidSearch=new ArrayList<String>(ss);
		newValidSearch.removeAll(Arrays.asList("logout","login"));
		return newValidSearch;
	}
}
