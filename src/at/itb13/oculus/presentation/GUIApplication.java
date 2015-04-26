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

/**
 * 
 * Controller for the main construct of this application
 * @category ViewController
 *
 */
public class GUIApplication extends Application {
	
	public static final String OCULUSTITEL = "Oculus";
	private static final String MAINVIEW = "MainView.fxml";
	
	public static MainViewController _mainViewController;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				onClose();
			}
		});
    	
		initMainView(stage);
		
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setTitle(OCULUSTITEL);
	    stage.setX(primaryScreenBounds.getMinX());
	    stage.setY(primaryScreenBounds.getMinY());
	    stage.setWidth(primaryScreenBounds.getWidth());
	    stage.setHeight(primaryScreenBounds.getHeight());
		stage.show();
	}
	
	private void onClose() {
		System.out.println("Stage closing");
		System.exit(0);
	}
	
	private void initMainView(Stage stage) {
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(getClass().getResource(MAINVIEW), LangFacade.getInstance().getResourceBundle());
			Parent mainView = (Parent) loader.load();
			_mainViewController = loader.getController();
			_mainViewController.setContent(MainViewContent.PATIENTMAINVIEW);
			stage.setScene(new Scene(mainView));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setMainViewContent(MainViewContent content) {
		_mainViewController.setContent(content);
	}
}
