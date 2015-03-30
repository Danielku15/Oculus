package at.itb13.oculus.test;

import java.io.IOException;

import at.itb13.oculus.config.Config;
import at.itb13.oculus.config.ConfigFacade;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;

public class Main {
	public static void main(String[] args) {
		try {
			ConfigFacade.load();
			ConfigFacade configFacade = ConfigFacade.getInstance();
			System.out.println(configFacade.getProperty(Config.LANGUAGE));
			System.out.println(configFacade.getProperty(Config.COUNTRY));
			
			LangFacade.load();
			LangFacade langFacade = LangFacade.getInstance();
			System.out.println(langFacade.getString(LangKey.DOCTOR));
			System.out.println(langFacade.getString(LangKey.ORTHOPTIST));
			System.out.println(langFacade.getString(LangKey.RECEPTIONIST));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
