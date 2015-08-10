package it.uniroma1.lcl.dietrolequinte.loader.query;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

import it.uniroma1.lcl.dietrolequinte.loader.AbstractLoader;

public class QueryLoader extends AbstractLoader {



	public QueryLoader(File file) throws IOException {
		super(file);
	}

	@Override
	protected List<String> getList() {
		return null;
	}

	@Override
	protected void inizializzaLoader(BufferedReader br) {
		
		
		

		
	}

}
