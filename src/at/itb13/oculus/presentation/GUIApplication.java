package at.itb13.oculus.presentation;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import at.itb13.oculus.lang.LangFacade;

public class GUIApplication extends Application {
	    
	public static final String PATIENTMAINVIEWXML = "PatientMainView.fxml";
	public static final String OCULUSTITEL = "Oculus";
	private static Stage _stage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		_stage = stage;
		_stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				onClose();
			}
		});

		LangFacade facade = LangFacade.getInstance();
    	try {
    		Parent root = FXMLLoader.load(GUIApplication.class.getResource(PATIENTMAINVIEWXML), facade.getResourceBundle());		 
    		Scene scene = new Scene(root);             
    		_stage.setScene(scene);
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
    		   
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        _stage.setTitle(OCULUSTITEL);
	    _stage.setX(primaryScreenBounds.getMinX());
	    _stage.setY(primaryScreenBounds.getMinY());
	    _stage.setWidth(primaryScreenBounds.getWidth());
	    _stage.setHeight(primaryScreenBounds.getHeight());
		_stage.show();
	}
	
	private void onClose() {
		System.out.println("Stage closing");
	}

}