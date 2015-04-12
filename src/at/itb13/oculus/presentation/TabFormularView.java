package at.itb13.oculus.presentation;

import java.io.IOException;

import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TabFormularView {
	
	public static final String FORMULARVIEWXML = "Formular.fxml";
	public static final String PATIENTSEARCHVIEW = "PatientSearchView.fxml";
	
	@FXML
	private Button _createTabButton;
	@FXML
	private TabPane _tabPane;
	@FXML
	private Tab _newPatient;
	@FXML
    private Button _searchPatient;
    @FXML
    private VBox _vBox;
	
    // open new window with patient search
    @FXML
    void searchPatient(ActionEvent event) {
    	LangFacade facade = LangFacade.getInstance();
		
    	Stage stage = new Stage();
		Pane pane = null;
		
	        try {
	            pane = FXMLLoader.<Pane>load(this.getClass().getResource(PATIENTSEARCHVIEW),facade.getResourceBundle());
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	     
         stage.setScene(new Scene(pane));  
         stage.show();
    }

    // creates new tab with formular included
	// Event Listener on Button[#_createTabButton].onAction
	@FXML
	public void createNewTab(ActionEvent event) {
		LangFacade facade = LangFacade.getInstance();
		 
		 Pane pane = null;
	        try {
	            pane = FXMLLoader.<Pane>load(this.getClass().getResource(FORMULARVIEWXML),facade.getResourceBundle());
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        Tab tab = new Tab(); 
	        
	        tab.setText(facade.getString(LangKey.NEWPATIENT));        
 
	        tab.setContent(pane);
	        tab.setClosable(true);
	        _tabPane.getTabs().add(tab);
	        _tabPane.getSelectionModel().select(tab);
	        
	        ObservableList<Tab> tabs = _tabPane.getTabs();
	        
	        for (int i = 0; i < tabs.size(); i++){
	        	tabs.get(i).setClosable(true);
	        	
	        	if (_tabPane.getTabs().size() < 1){
	        		
	        		tabs.get(i).setClosable(false);
	        	}
	        }
	        
	        tab.setOnClosed(new EventHandler<javafx.event.Event>() {
                public void handle(javafx.event.Event e) {             
                	if (tabs.size() <= 1){
        	        	tabs.get(0).setClosable(false);
        	        }
                }
	        });
	}
}
