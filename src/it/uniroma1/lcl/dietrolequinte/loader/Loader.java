package it.uniroma1.lcl.dietrolequinte.loader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
		loaders = new ArrayList<>();
		if(!fileDirectory.exists()) {
			
			throw new DirectoryNotFoundException();
		
		}
		if(fileDirectory.isFile()) {
			throw new NotADirectoryException();
		}
		if(fileDirectory.list().length == 0) {
			throw new EmptyDirectoryException();
		}
		
		/* A questo punto, si è sicuri che la cartella esista e
		non sia vuota. Si può procedere a filtrare la lista dei file e ad
		eliminare quelli che non sono file di log e a creare, per quelli rimasti, lo specifico
		AbstractLoader */
		Arrays.stream(fileDirectory.listFiles()).filter(this::fileIsLogFile).forEach(this::createSpecificLoader);
	}
	
	/**
	 * @return Restituisce il pathname della cartella su cui 
	 * viene eseguito il programma
	 */
	public String getDirectory() {
		return directory;
	}
	
	/**
	 * @param f File object da controllare
	 * @return booleano, true se il file è file di log, 
	 * altrimenti false
	 */
	private boolean fileIsLogFile(File f) {
		String format, zippedFormat;
		format = f.getName().split("\\.")[f.getName().split("\\.").length-1];
		zippedFormat = f.getName().split("\\.")[f.getName().split("\\.").length-2];
		return (format.equals(LogFileExtensions.log.toString()) || format.equals(LogFileExtensions.txt.toString())) || (format.equals(LogFileExtensions.gz.toString()) && (zippedFormat.equals(LogFileExtensions.txt.toString()) || zippedFormat.equals(LogFileExtensions.log.toString())));
		
	}
	
	/**
	 * @param f file a partire dal quale creare il loader
	 * Il metodo aggiunge allla lista dei loader il loader specifico
	 * per il file passato in input
	 */
	private void createSpecificLoader(File f) {
		String type = f.getName().split("\\.")[0];
		String path = "it.uniroma1.lcl.dietrolequinte."+"loader."+type+"."+Character.toUpperCase(type.charAt(0))+type.substring(1)+"Loader";
		try {
			loaders.add((AbstractLoader) Class.forName(path).getConstructor(File.class).newInstance(f));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException  e) {
			e.printStackTrace();
			return;
		}
		/* caso in cui non sia stato creato il loader specifico
		 * relativo al file f
		 */
		catch (ClassNotFoundException e) {
			System.out.println("Non esiste loader per il tipo di file specificato");
			return;
		}
	}
	
	/**
	 * @return Restituisce la lista dei loader specifici, 
	 * inizializzati per ogni file di log presente
	 * nella cartella
	 */
	public List<AbstractLoader> getLoaders() {
		return loaders;
	}

	
	
	
	
	/**
	 * @param args
	 * @throws EmptyDirectoryException 
	 * @throws NotADirectoryException 
	 * @throws DirectoryNotFoundException 
	 */
	public static void main(String[] args) throws DirectoryNotFoundException, NotADirectoryException, EmptyDirectoryException {
		Loader l = new Loader("/home/onoda/Documents/progetto_metodologie2015/AOL");
		System.out.println(l.getLoaders().size());
		
		
	}

	

}
