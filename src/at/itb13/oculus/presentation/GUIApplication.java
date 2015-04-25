package at.itb13.oculus.presentation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import at.itb13.oculus.application.ControllerFactory;
import at.itb13.oculus.lang.LangFacade;

public class GUIApplication extends Application {
	
	public static final String OCULUSTITEL = "Oculus";
	private static Map<MainView, Scene> _sceneMap;
	private static Stage _stage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		_sceneMap = new HashMap<MainView, Scene>();
		
		_stage = stage;
		_stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				onClose();
			}
		});
    	
		initScenes();
		setScene(MainView.PATIENTMAINVIEW);
		
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
	
	private void initScenes() {
		for(MainView mainView : MainView.values()) {
	    	try {
	    		Parent root = FXMLLoader.load(getClass().getResource(mainView.getFxmlFile()), LangFacade.getInstance().getResourceBundle());		 
	    		_sceneMap.put(mainView, new Scene(root));			    	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void setScene(MainView mainView) {
		if(mainView != null) {
			ControllerFactory.getInstance().getMainController().setMainView(mainView);
    		_stage.setScene(_sceneMap.get(mainView));
		}
	}
}