/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 22.04.2015
 */
package at.itb13.oculus.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import at.itb13.oculus.application.ControllerFactory;
import at.itb13.oculus.application.ObjectNotFoundException;
import at.itb13.oculus.application.TreatmentController;

/**
 * 
 * Controller for the view of treatments
 * @category ViewController
 *
 */
public class TreatmentViewController {

	//Application Controller
	private TreatmentController _treatmentController;
	
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

	public TreatmentViewController(){
		_treatmentController = ControllerFactory.getInstance().getTreatmentController();
	}
	
	public void loadAppointmentToForm(String appointmentId){
		try {
			_treatmentController.loadAppointment(appointmentId);
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
		}
		setDataToForm();
		//setTabLabelNameIsUnmodified();
	}
	
	private void setDataToForm() {
		_firstnameLoadedLabel.setText(_treatmentController.getPatientFirstname());
		_lastnameLoadedLabel.setText(_treatmentController.getPatientLastname());
		_socialSecurityLoadedNumberLabel.setText(_treatmentController.getPatientSocialSecurityNumber());
	}
	
	public void activate(){
		_treatmentController.activate();
	}
}
