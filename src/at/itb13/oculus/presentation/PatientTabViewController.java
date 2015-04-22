package at.itb13.oculus.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.stage.Stage;
import at.itb13.oculus.application.ControllerFactory;
import at.itb13.oculus.application.SearchController;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;
import at.itb13.oculus.model.Patient;
import at.itb13.oculus.util.GUIUtil;

public class PatientTabViewController implements Initializable {
	
	public static final String PATIENTVIEWXML = "PatientView.fxml";
	public static final String SEARCHVIEW = "SearchView.fxml";
	
	//instance of PatientTabViewController
	private static PatientTabViewController _instance;
	
	//window - PatientSearchViewController
	private Stage _searchViewStage;
	private SearchController<Patient> _patientSearchController;

	@FXML
	private Button _createNewPatientButton;
	@FXML
	private TabPane _tabPane;
	@FXML
    private Button _searchPatientButton;
    @FXML
    private TextField _searchInput;
    @FXML
    private Label _searchLabel;
	
    public PatientTabViewController() {
    	_patientSearchController = ControllerFactory.getInstance().getSearchController(Patient.class);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		_instance = this;
		_tabPane.getSelectionModel().clearSelection();
		createNewTab();

		_tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldTab,
					Tab newTab) {
				PatientViewController patientViewController = (PatientViewController) newTab.getUserData();	
				patientViewController.activate();
			}
        });
		
		_searchInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					GUIUtil.validate(_searchLabel, _patientSearchController.setCriteria(_searchInput.getText()));
				}
			}
		});
	}
	
    // open new window if succeeds, if not set searchLabel red
    @FXML
    void searchPatient(ActionEvent event) {
    	LangFacade langFacade = LangFacade.getInstance();
		_searchViewStage = new Stage();
		FXMLLoader loader = null;
		SearchViewController<Patient> _patientSearchViewController = new SearchViewController<Patient>(Patient.class, _patientSearchController);
		
		Pane pane = null;
		try {
			loader = new FXMLLoader(this.getClass().getResource(
					SEARCHVIEW), langFacade.getResourceBundle());
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
		
		_patientSearchViewController.setCriteria(_searchInput.getText());
		_patientSearchViewController.search(event);
		_searchViewStage.setTitle(langFacade.getString(LangKey.SEARCH));
		_searchViewStage.setScene(new Scene(pane));
		_searchViewStage.show();
	}

	// Event Listener on Button[#_createNewPatientButton].onAction
	@FXML
	public void newTab(ActionEvent event) {
		createNewTab();
	}

	private void createNewTab(){
		LangFacade facade = LangFacade.getInstance();
		Tab tab = new Tab();
		FXMLLoader loader = null;
		Pane pane = null;
		try {
			loader = new FXMLLoader(this.getClass().getResource(PATIENTVIEWXML),
					facade.getResourceBundle());
			pane = (Pane) loader.load();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		PatientViewController patientViewController = loader
				.<PatientViewController> getController();
		
		tab.setUserData(patientViewController);
		tab.setText(facade.getString(LangKey.NEWPATIENT));
		tab.setContent(pane);
		tab.setClosable(false);
		
		_tabPane.getTabs().add(tab);
		_tabPane.getSelectionModel().select(tab);

		ObservableList<Tab> tabs = _tabPane.getTabs();

		if (tabs.size() > 1){
			for (int i = 0; i < tabs.size(); i++) {
				tabs.get(i).setClosable(true);
			}
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
		PatientViewController patientViewController = loader
				.<PatientViewController> getController();
		
		tab.setUserData(patientViewController);
		tab.setContent(pane);
		tab.setClosable(true);
		_tabPane.getTabs().add(tab);
		_tabPane.getSelectionModel().select(tab);

		patientViewController.loadPatientToFormular(id);
		
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
	
	public static PatientTabViewController getInstance(){
		return _instance;
	}
}
