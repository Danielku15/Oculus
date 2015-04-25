package at.itb13.oculus.presentation;

import java.util.LinkedList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import at.itb13.oculus.util.GUIUtil;

/**
 * This controller is not responsible for the search view
 * It is responsible for the field, for quick access to the search view
 *
 */
public class SearchPanelController {
	
	private static final int MINCRITERIALENGTH = 3;
	
	private List<CriteriaChangeListener> _listeners;
	
	public SearchPanelController() {
		_listeners = new LinkedList<CriteriaChangeListener>();
	}
	
	@FXML
	private Label _searchInputLabel;
	@FXML
	private TextField _searchInput;
	@FXML
	private Button _searchButton;

	@FXML
	public void initialize() {
		_searchInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					GUIUtil.validate(_searchInputLabel, isCriteriaValid(_searchInput.getText()));
				}
			}
		});
		
		_searchInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		    	changeCriteria(newValue);
		    }
		});
	}
	
	private boolean isCriteriaValid(String criteria) {
		return ((criteria != null) && ((criteria.trim().isEmpty()) || (criteria.trim().length() >= MINCRITERIALENGTH)));
	}
	
	private void changeCriteria(String criteria) {
		CriteriaChangeEvent e = new CriteriaChangeEvent(this, criteria);
		for(CriteriaChangeListener listener : _listeners) {
			listener.onCriteriaChange(e);
		}
	}
	
	public boolean setCriteria(String criteria) {
		_searchInput.setText(criteria);
		GUIUtil.validate(_searchInputLabel, isCriteriaValid(criteria));
		changeCriteria(criteria);
		return isCriteriaValid(criteria);
	}
	
	public String getCriteria() {
		return _searchInput.getText();
	}
	
	public void addCriteriaChangeListener(CriteriaChangeListener listener) {
		_listeners.add(listener);
	}
	
	public void removeCritieraChangeListener(CriteriaChangeListener listener) {
		_listeners.remove(listener);
	}
	
	public void setOnSearchAction(EventHandler<ActionEvent> e) {
		_searchButton.setOnAction(e);
		_searchInput.setOnAction(e);
	}
}
