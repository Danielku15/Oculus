package at.itb13.oculus.presentation;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;

public class PatientTabViewController {
	
	public static final String PATIENTVIEWXML = "PatientView.fxml";
	public static final String PATIENTSEARCHVIEW = "PatientSearchView.fxml";
	
	//parent
	private PatientMainViewController _patientMainViewController;
	
	//child
	private PatientViewController _patientViewController;
	
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
	        } catch(IOException ex) {
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
		
        Tab tab = new Tab();
		 
		VBox vbox = null;
	        try {
	        	vbox = FXMLLoader.<VBox>load(this.getClass().getResource(PATIENTVIEWXML),facade.getResourceBundle());
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        
	        tab.setText(facade.getString(LangKey.NEWPATIENT));        
	        tab.setContent(vbox);
	        tab.setClosable(true);
	        _tabPane.getTabs().add(tab);
	        _tabPane.getSelectionModel().select(tab);
	        
	        ObservableList<Tab> tabs = _tabPane.getTabs();
	        
	        for (int i = 0; i < tabs.size(); i++){
	        	tabs.get(i).setClosable(true);
	        }
	        
	        // check if only one tab is shown and setCloseable to false
	        tab.setOnClosed(new EventHandler<javafx.event.Event>() {
                public void handle(javafx.event.Event e) {             
                	if (tabs.size() <= 1){
        	        	tabs.get(0).setClosable(false);
        	        }
                }
	        });
	}

	
	@FXML
	public void initialize(){
		
		System.out.println("TABVIEW");
		//System.out.println(_patientViewController);
		
		ObservableList<Tab> tabs = _tabPane.getTabs();
		
		 // check if only one tab is shown and setCloseable to false
		_newPatient.setOnClosed(new EventHandler<javafx.event.Event>() {    
			public void handle(javafx.event.Event e) {             
           	if (tabs.size() <= 1){
   	        	tabs.get(0).setClosable(false);
   	        }
           }
       });
	}
	

	public void init(PatientMainViewController patientMainViewController) {
		_patientMainViewController = patientMainViewController;
	}
	
	public void setTabLabelName(String name){
		_tabPane.getSelectionModel().getSelectedItem().setText(name);
	}
}
