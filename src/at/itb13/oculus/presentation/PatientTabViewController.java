package at.itb13.oculus.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import at.itb13.oculus.model.Patient;

public class PatientTabViewController implements Initializable{
	
	public static final String PATIENTVIEWXML = "PatientView.fxml";
	public static final String SEARCHVIEW = "SearchView.fxml";
	private static final Color COLOR_FAIL = Color.RED;
	private static final Color COLOR_SUCCESS = Color.web("0x333333ff");
	
	//instance of PatientTabViewController
	private static PatientTabViewController _instance;
	//parent - PatientMainViewController
	@FXML
	private PatientMainViewController _patientMainViewController;	
	//child - PatientViewController
	@FXML
	private PatientViewController _patientViewController;
	//window - PatientSearchViewController
	
	private Stage _searchViewStage;
	
	@SuppressWarnings("unused")
	private SearchViewController<Patient> _patientSearchViewController;
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
		_searchViewStage = new Stage();
		FXMLLoader loader = null;		
		SearchViewController<Patient> _patientSearchViewController = new SearchViewController<Patient>(Patient.class);
		
		Pane pane = null;
		try {
			loader = new FXMLLoader(this.getClass().getResource(
					SEARCHVIEW), facade.getResourceBundle());
			loader.setController(_patientSearchViewController);
			pane = (Pane) loader.load();			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		_patientSearchViewController.addConsumer(new Consumer<String>() {
			@Override
			public void accept(String id) {
				createFormular(id);
			}
		});
		if (_patientSearchViewController.setCriteria(query)) {
			setSuccessToSearchLabel();
			_patientSearchViewController.search(event);
			_searchViewStage.setTitle(facade.getString(LangKey.SEARCH));
			_searchViewStage.setScene(new Scene(pane));
			_searchViewStage.show();
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

	public void createFormular(String id){
		LangFacade facade = LangFacade.getInstance();
		FXMLLoader loader = null;
		Pane pane = null;
		Tab tab = new Tab();
		try {
			loader = new FXMLLoader(this.getClass().getResource(
					PATIENTVIEWXML), facade.getResourceBundle());
			pane = (Pane) loader.load();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		PatientViewController loadPatient = loader
				.<PatientViewController> getController();
		
		tab.setContent(pane);
		tab.setClosable(true);
		_tabPane.getTabs().add(tab);
		_tabPane.getSelectionModel().select(tab);

		loadPatient.loadPatientToFormular(id);
		
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
		
		_searchViewStage.close();
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
