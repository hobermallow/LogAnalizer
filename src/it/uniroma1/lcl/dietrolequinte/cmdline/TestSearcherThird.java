import it.uniroma1.lcl.dietrolequinte.search.Searcher;
import it.uniroma1.lcl.dietrolequinte.search.SearchResult;
import it.uniroma1.lcl.dietrolequinte.Utente;
import java.time.LocalDateTime;
import java.util.List;

public class TestSearcherThird {
    public static void main(String args[]) {
	Searcher searcher = null;
	int j = Integer.parseInt(args[1]);
	try {
	    searcher = Searcher.getIstanza(args[0]);
	}
	catch (Exception e) {
	    System.out.println("Errore nel creare l'istanza del Searcher: " + e.getMessage());
	    System.exit(1);
	}
	try {
	    System.out.println(searcher.getUsers().size());
	    String types[] = {"interrogazione", "loginout", "messaggio", "azione"};
	    for (Utente u : searcher.getUsers()) {
		System.out.println("Informazioni sull'utente: " + u.toString());
		for (int i = 0; i < types.length; i++) {
		    Integer size;
		    size = null;
		    try {
			if (j == 0) {
			    size = searcher.search(types[i], u).size();
			}
			else if (j == 1) {
			    size = searcher.search(types[i], u, LocalDateTime.parse(args[2]), LocalDateTime.parse(args[3])).size();
			}
			else if (j == 2) {
			    size = searcher.search(types[i], u, args[2]).size();
			}
			else {
			    size = searcher.search(types[i], u, args[2], LocalDateTime.parse(args[3]), LocalDateTime.parse(args[4])).size();
			}
			System.out.println("Dimensione per tipo " + types[i] + " e modalita' " + j + " per l'utente " + u.toString() + ": " + size);
		    }
		    catch (Exception e) {
			System.out.println("Errore in " + types[i] + " e modalita' " + j + " per l'utente " + u.toString() + "\n\t\t" + e.getMessage());
		    }
		}
	    }
	}
	catch (Exception e) {
	    System.out.println("Errore getUsers: " + e.getMessage());
	    System.exit(1);
	}
    }
}
