package at.itb13.oculus.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;

public class PatientTabViewController implements Initializable{
	
	public static final String PATIENTVIEWXML = "PatientView.fxml";
	public static final String PATIENTSEARCHVIEW = "PatientSearchView.fxml";
	private static final Color COLOR_FAIL = Color.RED;
	private static final Color COLOR_SUCCESS = Color.BLACK;
	//instance of PatientTabViewController
	private static PatientTabViewController _instance;
	//parent - PatientMainViewController
	@FXML
	private PatientMainViewController _patientMainViewController;	
	//child - PatientViewController
	@FXML
	private PatientViewController _patientViewController;
	//window - PatientSearchViewController
	@SuppressWarnings("unused")
	private PatientSearchViewController _patientSearchViewController;
	@FXML
	private Button _createNewPatientButton;
	@FXML
	private TabPane _tabPane;
	@FXML
	private Tab _newPatient;
	@FXML
    private Button _searchPatientButton;
    @FXML
    private TextField _searchInput;
    @FXML
    private Label _searchLabel;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		_instance = this;
		
		_patientViewController.init(this);
		
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
	
    // open new window if succeeds, if not set searchLabel red
    @FXML
    void searchPatient(ActionEvent event) {
		String query = _searchInput.getText();			
		LangFacade facade = LangFacade.getInstance();
		Stage stage = new Stage();
		FXMLLoader loader = null;
		Pane pane = null;
		try {
			loader = new FXMLLoader(this.getClass().getResource(
					PATIENTSEARCHVIEW), facade.getResourceBundle());
			pane = (Pane) loader.load();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		PatientSearchViewController _patientSearchViewController = loader
				.<PatientSearchViewController> getController();
		if (_patientSearchViewController.setCriteria(query)) {
			setSuccessToSearchLabel();
			_patientSearchViewController.search(event);

			stage.setScene(new Scene(pane));
			stage.show();
		} else {
			setFailToSearchLabel();
		}
	}

    // creates new tab with formular included
	// Event Listener on Button[#_createNewPatientButton].onAction
	@FXML
	public void createNewTab(ActionEvent event) {
		LangFacade facade = LangFacade.getInstance();
		Tab tab = new Tab();
		Pane pane = null;
		try {
			pane = FXMLLoader.load(this.getClass().getResource(PATIENTVIEWXML),
					facade.getResourceBundle());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		tab.setText(facade.getString(LangKey.NEWPATIENT));
		tab.setContent(pane);
		tab.setClosable(true);
		_tabPane.getTabs().add(tab);
		_tabPane.getSelectionModel().select(tab);

		ObservableList<Tab> tabs = _tabPane.getTabs();

		for (int i = 0; i < tabs.size(); i++) {
			tabs.get(i).setClosable(true);
		}

		// check if only one tab is shown and setCloseable to false
		tab.setOnClosed(new EventHandler<javafx.event.Event>() {
			public void handle(javafx.event.Event e) {
				if (tabs.size() <= 1) {
					tabs.get(0).setClosable(false);
				}
			}
		});
	}
	
	//init parent - PatientMainViewController
	public void init(PatientMainViewController patientMainViewController) {
		_patientMainViewController = patientMainViewController;
	}
	
	public void setTabLabelName(String name){
		_tabPane.getSelectionModel().getSelectedItem().setText(name);
	}
	
	private void setFailToSearchLabel() {
		_searchLabel.setTextFill(COLOR_FAIL);
	}

	private void setSuccessToSearchLabel() {
		_searchLabel.setTextFill(COLOR_SUCCESS);
	}
	
	public static PatientTabViewController getInstance(){
		return _instance;
	}
	
}
