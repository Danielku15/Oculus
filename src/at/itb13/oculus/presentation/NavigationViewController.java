package at.itb13.oculus.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * 
 * Controller for the view of the navigation
 * @category ViewController
 *
 */
public class NavigationViewController {

	@FXML
	public void openTreatmentMainView(ActionEvent event) {
		GUIApplication.setMainViewContent(MainViewContent.TREATMENTMAINVIEW);
	}
	
	@FXML
	public void openPatientMainView(ActionEvent event) {
		GUIApplication.setMainViewContent(MainViewContent.PATIENTMAINVIEW);
	}
	
	@FXML
	public void openCalendarMainView(ActionEvent event) {
		GUIApplication.setMainViewContent(MainViewContent.CALENDARMAINVIEW);
	}
}
