package it.uniroma1.lcl.dietrolequinte.cmdline;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import it.uniroma1.lcl.dietrolequinte.exception.EndProgramException;
import it.uniroma1.lcl.dietrolequinte.search.Searcher;

public class DietroLeQuinte {
	
	private String nomeDirectory;
	private Searcher searcher;
	
	public DietroLeQuinte(String nomeDirectory) throws EndProgramException {
		this.nomeDirectory = nomeDirectory;
		searcher = Searcher.getIstanza(nomeDirectory);
	}
	
	private static void cicloProgramma() throws IOException {
		
		for(;;) {
			List<String> l = prendiInput();
			if(l.contains("exit")) return;
		}
		
	}
	
	private static List<String> prendiInput() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		return Arrays.asList(br.readLine().split("\\s+"));
	}

	public static void main(String[] args) throws IOException {
		
		try {
			DietroLeQuinte dlq = new DietroLeQuinte(args[0]);
		} catch (EndProgramException e) {
			e.printStackTrace();
			return;
		}
		cicloProgramma();
		
		
		
	}
}
