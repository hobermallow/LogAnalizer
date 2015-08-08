package it.uniroma1.lcl.dietrolequinte.loader;

import java.util.Collection;

import it.uniroma1.lcl.dietrolequinte.Interrogazione;
import it.uniroma1.lcl.dietrolequinte.Utente;

public interface Loadable {
	

	
	Collection<Utente> getUsers();
	
	Collection<Interrogazione> getInterrogazioni();
	

}
