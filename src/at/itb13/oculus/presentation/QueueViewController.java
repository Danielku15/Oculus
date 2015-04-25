package at.itb13.oculus.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import at.itb13.oculus.application.ControllerFactory;
import at.itb13.oculus.application.QueueController;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;
import at.itb13.oculus.main.Main;
import at.itb13.oculus.service.TableChangeEvent;
import at.itb13.oculus.service.TableChangeListener;
import at.itb13.oculus.util.DateUtil;

/**
 * @author Carola
 *
 */

public class QueueViewController implements Serializable, Consumer<Boolean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3311907797950844425L;
	private QueueController _queueController;
	public static final String QUEUEENTRYVIEW = "QueueEntryView.fxml";
	private Stage _queueEntryViewStage;
	private List<String[]> _queues;
	private PatientMainViewController _patientMainViewController;
	
	private List<QueueEntryChosenListener> _listeners;
	
	@FXML
	private Button _queueViewAddQEntryButton;
	
	@FXML
	private ComboBox<String> _queueViewEmployeeSelection;
	
	@FXML
	private ListView<QueueEntryObj> _queueViewListView;
	
	public QueueViewController() {
		_listeners = new LinkedList<QueueEntryChosenListener>();
	}
	
	class QueueEntryObj{
        private String _id;
        private String _patientId;
        private String _patientName;
        private String _start;
        private String _appointmentId;
         
        QueueEntryObj(String id, String patientId, String patientName, String start, String appointmentId){
            _id = id;
            _patientId = patientId;
            _patientName = patientName;
            _start = start;
            _appointmentId = appointmentId;
        }
         
        String getId(){
            return _id;
        }
        
        String getPatientId(){
        	return _patientId;
        }
         
        String getPatientName(){
            return _patientName;
        }
        
        String getStart(){
        	return _start;
        }
        
        String getAppointmentId() {
        	return _appointmentId;
        }
    }
	
	public void initialize(){
		_queueController = ControllerFactory.getInstance().getQueueController();
		_queues = _queueController.getQueues();
		
		
		
		Main.getIndexService().addTableChangeListener("QueueEntry", new TableChangeListener() {
			@Override
			public void onTableChange(TableChangeEvent e) {
				if(e.getModified().after(DateUtil.truncateHours(new Date()))) {
					refresh();
				}
			}
		});
		
		//fill comboBox with employee names
		//_queueViewEmployeeSelection = new ComboBox<String>();
		for(String[] queue: _queues){
			_queueViewEmployeeSelection.getItems().add(queue[1]);
		}
		_queueViewEmployeeSelection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				String queueId = _queueController.getQueueIdByName(newValue);
				setListView(_queueController.getQueueEntries(queueId));
				_queueController.fetchQueue(newValue);
				_queueController.activate();
			};
		});
		_queueViewEmployeeSelection.getSelectionModel().selectFirst();
		String[] firstQueue = _queues.get(0);
		setListView(_queueController.getQueueEntries(firstQueue[0]));
		
		//TODO finish stuff
	}
	
	@FXML
	public void loadQueueEntryView(){		
		_queueEntryViewStage = new Stage();
		FXMLLoader loader = null;
		Pane pane = null;
		LangFacade facade = LangFacade.getInstance();
		String queueId = _queueController.getQueueIdByName(_queueViewEmployeeSelection.getSelectionModel().getSelectedItem());
		
		try {
			loader = new FXMLLoader(this.getClass().getResource(QUEUEENTRYVIEW),
					facade.getResourceBundle());
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		QueueEntryViewController queueEntryViewController = loader.getController();
		queueEntryViewController.init(this);
		
		_queueEntryViewStage.setScene(new Scene(pane));
		_queueEntryViewStage.setTitle("Create Queue Entry");
		//TODO name of Stage --> Language german/english
		_queueEntryViewStage.setResizable(false);
		_queueEntryViewStage.show();
	}
	
	private void setListView(List<String[]> queueEntries){

		LangFacade langInstance = LangFacade.getInstance();
	    List<QueueEntryObj> myList = new ArrayList<>();
		
		_queueViewListView.getItems().clear();
		
    	for(String[] queueEntry: queueEntries){
    		myList.add(new QueueEntryObj(queueEntry[0], queueEntry[4], queueEntry[5] + " " + queueEntry[6], queueEntry[8], queueEntry[9]));
    	}
 
        ListView<QueueEntryObj> listView = new ListView<>();
        ObservableList<QueueEntryObj> myObservableList = FXCollections.observableList(myList);
        _queueViewListView.setItems(myObservableList);
         
        _queueViewListView.setCellFactory(new Callback<ListView<QueueEntryObj>, ListCell<QueueEntryObj>>(){
 
            @Override
            public ListCell<QueueEntryObj> call(ListView<QueueEntryObj> p) {
                 
                ListCell<QueueEntryObj> cell = new ListCell<QueueEntryObj>(){
 
                    @Override
                    protected void updateItem(QueueEntryObj t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                        	setText(langInstance.getString(LangKey.PATIENTNAME) + ": " + t.getPatientName()+ "\n" + langInstance.getString(LangKey.APPOINTMENTSTART) + ": " + t.getStart());
                        	setOnMouseClicked(new EventHandler<MouseEvent>() {
            					@Override
            					public void handle(MouseEvent e) {
            						if(e.getClickCount() >= 2) {
            							notifyListeners(t.getId(), t.getPatientId(), t.getAppointmentId());
            						}
            					}
            				});
                        }
                    }
 
                };
                 
                return cell;
            }
        });
	}
	
	public void openTreatment(String id){
		_patientMainViewController.setNewTab(_queueController.getPatientIdByQueueEntryId(id));
	}
	
	public void openPatient(String id){
		_patientMainViewController.setNewTab(_queueController.getPatientIdByQueueEntryId(id));
	}
	
	public void refresh(){
		//TODO implement method
		String queueId = _queueController.getQueueIdByName(_queueViewEmployeeSelection.getSelectionModel().getSelectedItem());
		setListView(_queueController.getQueueEntries(queueId));
	}
	
	@Override
	public void accept(Boolean b) {
		// TODO something with consumer from QueueEntryViewController
		System.out.println("TESf");
		if(b){
			refresh();
		}
		_queueEntryViewStage.close();
	}

	private void notifyListeners(String queueEntryId, String patientId, String appointmentId) {
		QueueEntryChosenEvent e = new QueueEntryChosenEvent(this, queueEntryId, patientId, appointmentId);
		for(QueueEntryChosenListener listener : _listeners) {
			listener.queueEntryChosen(e);
		}
	}

	public void addQueueEntryChosenListener(QueueEntryChosenListener listener) {
		_listeners.add(listener);
	}
	
	public void removeQueueEntryChosenListener(QueueEntryChosenListener listener) {
		_listeners.remove(listener);
	}
	
	//init parent - PatientMainViewController
	public void init(PatientMainViewController patientMainViewController) {
		_patientMainViewController = patientMainViewController;
	}
}
