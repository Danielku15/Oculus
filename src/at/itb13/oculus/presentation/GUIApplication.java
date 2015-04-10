package at.itb13.oculus.presentation;

import java.io.IOException;
import java.net.URL;

import javafx.stage.Screen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import at.itb13.oculus.config.ConfigFacade;
import at.itb13.oculus.lang.LangFacade;

public class GUIApplication extends Application {
	    
	public static final String PATIENTVIEWXML = "CreateNewPatientGUI.fxml";
	public static final String STYLESHEETFONT = "font.css";
	
	
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
			
    		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    		
	    	Parent root = FXMLLoader.load(GUIApplication.class.getResource(PATIENTVIEWXML), facade.getResourceBundle());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(STYLESHEETFONT);
			_stage.setScene(scene);
		    _stage.setX(primaryScreenBounds.getMinX());
		    _stage.setY(primaryScreenBounds.getMinY());
		    _stage.setWidth(primaryScreenBounds.getWidth());
		    _stage.setHeight(primaryScreenBounds.getHeight());
			_stage.show();
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    public void changeView(){
    }
}

	
