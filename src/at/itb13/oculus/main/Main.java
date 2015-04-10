package at.itb13.oculus.main;

import java.io.IOException;

import at.itb13.oculus.config.ConfigFacade;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.presentation.GUIApplication;

public class Main {

	public static void main(String[] args) {
		new Main().init(args);
	}
	
	public void init(String[] args) {
		try {
			ConfigFacade.load();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		try {
			LangFacade.load();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		GUIApplication.main(args);
	}
}
