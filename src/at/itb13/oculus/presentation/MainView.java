package at.itb13.oculus.presentation;

public enum MainView {
	PATIENTMAINVIEW("PatientMainView.fxml"),
	TREATMENTMAINVIEW("TreatmentMainView.fxml"),
	CALENDARMAINVIEW("CalendarMainView.fxml");
	
	private String _fxmlFile;
	
	private MainView(String fxmlFile) {
		_fxmlFile = fxmlFile;
	}
	
	public String getFxmlFile() {
		return _fxmlFile;
	}
}
