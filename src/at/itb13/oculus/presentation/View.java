package at.itb13.oculus.presentation;

import javafx.stage.Modality;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;

/**
 * Enumeration of views
 */
public enum View {
	QUEUEENTRYVIEW("QueueEntryView.fxml", LangKey.QUEUEENTRYTITLE, null, null, false, Modality.APPLICATION_MODAL),
	SEARCHVIEW("SearchView.fxml", LangKey.PATIENTSEARCHTITLE, 830, null, true, Modality.APPLICATION_MODAL);
	
	private String _fxmlFile;
	private LangKey _title;
	private Integer _width;
	private Integer _height;
	private boolean _resizeable;
	private Modality _modality;
	
	private View(String fxmlFile, LangKey title, Integer width, Integer height, boolean resizeable, Modality modality) {
		_fxmlFile = fxmlFile;
		_title = title;
		_width = width;
		_height = height;
		_resizeable = resizeable;
		_modality = modality;
	}
	
	public String getFxmlFile() {
		return _fxmlFile;
	}
	
	public String getTitle() {
		if(_title != null) {
			return LangFacade.getInstance().getString(_title);
		}
		return null;
	}
	
	public Integer getWidth() {
		return _width;
	}
	
	public Integer getHeight() {
		return _height;
	}
	
	public boolean isResizeable() {
		return _resizeable;
	}
	
	public Modality getModality() {
		return _modality;
	}
}
