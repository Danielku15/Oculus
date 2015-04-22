package at.itb13.oculus.test;

import java.io.IOException;

import at.itb13.oculus.config.ConfigFactory;
import at.itb13.oculus.database.DBFacade;

public class Reindex {

	public static void main(String[] args) {
		try {
			ConfigFactory.getInstance().getConfig(ConfigFactory.CONFIGFILE).load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		DBFacade dbFacade = new DBFacade();
		
		try {
			dbFacade.indexAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbFacade.close();
	}
}
