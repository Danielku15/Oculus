package at.itb13.oculus.presentation;

import java.io.IOException;
import java.util.function.Consumer;

import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;
import at.itb13.oculus.model.Patient;
import at.itb13.oculus.util.GUIUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppointmentTabViewController {

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
	
	public AppointmentTabViewController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
    void searchPatient(ActionEvent event) {	
		
	}

	// Event Listener on Button[#_createNewPatientButton].onAction
	@FXML
	public void newTab(ActionEvent event) {
		
	}

}
