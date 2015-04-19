/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 10.04.2015
 */
package at.itb13.oculus.test;

import java.io.IOException;

import at.itb13.oculus.config.ConfigFactory;
import at.itb13.oculus.config.ConfigFactory.Config;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.presentation.GUIApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Manu
 *
 */
public class MainManu extends Application{

	/**
	 * @param args
	 */
	public static final String PATIENTVIEWXML = "QueueEntryView.fxml";

		private Stage _stage;
		
		public static void main(String[] args) {
			launch(args);
		}
		
		@Override
		public void start(Stage stage) throws Exception {
			_stage = stage;
		    viewApplication();
		}
		
		public void close() {
			
		}
		
	    public void viewApplication(){
	    	
	    	try {
				ConfigFactory configFactory = ConfigFactory.getInstance();
				Config config = configFactory.getConfig(ConfigFactory.CONFIGFILE);
				config.load();
	    		LangFacade.load();
	    		LangFacade facade = LangFacade.getInstance();
		    	Parent root = FXMLLoader.load(GUIApplication.class.getResource(PATIENTVIEWXML), facade.getResourceBundle());
				Scene scene = new Scene(root);
				_stage.setScene(scene);
				_stage.show();
		    	
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    }

}
