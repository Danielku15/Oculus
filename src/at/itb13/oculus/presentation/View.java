package at.itb13.oculus.presentation;

public enum View {
	QUEUEENTRYVIEW("QueueEntryView.fxml"),
	SEARCHVIEW("SearchView.fxml");
	
	private String _fxmlFile;
	
	private View(String fxmlFile) {
		_fxmlFile = fxmlFile;
	}
	
	public String getFxmlFile() {
		return _fxmlFile;
	}
}
