package at.itb13.oculus.presentation;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import at.itb13.oculus.application.ControllerFactory;
import at.itb13.oculus.application.IncompleteDataException;
import at.itb13.oculus.application.ObjectNotSavedException;
import at.itb13.oculus.application.QueueController;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;
import at.itb13.oculus.main.Main;
import at.itb13.oculus.service.TableChangeEvent;
import at.itb13.oculus.service.TableChangeListener;
import at.itb13.oculus.util.DateUtil;
import at.itb13.oculus.util.GUIUtil;

/**
 * 
 * The QueueViewController controls the view of one queue, but can be changed by a selection
 * @category ViewController
 *
 */
public class QueueViewController implements Serializable {
	private static final long serialVersionUID = -3311907797950844425L;
	
	private QueueController _queueController;
	private List<QueueEntryChosenListener> _listeners;
	
	private Stage _queueEntryViewStage;
	
	@FXML
	private Button _addQueueEntryBtn;
	@FXML
	private ComboBox<String> _queueCbx;
	@FXML
	private ListView<QueueEntryObj> _queueEntryListView;
	
	public QueueViewController() {
		_queueController = ControllerFactory.getInstance().getQueueController();
		_listeners = new LinkedList<QueueEntryChosenListener>();
	}
	
	public void initialize(){		
		Main.getIndexService().addTableChangeListener("QueueEntry", new TableChangeListener() {
			@Override
			public void onTableChange(TableChangeEvent e) {
				if(e.getModified().after(DateUtil.truncateHours(new Date()))) {
					// TODO: refresh queue entry list when another client inserted or updated a queue entry
				}
			}
		});

		// add listener to refresh queue entry list when another queue is selected
		_queueCbx.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldQueueName, String newQueueName) {
				refreshQueueEntryList(newQueueName);
			};
		});
		
        // set cell factory and add mouse click handler for queue entry list view
        _queueEntryListView.setCellFactory(new Callback<ListView<QueueEntryObj>, ListCell<QueueEntryObj>>(){
            @Override
            public ListCell<QueueEntryObj> call(ListView<QueueEntryObj> p) {
                ListCell<QueueEntryObj> cell = new ListCell<QueueEntryObj>() {
                    @Override
                    protected void updateItem(QueueEntryObj queueEntry, boolean bln) {
                        super.updateItem(queueEntry, bln);
                        LangFacade langFacade = LangFacade.getInstance();
                        if (queueEntry != null) {
                        	setText(langFacade.getString(LangKey.PATIENTNAME) + ": " + queueEntry.getPatientName()+ "\n" + langFacade.getString(LangKey.APPOINTMENTSTART) + ": " + queueEntry.getStart());
                        	setOnMouseClicked(new EventHandler<MouseEvent>() {
            					@Override
            					public void handle(MouseEvent e) {
            						if(e.getClickCount() >= 2) {
            							notifyListeners(queueEntry.getId(), queueEntry.getPatientId(), queueEntry.getAppointmentId());
            						}
            					}
            				});
                        }
                    }
                };
                return cell;
            }
        });
		
		// get queues and fill combobox
		getQueues();
	}
	
	private void fillQueueCombobox(List<String[]> queues) {
		for(String[] queue : queues) {
			_queueCbx.getItems().add(queue[1]);
		}
		_queueCbx.getSelectionModel().selectFirst();
	}
	
	@FXML
	public void showQueueEntryView(ActionEvent e) {
		_queueEntryViewStage = new Stage();
		_queueController.activate();
		QueueEntryViewController queueEntryViewController = GUIUtil.showView(View.QUEUEENTRYVIEW, _queueEntryViewStage);
		queueEntryViewController.init(this);
	}
	
	public void closeQueueViewEntry() {
		if(_queueEntryViewStage != null) {
			// close queue entry view
			_queueEntryViewStage.close();
		}
		// refresh queue entry list
		refreshQueueEntryList(_queueCbx.getSelectionModel().getSelectedItem());
	}
	
	private void fillListView(List<String[]> queueEntries){
	    ObservableList<QueueEntryObj> queueEntryList = FXCollections.observableArrayList();	
    	for(String[] queueEntry: queueEntries){
    		queueEntryList.add(new QueueEntryObj(queueEntry[0], queueEntry[4], queueEntry[5] + " " + queueEntry[6], queueEntry[8], queueEntry[9]));
    	}
		_queueEntryListView.getItems().clear();
        _queueEntryListView.setItems(queueEntryList);
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
	
	private void getQueues() {
		new Thread(new GetQueuesTask()).start();
	}
	
	private void refreshQueueEntryList(String queueName) {
		new Thread(new RefreshQueueEntryListTask(queueName)).start();
	}
	
	private class GetQueuesTask extends Task<Void> {

		private List<String[]> _queues;
		
		@Override public Void call() throws IncompleteDataException, ObjectNotSavedException {
			_queues = _queueController.getQueues();
			return null;
	    }
	    
	    @Override protected void succeeded() {
	    	fillQueueCombobox(_queues);
	    }
	}
	
	private class RefreshQueueEntryListTask extends Task<Void> {

		private String _queueName;
		private List<String[]> _queueEntries;
		
		public RefreshQueueEntryListTask(String queueName) {
			_queueName = queueName;
		}
		
		@Override public Void call() {
			_queueController.fetchQueue(_queueName);
			_queueEntries = _queueController.getQueueEntries(_queueController.getQueueIdByName(_queueName));
			return null;
	    }
	    
	    @Override protected void succeeded() {
	    	fillListView(_queueEntries);
	    }
	}
	
	/**
	 * this inner class contains important informations about queue entries
	 * this objects are getting listed at the queue view
	 */
	private class QueueEntryObj {
		
        private String _id;
        private String _patientId;
        private String _patientName;
        private String _start;
        private String _appointmentId;
         
        public QueueEntryObj(String id, String patientId, String patientName, String start, String appointmentId) {
            _id = id;
            _patientId = patientId;
            _patientName = patientName;
            _start = start;
            _appointmentId = appointmentId;
        }
         
        public String getId() {
            return _id;
        }
        
        public String getPatientId() {
        	return _patientId;
        }
         
        public String getPatientName() {
            return _patientName;
        }
        
        public String getStart() {
        	return _start;
        }
        
        public String getAppointmentId() {
        	return _appointmentId;
        }
    }
}
