package at.itb13.oculus.presentation;

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
