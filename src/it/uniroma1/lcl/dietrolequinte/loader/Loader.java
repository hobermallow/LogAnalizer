package it.uniroma1.lcl.dietrolequinte.loader;

import java.io.File;
import java.util.List;

import it.uniroma1.lcl.dietrolequinte.exception.DirectoryNotFoundException;
import it.uniroma1.lcl.dietrolequinte.exception.EmptyDirectoryException;
import it.uniroma1.lcl.dietrolequinte.exception.NotADirectoryException;

public class Loader {
	
	private String directory;
	private File fileDirectory;
	private List<AbstractLoader> loaders;
	
	
	/**
	 * @param directory
	 * @throws DirectoryNotFoundException 
	 * @throws NotADirectoryException 
	 * @throws EmptyDirectoryException 
	 */
	public Loader(String directory) throws DirectoryNotFoundException, NotADirectoryException, EmptyDirectoryException {
		
		this.directory = directory;
		fileDirectory = new File(directory);
		if(!fileDirectory.exists()) {
			
			throw new DirectoryNotFoundException();
		
		}
		if(fileDirectory.isFile()) {
			throw new NotADirectoryException();
		}
		if(fileDirectory.list().length == 0) {
			throw new EmptyDirectoryException();
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
