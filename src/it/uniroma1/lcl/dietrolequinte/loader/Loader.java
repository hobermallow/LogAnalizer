package it.uniroma1.lcl.dietrolequinte.loader;

import java.io.File;

public class Loader {
	
	private String directory;
	private File fileDirectory;
	
	
	public Loader(String directory) {
		
		this.directory = directory;
		fileDirectory = new File(directory);
	}
	
	public String getDirectory() {
		return directory;
	}
	
	
	public static void main(String[] args) {
		
		Loader loader = new Loader(args[0]);
		
		
	}

}
