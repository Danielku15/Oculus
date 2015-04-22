package at.itb13.oculus.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
	
	@FXML
	private Button _queueViewAddQEntryButton;
	
	@FXML
	private ComboBox<String> _queueViewEmployeeSelection;
	
	@FXML
	private ListView<String> _queueViewListView;
	
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
				String queueId = _queueController.getIdOfQueue(newValue);
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
	
	public void setListView(List<String[]> queueEntries){

		LangFacade langInstance = LangFacade.getInstance();
		
		_queueViewListView.getItems().clear();

		for(String[] queueEntry: queueEntries){
			_queueViewListView.getItems().add(langInstance.getString(LangKey.PATIENTNAME) + ": " + queueEntry[5] + " " + queueEntry[6] + "\n" + langInstance.getString(LangKey.APPOINTMENTSTART) + ": " + queueEntry[8]);
		}
		
	}
	
	@FXML
	public void loadQueueEntryView(){		
		_queueEntryViewStage = new Stage();
		FXMLLoader loader = null;
		Pane pane = null;
		LangFacade facade = LangFacade.getInstance();
		QueueEntryViewController _queueEntryViewController;
		String queueId = _queueController.getIdOfQueue(_queueViewEmployeeSelection.getSelectionModel().getSelectedItem());
		if(_patientMainViewController.getCurrentPatient() != null){
			_queueEntryViewController = new QueueEntryViewController(queueId, _patientMainViewController.getCurrentPatient());
		}else{
			_queueEntryViewController = new QueueEntryViewController(queueId);
		}
		
		try {
			loader = new FXMLLoader(this.getClass().getResource(QUEUEENTRYVIEW),
					facade.getResourceBundle());
			loader.setController(_queueEntryViewController);
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		_queueEntryViewStage.setScene(new Scene(pane));
		_queueEntryViewStage.setTitle("Create Queue Entry");
		//TODO name of Stage --> Language german/english
		_queueEntryViewStage.setResizable(false);
		_queueEntryViewStage.show();
	}
	
	public void refresh(){
		//TODO implement method
		String queueId = _queueController.getIdOfQueue(_queueViewEmployeeSelection.getSelectionModel().getSelectedItem());
		setListView(_queueController.getQueueEntries(queueId));
	}
	
	@Override
	public void accept(Boolean b) {
		// TODO something with consumer from QueueEntryViewController
		if(b){
			refresh();
		}
		_queueEntryViewStage.close();
	}
	
	//init parent - PatientMainViewController
	public void init(PatientMainViewController patientMainViewController) {
		_patientMainViewController = patientMainViewController;
	}
}
