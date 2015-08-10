package it.uniroma1.lcl.dietrolequinte.cmdline;

import it.uniroma1.lcl.dietrolequinte.exception.EndProgramException;
import it.uniroma1.lcl.dietrolequinte.search.Searcher;

public class DietroLeQuinte {
	
	private String nomeDirectory;
	private Searcher searcher;
	
	public DietroLeQuinte(String nomeDirectory) throws EndProgramException {
		this.nomeDirectory = nomeDirectory;
		searcher = Searcher.getIstanza(nomeDirectory);
	}
	
	public static void main(String[] args) {
		
		try {
			DietroLeQuinte dlq = new DietroLeQuinte(args[0]);
		} catch (EndProgramException e) {
			return;
		}
		
	}
}
