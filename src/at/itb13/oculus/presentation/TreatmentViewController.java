/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 22.04.2015
 */
package at.itb13.oculus.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import at.itb13.oculus.application.ControllerFactory;
import at.itb13.oculus.application.ObjectNotFoundException;
import at.itb13.oculus.application.TreatmentController;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;

/**
 * 
 * Controller for the view of treatments
 * @category ViewController
 *
 */
public class TreatmentViewController {

	//Application Controller
	private TreatmentController _treatmentController;
	/**
	 * parent - {@link TreatmentTabViewController}
	 */
	@FXML
	private TreatmentTabViewController _treatmentTabViewController;
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
		_treatmentTabViewController = TreatmentTabViewController.getInstance();
	}
	
	public void loadAppointmentToForm(String appointmentId){
		try {
			_treatmentController.loadAppointment(appointmentId);
		} catch (ObjectNotFoundException e) {
			LangFacade facade = LangFacade.getInstance();
			Alert errorDialog = new Alert(AlertType.ERROR);
			errorDialog.setTitle(facade.getString(LangKey.ERRORDIALOGTITEL));
			errorDialog.setHeaderText(facade.getString(LangKey.OBJECTNOTFOUNDHEADER));
			errorDialog.setContentText(facade.getString(LangKey.OBJECTNOTFOUNDCONTENT) + " " + appointmentId);
			errorDialog.showAndWait();
		}
		setDataToForm();
		setTabLabelNameModified(false);
	}
	
	private void setTabLabelNameModified(boolean isModified){
		String tabLabelName = "";
		if (isModified){
			tabLabelName = "*";
		}	
		tabLabelName += _treatmentController.getPatientFirstname().charAt(0) + ". " + _treatmentController.getPatientLastname();
		_treatmentTabViewController.setTabLabelName(tabLabelName);
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
