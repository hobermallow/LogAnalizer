package it.uniroma1.lcl.dietrolequinte.loader;

import java.io.File;
import java.util.List;

public class Loader {
	
	private String directory;
	private File fileDirectory;
	private List<AbstractLoader> loaders;
	
	
	/**
	 * @param directory
	 */
	public Loader(String directory) {
		
		this.directory = directory;
		fileDirectory = new File(directory);
		if(!fileDirectory.exists()) {
		
		}
	}
	
	/**
	 * @return
	 */
	public String getDirectory() {
		return directory;
	}
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
	}

	/**
	 * @return
	 */
	public List<AbstractLoader> getLoaders() {
		return loaders;
	}


}
