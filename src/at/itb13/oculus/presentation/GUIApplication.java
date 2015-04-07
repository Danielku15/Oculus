package at.itb13.oculus.presentation;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import at.itb13.oculus.config.ConfigFacade;
import at.itb13.oculus.lang.*;

public class GUIApplication extends Application {
	    
	private static Stage _stage;
	
	@Override
	public void start(Stage stage) throws Exception {
		_stage = stage;
	    
	    viewApplication(Lang.ENGLISH);
	    
	}
	
	
    public void viewApplication(Lang lang){
    	
    	try {
    		ConfigFacade.load();

    		LangFacade.load(lang);
    		LangFacade facade = LangFacade.getInstance();
			
	    	Parent root = FXMLLoader.load(GUIApplication.class.getResource("CreateNewPatientGUI.fxml"), facade.getResourceBundle());
			Scene scene = new Scene(root);
			scene.setRoot(root);
			_stage.setScene(scene);
			_stage.show();
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    public static void main(String[] args) {
        launch(args);
    }

    
    public void changeView(){
    	
    	
    }
}

	
