package at.itb13.oculus.presentation;

import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import at.itb13.oculus.application.ControllerFactory;
import at.itb13.oculus.application.DataMismatchException;
import at.itb13.oculus.application.IncompleteDataException;
import at.itb13.oculus.application.MainController;
import at.itb13.oculus.application.ObjectNotFoundException;
import at.itb13.oculus.application.ObjectNotSavedException;
import at.itb13.oculus.application.QueueEntryController;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;
import at.itb13.oculus.model.Patient;
import at.itb13.oculus.util.GUIUtil;
/**
 * @author Manu
 *
 */
public class QueueEntryViewController implements Serializable, Initializable {
	private static final long serialVersionUID = 3093525881568421603L;
	
	// parent controller
	private QueueViewController _queueViewController;
	private List<String[]> _queues;
	
	private QueueEntryController _queueEntryController;
	
	private Stage _searchViewStage;
	
	@FXML
	private TextField _patientTbx;
	@FXML
	private Button _searchBtn;
	@FXML
	private ComboBox<AppointmentObj> _appointmentCbx;
	@FXML
	private TextField _titleTbx;
	@FXML
	private TextArea _descriptionTax;
	@FXML
	private TextField _employeeTbx;
	@FXML
	private ComboBox<QueueObj> _queueCbx;
	@FXML
	private Button _cancelBtn;
	@FXML
	private Button _saveBtn;

	public QueueEntryViewController() {
		_queueEntryController = ControllerFactory.getInstance().getQueueEntryController();
	}

	@Override
	public void initialize(URL url, ResourceBundle resBundle) {		
		// add listener to fetch appointment when another appointment is selected
		_appointmentCbx.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AppointmentObj>() {
			@Override
			public void changed(ObservableValue<? extends AppointmentObj> observable, AppointmentObj oldAppointment, AppointmentObj newAppointment) {
				if(newAppointment != null) {
					fetchAppointment(newAppointment.getId());
				}
			};
		});
		
		// add listener to fetch queue when another queue is selected
		_queueCbx.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<QueueObj>() {
			@Override
			public void changed(ObservableValue<? extends QueueObj> observable, QueueObj oldQueue, QueueObj newQueue) {
				if(newQueue != null) {
					fetchQueue(newQueue.getQueueId());
				}
			};
		});
		
		// get queues and fill combobox
		getQueues();
		
		MainController mainController = ControllerFactory.getInstance().getMainController();
		String patientId = mainController.getPatientController().getID();
		String queueId = mainController.getQueueController().getQueueId();		
		if(patientId != null) {
			fetchPatient(patientId);
		}
		if(queueId != null) {
			fetchQueue(queueId);
		}
	}

	@FXML
	public void searchPatient(ActionEvent event) {
		openPatientSearchView();
	}
	
	@FXML
	public void save(ActionEvent e) {
		saveQueueEntry();
	}
	
	@FXML
	public void cancel(ActionEvent e) {
		_queueViewController.closeQueueViewEntry();
	}
	
	private void getQueues() {
		new Thread(new GetQueuesTask()).start();
	}
	
	private void fetchPatient(String patientId) {
		new Thread(new FetchPatientTask(patientId)).start();
	}
	
	private void fetchAppointment(String appointmentId) {
		new Thread(new FetchAppointmentTask(appointmentId)).start();
	}
	
	private void fetchQueue(String queueId) {
		new Thread(new FetchQueueTask(queueId)).start();
	}
	
	private void saveQueueEntry() {
		new Thread(new SaveQueueEntryTask()).start();
	}

	private void loadPatientData(List<String[]> appointments) {
		if(_searchViewStage != null) {
			_searchViewStage.close();
		}
		clearForm();
		fillPatientInput();
		fillAppointmentCombobox(appointments);
	}
	
	private void clearForm() {
		_patientTbx.clear();
		ObservableList<AppointmentObj> appointments = _appointmentCbx.getItems();
		if(appointments != null) {
			appointments.clear();
		}
		_titleTbx.clear();
		_descriptionTax.clear();
		_employeeTbx.clear();
	}
	
	private void fillPatientInput() {
		_patientTbx.setText(_queueEntryController.getPatientFirstname()	+ " " + _queueEntryController.getPatientLastname());
	}
	
	private void displayAppointmentData() {
		AppointmentObj appointment = _appointmentCbx.getSelectionModel().getSelectedItem();
		_titleTbx.setText(appointment.getTitle());
		_descriptionTax.setText(appointment.getDescription());
		_employeeTbx.setText(appointment.getEmployeeFirstname() + " " + appointment.getEmployeeLastname());
	}
	
	private void fillAppointmentCombobox(List<String[]> appointments) {
		if(appointments != null) {
			for (String[] appointment : appointments) {
				_appointmentCbx.getItems().add(new AppointmentObj(appointment));
			}
			_appointmentCbx.getSelectionModel().selectFirst();
		}
	}
	
	private void fillQueueCombobox(List<String[]> queues) {
		if(queues != null) {
			for (String[] queue : queues) {
				_queueCbx.getItems().add(new QueueObj(queue));
			}
			_queueCbx.getSelectionModel().selectFirst();
		}
	}

	private void openPatientSearchView() {
		if(_searchViewStage == null) {
			_searchViewStage = new Stage();
		}
		
		SearchViewController<Patient> patientSearchViewController = new SearchViewController<Patient>(Patient.class);
		patientSearchViewController.addConsumer(new Consumer<String>() {
			@Override
			public void accept(String id) {
				fetchPatient(id);
			}
		});
		
		GUIUtil.showView(patientSearchViewController, View.SEARCHVIEW, _searchViewStage, LangFacade.getInstance().getString(LangKey.PATIENTSEARCHTITLE));
		patientSearchViewController.setCriteria(_patientTbx.getText());
		patientSearchViewController.search();
	}

	public void init(QueueViewController queueViewController) {
		_queueViewController = queueViewController;
	}
	
	/**
	 * Task that fetches the patient identified by the passed id
	 * @author Patrick
	 */
	private class FetchPatientTask extends Task<Void> {

		private String _patientId;
		private List<String[]> _appointments;
		
		public FetchPatientTask(String patientId) {
			_patientId = patientId;
		}
		
		@Override public Void call() throws ObjectNotFoundException {
			_queueEntryController.fetchPatient(_patientId);
		    _appointments = _queueEntryController.getAppointmentsByPatientId(_patientId);
			return null;
	    }
	    
	    @Override protected void succeeded() {
	    	loadPatientData(_appointments);
	    }
	}
	
	/**
	 * Task that fetches the appointment identified by the passed id
	 * @author Patrick
	 */
	private class FetchAppointmentTask extends Task<Void> {

		private String _appointmentId;
		
		public FetchAppointmentTask(String appointmentId) {
			_appointmentId = appointmentId;
		}
		
		@Override public Void call() throws ObjectNotFoundException {
			_queueEntryController.fetchAppointment(_appointmentId);
			return null;
	    }
	    
	    @Override protected void succeeded() {
	    	displayAppointmentData();
	    }
	}
	
	/**
	 * Task that fetches the queue identified by the passed id
	 * @author Patrick
	 */
	private class FetchQueueTask extends Task<Void> {

		private String _queueId;
		
		public FetchQueueTask(String queueId) {
			_queueId = queueId;
		}
		
		@Override
		public Void call() throws ObjectNotFoundException {
			_queueEntryController.fetchQueue(_queueId);
			return null;
	    }
		
		@Override
		protected void succeeded() {
			for(QueueObj queue : _queueCbx.getItems()) {
				if(queue.getQueueId().equals(_queueId)) {
					_queueCbx.getSelectionModel().select(queue);
				}
			}
		}
	}
	
	/**
	 * Task that saves or updates the current queue entry
	 * @author Patrick
	 */
	private class SaveQueueEntryTask extends Task<Void> {
		
		@Override
		public Void call() throws IncompleteDataException, DataMismatchException, ObjectNotSavedException {
			_queueEntryController.saveQueueEntry();
			return null;
	    }
		
		@Override
		protected void succeeded() {
			_queueViewController.closeQueueViewEntry();
		}
	}
	
	private class GetQueuesTask extends Task<Void> {
		
		@Override
		public Void call() {
			_queues = _queueEntryController.getQueues();
			return null;
		}
		
	    @Override
	    protected void succeeded() {
	    	fillQueueCombobox(_queues);
	    }
	}
	
	/**
	 * Class that represents an entry in the appointment combobox
	 * @author Patrick
	 */
	private class AppointmentObj {
		
		private String _id;
		private String _title;
		private String _description;
		private String _start;
		private String _employeeFirstname;
		private String _employeeLastname;
		
		public AppointmentObj(String[] arr) {
			_id = arr[0];
			_title = arr[1];
			_description = arr[2];
			_start = arr[3];
			_employeeFirstname = arr[4];
			_employeeLastname = arr[5];
		}
		
		public String getId() {
			return _id;
		}
		
		public String getTitle() {
			return _title;
		}
		
		public String getDescription() {
			return _description;
		}
		
		public String getStart() {
			return _start;
		}
		
		public String getEmployeeFirstname() {
			return _employeeFirstname;
		}
		
		public String getEmployeeLastname() {
			return _employeeLastname;
		}
		
		@Override
		public String toString() {
			return getStart() + " " + getTitle();
		}
	}
	
	/**
	 * Class that represents an entry in the queue combobox
	 * @author Patrick
	 */
	private class QueueObj {
		
		private String _queueId;
		private String _queueName;
		
		public QueueObj(String[] arr) {
			_queueId = arr[0];
			_queueName = arr[1];
		}
		
		public String getQueueId() {
			return _queueId;
		}
		
		public String getQueueName() {
			return _queueName;
		}
		
		@Override
		public String toString() {
			return getQueueName();
		}
	}
}
