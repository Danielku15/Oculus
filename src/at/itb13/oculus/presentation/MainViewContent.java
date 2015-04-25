package at.itb13.oculus.presentation;

/**
 * 
 * Enumeration for all main views and its FXML files
 *
 */
public enum MainViewContent {
	PATIENTMAINVIEW("PatientMainView.fxml"),
	TREATMENTMAINVIEW("TreatmentMainView.fxml"),
	CALENDARMAINVIEW("CalendarMainView.fxml");
	
	private String _fxmlFile;
	
	private MainViewContent(String fxmlFile) {
		_fxmlFile = fxmlFile;
	}
	
	public String getFxmlFile() {
		return _fxmlFile;
	}
}
