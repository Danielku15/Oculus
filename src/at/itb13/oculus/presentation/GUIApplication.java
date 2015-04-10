package at.itb13.oculus.presentation;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
<<<<<<< HEAD
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
=======
import javafx.stage.Screen;
>>>>>>> dde4d7217372dbb6499cb576fa4cf450c983a9e1
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
		_stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				onClose();
			}
		});
	    viewApplication();
	}
	
	private void onClose() {
		System.out.println("Stage closing");
	}
	
    public void viewApplication(){
    	
    	try {
<<<<<<< HEAD
    		
    		ConfigFacade.load();
    		
    		LangFacade.load();
=======
>>>>>>> dde4d7217372dbb6499cb576fa4cf450c983a9e1
    		LangFacade facade = LangFacade.getInstance();
		
    		final Menu menu1 = new Menu("File");
    		final Menu menu2 = new Menu("Options");
    		final Menu menu3 = new Menu("Help");
    		 
    		/*
    		 MenuBar menuBar = new MenuBar();
    		 menuBar.getMenus().addAll(menu1, menu2, menu3);
    		*/
    		
    		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    		
	    	//Parent root = FXMLLoader.load(GUIApplication.class.getResource(PATIENTVIEWXML), facade.getResourceBundle());
			//Scene scene = new Scene(root);
		
			Scene scene = new Scene(new VBox(), 400, 350);
	        scene.setFill(Color.OLDLACE);
	 
	        MenuBar menuBar = new MenuBar();	 
	        Menu menuFile = new Menu("File");	 
	        Menu menuEdit = new Menu("Edit");	 	     
	        Menu menuView = new Menu("View");	 
	        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
			

	        ((VBox) scene.getRoot()).getChildren().addAll(menuBar);
	 
  

	         
	        SplitPane splitPane = new SplitPane();
	        splitPane.setDividerPositions(0.3f, 0.6f, 0.9f);
	        
	        splitPane.setOrientation(Orientation.HORIZONTAL);
	      
	        /*
	        splitPane.setPrefSize(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
	        final Pane pane = new Pane();
	        pane.setPrefSize(600,primaryScreenBounds.getHeight());
	        Label label = new Label();
	        label.setPrefSize(600, primaryScreenBounds.getHeight());
	        
	        final AnchorPane anchorPane = new AnchorPane();
	        anchorPane.setPrefSize(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
	        
	        
	        splitPane.getItems().addAll(pane, anchorPane);
	        */
	        
	        splitPane.getStylesheets().add("splitPane.css");
	        
	        ((VBox) scene.getRoot()).getChildren().add(splitPane);
	        
	     
	        
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

	
