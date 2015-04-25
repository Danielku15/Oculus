package at.itb13.oculus.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class NavigationViewController {

	@FXML
	public void openTreatmentMainView(ActionEvent event) {
		GUIApplication.setScene(MainViewContent.TREATMENTMAINVIEW);
	}
	
	@FXML
	public void openPatientMainView(ActionEvent event) {
		GUIApplication.setScene(MainViewContent.PATIENTMAINVIEW);
	}
	
	@FXML
	public void openCalendarMainView(ActionEvent event) {
		GUIApplication.setScene(MainViewContent.CALENDARMAINVIEW);
	}
}
