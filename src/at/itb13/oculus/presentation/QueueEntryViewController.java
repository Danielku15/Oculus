/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 16.04.2015
 */
package at.itb13.oculus.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import at.itb13.oculus.application.ControllerFactory;
import at.itb13.oculus.application.DataMismatchException;
import at.itb13.oculus.application.IncompleteDataException;
import at.itb13.oculus.application.ObjectNotFoundException;
import at.itb13.oculus.application.ObjectNotSavedException;
import at.itb13.oculus.application.QueueEntryController;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.model.Patient;

/**
 * @author Manu
 *
 */
public class QueueEntryViewController implements Serializable, Initializable, Consumer<String> {

	private static final long serialVersionUID = 1L;
	public static final String SEARCHVIEW = "SearchView.fxml";
	private QueueEntryController _queueEntryController;
	private Stage _searchViewStage;
	private List<String[]> _queuesList;
	private List<String[]> _appointmentList;
	private String _queueID;
	private String _patientID;
	private List<Consumer<Boolean>> _consumers;


	@FXML
	private TextField _patientTbx;
	
	@FXML
	private Button _searchBtn;

	@FXML
	private ComboBox<String> _appointmentCbx;

	@FXML
	private Button _searchAppointmentBtn;

	@FXML
	private TextField _titleTbx;

	@FXML
	private TextArea _descriptionTax;
	
	@FXML
	private TextField _employeeTbx;
	
	@FXML
	private ComboBox<String> _queueCbx;
	
	@FXML
	private Button _cancelBtn;

	@FXML
	private Button _saveBtn;

	
	public QueueEntryViewController(){	
		
		_queueID = null;
		_patientID = null;		
	}

	public QueueEntryViewController(String queueID) {
		
		_queueID = queueID;
		_patientID = null;		
	}
	
	public QueueEntryViewController(String queueID, String patientID) {
		
		_queueID = queueID;
		_patientID = patientID;			
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if(_patientID != null){
			
			try {
				_queueEntryController.fetchPatient(_patientID);
				
			} catch (ObjectNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_patientTbx.setText(_queueEntryController.getPatientFirstname() + " " + _queueEntryController.getPatientLastname());
		}
		

		
		_consumers = new LinkedList<Consumer<Boolean>>();
		_queueEntryController = ControllerFactory.getInstance().getQueueEntryController();
		_queuesList = _queueEntryController.getQueues();

		for (String[] array : _queuesList) {
			String nameQueue = array[1];
			_queueCbx.getItems().add(nameQueue);
		}
		
		_queueCbx.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				addQueueEntry();
			};
		});
		
		if(_queueID != null){
			try {
				
				_queueEntryController.fetchQueue(_queueID);
				_queueCbx.getSelectionModel().select(_queueEntryController.getQueueName());
			
			} catch (ObjectNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			_queueCbx.getSelectionModel().selectFirst();
		}
		
	}
	
	public void addConsumer(Consumer<Boolean> consumer) {
		_consumers.add(consumer);
	}
	
	@FXML
	public void addPatient(ActionEvent event) {
		
		openSearchView(event);

	}
	
	
	public void addAppointment(){

		for (String[] array : _appointmentList) {
			String nameAppointment = array[3];
			_appointmentCbx.getItems().add(nameAppointment);
		}
		_appointmentCbx.getSelectionModel().selectFirst();
	}
	
	@FXML
	public void fillAppoinmentData(ActionEvent event) {
		
		int index = _appointmentCbx.getSelectionModel().getSelectedIndex();
		String[] appointment = _appointmentList.get(index);
		_titleTbx.setText(appointment[1]);
		_descriptionTax.setText(appointment[2]);
		_employeeTbx.setText(appointment[4] + " " + appointment[5]);	
		try {
			_queueEntryController.fetchAppointment(appointment[0]);
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void addQueueEntry() {
		
		int index = _queueCbx.getSelectionModel().getSelectedIndex();
		_queueID = _queuesList.get(index)[0];
		try {
			_queueEntryController.fetchQueue(_queueID);
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void save(ActionEvent event){
		
		try {
			_queueEntryController.saveQueueEntry();
			
			for(Consumer<Boolean> c : _consumers) {
				c.accept(true);
			}
		} catch (IncompleteDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ObjectNotSavedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cancel(ActionEvent event){
		
		for(Consumer<Boolean> c : _consumers){
			c.accept(false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Consumer#accept(java.lang.Object)
	 */

	@Override
	public void accept(String t) {
		try {
			
			_queueEntryController.fetchPatient(t);
			_patientTbx.setText(_queueEntryController.getPatientFirstname() + " " + _queueEntryController.getPatientLastname());
			_searchViewStage.close();
			_appointmentList = _queueEntryController.getAppointmentsByPatientId(t);
			addAppointment();

		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getQueueID() {
		return _queueID;
	}
	
	
	private void openSearchView(ActionEvent event){		
		String query = _patientTbx.getText();
		_searchViewStage = new Stage();
		FXMLLoader loader = null;
		Pane pane = null;
		LangFacade facade = LangFacade.getInstance();

		loader = new FXMLLoader(this.getClass().getResource(SEARCHVIEW),
				facade.getResourceBundle());
		SearchViewController<Patient> patientSearchViewController = new SearchViewController<Patient>(Patient.class);
		loader.setController(patientSearchViewController);
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		patientSearchViewController.addConsumer(this);

		if (patientSearchViewController.setCriteria(query)) {
			patientSearchViewController.search(event);
			_searchViewStage.setScene(new Scene(pane));
			_searchViewStage.show();
		}
	}


}
