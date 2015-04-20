package at.itb13.oculus.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

<<<<<<< HEAD
import org.hibernate.HibernateException;

import at.itb13.oculus.application.QueueControllerImpl;
import at.itb13.oculus.application.QueueEntryControllerImpl;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;
import at.itb13.oculus.model.Employee;
import at.itb13.oculus.model.Patient;
import at.itb13.oculus.model.Queue;
=======
>>>>>>> origin/master
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
<<<<<<< HEAD
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
=======
import at.itb13.oculus.application.QueueControllerImpl;
import at.itb13.oculus.application.QueueEntryControllerImpl;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;
>>>>>>> origin/master


/**
 * @author Carola
 *
 */

public class QueueViewController implements Serializable, Consumer<Boolean>{

	private QueueControllerImpl _queueController;
	private QueueEntryControllerImpl _queueEntryController;
	public static final String QUEUEENTRYVIEW = "QueueEntryView.fxml";
	private Stage _queueEntryViewStage;
	private List<String[]> _queues;
	private List<String[]> _employees;
	
	@FXML
	private Button _queueViewAddQEntryButton;
	
	@FXML
	private ComboBox<String> _queueViewEmployeeSelection;
	
	@FXML
	private ListView<String> _queueViewListView;
	
	public void initialize(){
		_queueController = new QueueControllerImpl();
		_queueEntryController = new QueueEntryControllerImpl();
		_queues = _queueController.getQueues();
		
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

		loader = new FXMLLoader(this.getClass().getResource(QUEUEENTRYVIEW),
				facade.getResourceBundle());
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_queueEntryViewStage.setScene(new Scene(pane));
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
}
