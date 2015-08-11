package it.uniroma1.lcl.dietrolequinte.loader.chat;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import it.uniroma1.lcl.dietrolequinte.Utente;
import it.uniroma1.lcl.dietrolequinte.loader.AbstractLoader;

public class ChatLoader extends AbstractLoader {
	
	private List<String> specialIRCCode = Arrays.asList("***","-!-","*");


	public ChatLoader(File file) throws IOException {
		super(file);
	}

	@Override
	protected List<String> getListValidSearch() {
		return Arrays.asList("loginout","messaggio","azione");
	}

	@Override
	protected void inizializzaLoader(List<String> list) {
		list.stream().filter(s -> s != null).map(s -> Arrays.asList(s.split(" "))).forEach(this::analizzaRiga);
	}

	@Override
	protected void analizzaRiga(List<String> riga) {
		InterrogazioneChat ic;
		switch (riga.get(2)) {
		
		
		}
	}

	

}
