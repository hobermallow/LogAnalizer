package it.uniroma1.lcl.dietrolequinte.cmdline;

import it.uniroma1.lcl.dietrolequinte.search.Searcher;

public class DietroLeQuinte {
	
	private String nomeDirectory;
	private Searcher searcher;
	
	public DietroLeQuinte(String nomeDirectory) {
		this.nomeDirectory = nomeDirectory;
		searcher = Searcher.getIstanza(nomeDirectory);
	}
	
	public static void main(String[] args) {
		
		DietroLeQuinte dlq = new DietroLeQuinte(args[0]);
		
	}
}
