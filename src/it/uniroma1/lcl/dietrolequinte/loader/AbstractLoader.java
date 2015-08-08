package it.uniroma1.lcl.dietrolequinte.loader;

import java.util.List;


public abstract class AbstractLoader implements Loadable {
	
	
	abstract protected  List<String> getList();
	
	public boolean checkValidSearch(String string) {
		
		return getList().contains(string);
	}
	
	

	

	
}
