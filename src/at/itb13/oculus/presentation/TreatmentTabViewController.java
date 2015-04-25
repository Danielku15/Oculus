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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;
import at.itb13.oculus.model.Appointment;

/**
 * 
 * Controller of all the tabs of treatments
 * @category ViewController
 *
 */
public class TreatmentTabViewController implements Initializable {
	
	public static final String TREATMENTVIEWXML = "TreatmentView.fxml";
	public static final String SEARCHVIEW = "SearchView.fxml";
	private static final int STAGEVIEWWIDTH = 830;
	
	//window - PatientSearchViewController
	private Stage _searchViewStage;

	@FXML
	private SearchPanelController _searchPanelController;
	@FXML
	private TabPane _tabPane;
	
	@Override
	public void initialize(URL url, ResourceBundle resBundle) {
		_tabPane.getSelectionModel().clearSelection();
		createNewTab();

		_tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
				TreatmentViewController treatmentViewController = (TreatmentViewController) newTab.getUserData();	
				treatmentViewController.activate();
			}
        });
		
		_searchPanelController.setOnSearchAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				searchTreatment();
			}
		});
	}
	
    // open new window if succeeds, if not set searchLabel red
    void searchTreatment() {	
		LangFacade facade = LangFacade.getInstance();
		_searchViewStage = new Stage();
		FXMLLoader loader = null;		
		SearchViewController<Appointment> appointmentSearchViewController = new SearchViewController<Appointment>(Appointment.class);
		
		Pane pane = null;
		try {
			loader = new FXMLLoader(this.getClass().getResource(
					SEARCHVIEW), facade.getResourceBundle());
			loader.setController(appointmentSearchViewController);
			pane = (Pane) loader.load();			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		appointmentSearchViewController.addConsumer(new Consumer<String>() {
			@Override
			public void accept(String id) {
				createForm(id);
			}
		});
		
		appointmentSearchViewController.setCriteria(_searchPanelController.getCriteria());
		appointmentSearchViewController.search();
		_searchViewStage.initModality(Modality.APPLICATION_MODAL);
		_searchViewStage.setWidth(STAGEVIEWWIDTH);
		_searchViewStage.setTitle(facade.getString(LangKey.TREATMENTSEARCHTITLE));
		_searchViewStage.setScene(new Scene(pane));
		_searchViewStage.show();
	}

	// Event Listener on Button[#_createNewPatientButton].onAction
	@FXML
	public void newTab(ActionEvent event) {
		createNewTab();
	}

	private TreatmentViewController createNewTab(){
		LangFacade facade = LangFacade.getInstance();
		Tab tab = new Tab();
		FXMLLoader loader = null;
		Pane pane = null;
		try {
			loader = new FXMLLoader(this.getClass().getResource(TREATMENTVIEWXML),
					facade.getResourceBundle());
			pane = (Pane) loader.load();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		TreatmentViewController treatmentViewController = loader.<TreatmentViewController>getController();
		tab.setUserData(treatmentViewController);
		tab.setText(facade.getString(LangKey.NEWTREATMENT));
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
		
		return treatmentViewController;
	}
	
	public void createForm(String id){
		TreatmentViewController treatmentViewController = createNewTab();
		treatmentViewController.loadAppointmentToForm(id);
		if(_searchViewStage != null) {
			_searchViewStage.close();
		}
	}
}
