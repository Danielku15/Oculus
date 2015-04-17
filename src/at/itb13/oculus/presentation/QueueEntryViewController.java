/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 16.04.2015
 */
package at.itb13.oculus.presentation;

import java.io.Serializable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import at.itb13.oculus.application.QueueEntryControllerImpl;

/**
 * @author Manu
 *
 */
public class QueueEntryViewController implements Serializable {

	
	private QueueEntryControllerImpl _queueEntryController;
	
	@FXML
	private TextField _patientTbx;
	
	@FXML
	private ChoiceBox<String> _appointmentCbx;
	
	@FXML
	private ChoiceBox<String> _queueCbx;
	
	@FXML
	private Button _searchBtn;
	
	@FXML
	private Button _okBtn;
	
	
	public void initialize(){
		
	}
		
}
