package at.itb13.oculus.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.hibernate.HibernateException;

import at.itb13.oculus.application.QueueControllerImpl;
import at.itb13.oculus.application.QueueEntryControllerImpl;
import at.itb13.oculus.model.Employee;
import at.itb13.oculus.model.Queue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;


/**
 * @author Carola
 *
 */

public class QueueViewController implements Serializable, Consumer<Boolean>{

	private QueueControllerImpl _queueController;
	private QueueEntryControllerImpl _queueEntryController;
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
		_queues = _queueController.getQueues();
		
		//fill comboBox with employee names
		_queueViewEmployeeSelection = new ComboBox<String>();
		for(String[] queue: _queues){
			_queueViewEmployeeSelection.getItems().add(queue[1]);
		}
		
		_queueEntryController = new QueueEntryControllerImpl();
		
		//TODO finish stuff
	}
	
	public void refresh(){
		//TODO implement method
	}
	
	@Override
	public void accept(Boolean b) {
		// TODO something with consumer from QueueEntryViewController
		
	}
}
