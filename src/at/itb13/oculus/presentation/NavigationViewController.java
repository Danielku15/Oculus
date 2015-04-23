package at.itb13.oculus.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import at.itb13.oculus.presentation.GUIApplication.MainView;

public class NavigationViewController {

	@FXML
	public void openTreatmentMainView(ActionEvent event) {
		GUIApplication.setScene(MainView.TREATMENTMAINVIEW);
	}
	
	@FXML
	public void openPatientMainView(ActionEvent event) {
		GUIApplication.setScene(MainView.PATIENTMAINVIEW);
	}
	
	@FXML
	public void openCalendarMainView(ActionEvent event) {
		GUIApplication.setScene(MainView.CALENDARMAINVIEW);
	}
}
