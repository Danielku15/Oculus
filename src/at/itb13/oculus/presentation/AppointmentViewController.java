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
	private Label _lastnameLabel;
	
	@FXML
	private Label _socialSecurityNumberLabel;


	public AppointmentViewController(){
		
		_appointmentController = ControllerFactory.getAppointmentController();
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	

	
	
}
