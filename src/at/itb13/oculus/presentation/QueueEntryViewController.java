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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
public class QueueEntryViewController implements Serializable, Initializable,
		Consumer<String> {

	private static final long serialVersionUID = 1L;
	public static final String SEARCHVIEW = "SearchView.fxml";
	private QueueEntryController _queueEntryController;
	private Stage _searchViewStage;
	private List<String[]> _queuesList;
	private List<String[]> _appointmentList;
	private String _queueID;
	private String _patientID;

	private QueueViewController _queueViewController;

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

	public QueueEntryViewController() {
		_queueEntryController = ControllerFactory.getInstance()
				.getQueueEntryController();
		_queueID = _queueEntryController.getQueueId();
		_patientID = _queueEntryController.getPatientId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			_queueEntryController.fetchPatient(_patientID);
			_appointmentList = _queueEntryController
					.getAppointmentsByPatientId(_patientID);
			addAppointment();
			fillAppointmentDataForInitializeMethod();
			_patientTbx.setText(_queueEntryController.getPatientFirstname()
					+ " " + _queueEntryController.getPatientLastname());
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		_queuesList = _queueEntryController.getQueues();

		for (String[] array : _queuesList) {
			String nameQueue = array[1];
			_queueCbx.getItems().add(nameQueue);
		}

		_queueCbx.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {

					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						addQueueEntry();
					};
				});

		if (_queueID != null) {
			try {

				_queueEntryController.fetchQueue(_queueID);
				_queueCbx.getSelectionModel().select(
						_queueEntryController.getQueueName());

			} catch (ObjectNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			_queueCbx.getSelectionModel().selectFirst();
		}

	}

	@FXML
	public void addPatient(ActionEvent event) {

		openSearchView(event);

	}

	public void addAppointment() {

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
		// fill Fields
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

	// Method without ActionEvent is for the initialize method
	public void fillAppointmentDataForInitializeMethod() {

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
	public void save(ActionEvent event) {

		try {
			_queueEntryController.saveQueueEntry();
			_queueViewController.accept(true);

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

	@FXML
	public void cancel(ActionEvent event) {
		_queueViewController.accept(false);
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
			_patientTbx.setText(_queueEntryController.getPatientFirstname()
					+ " " + _queueEntryController.getPatientLastname());
			_searchViewStage.close();
			_appointmentList = _queueEntryController
					.getAppointmentsByPatientId(t);
			// clear Fields
			_appointmentCbx.getItems().clear();
			_titleTbx.clear();
			_descriptionTax.clear();
			_employeeTbx.clear();
			addAppointment();
			// fillAppointmentDataForInitializeMethod();

		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getQueueID() {
		return _queueID;
	}

	private void openSearchView(ActionEvent event) {
		String query = _patientTbx.getText();
		_searchViewStage = new Stage();
		FXMLLoader loader = null;
		Pane pane = null;
		LangFacade facade = LangFacade.getInstance();

		loader = new FXMLLoader(this.getClass().getResource(SEARCHVIEW),
				facade.getResourceBundle());
		SearchViewController<Patient> patientSearchViewController = new SearchViewController<Patient>(
				Patient.class);
		loader.setController(patientSearchViewController);
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		patientSearchViewController.addConsumer(this);

		if (patientSearchViewController.setCriteria(query)) {
			patientSearchViewController.search();
			_searchViewStage.setScene(new Scene(pane));
			_searchViewStage.show();
		}
	}

	public void init(QueueViewController queueViewController) {
		_queueViewController = queueViewController;
	}

}
