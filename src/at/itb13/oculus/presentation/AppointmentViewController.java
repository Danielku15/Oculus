/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 22.04.2015
 */
package at.itb13.oculus.presentation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import at.itb13.oculus.application.AppointmentController;
import at.itb13.oculus.application.ControllerFactory;
import at.itb13.oculus.application.ObjectNotFoundException;

/**
 * @author Manu
 *
 */
public class AppointmentViewController implements Initializable {

	//Application Controller
	private AppointmentController _appointmentController;

	
	@FXML
	private Label _firstnameLabel;
	
	@FXML
	private Label _firstnameLoadedLabel;
	
	@FXML
	private Label _lastnameLabel;
	
	@FXML
	private Label _lastnameLoadedLabel;
	
	@FXML
	private Label _socialSecurityNumberLabel;
	
	@FXML
	private Label _socialSecurityLoadedNumberLabel;


	public AppointmentViewController(){
		
		_appointmentController = ControllerFactory.getAppointmentController();
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		setPatientDataToFormular();
	}
	
	
	public void setPatientDataToFormular(){
		
		_firstnameLoadedLabel.setText(_appointmentController.getFirstName());
		_lastnameLabel.setText(_appointmentController.getLastName());
		_socialSecurityLoadedNumberLabel.setText(_appointmentController.getSocialSecurityNumber());
	}

	
	
}
