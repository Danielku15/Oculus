package at.itb13.oculus.presentation;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import at.itb13.oculus.config.ConfigFacade;
import at.itb13.oculus.lang.LangFacade;

public class GUIApplication extends Application {
	    
	public static final String PATIENTVIEWXML = "CreateNewPatientGUI.fxml";
	
	private static Stage _stage;
	
    public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage stage) throws Exception {
		_stage = stage;
	    viewApplication();
	}
	
    public void viewApplication(){
    	
    	try {
    		ConfigFacade.load();
    		
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
    
    public void changeView(){
    }
}

	
