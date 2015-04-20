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
	private List<String[]> _employees;
	
	@FXML
	private Button _queueViewAddQEntryButton;
	
	@FXML
	private ComboBox<String> _queueViewEmployeeSelection;
	
	@FXML
	private ListView<String> _queueViewListView;
	
	public void initialize(){
		_queueController = new QueueControllerImpl();
		_queueController.getQueues();
		
		_queueEntryController = new QueueEntryControllerImpl();
		_employees = fetchEmployees(_queueController);
		
		//fill comboBox with employee names
		_queueViewEmployeeSelection = new ComboBox<String>();
		for(String[] employee: _employees){
			String name = employee[1] + " " + employee[2];
			_queueViewEmployeeSelection.getItems().add(name);
		}
		//TODO finish stuff
	}
	
	public void refresh(){
		//TODO implement method
	}
	
	//fetch employees to show in selection box
	public List<String[]> fetchEmployees(QueueControllerImpl queueController){
		List<String[]> employeesStr = new ArrayList<String[]>();
		employeesStr = queueController.getEmployees();
		return employeesStr;
	}

	@Override
	public void accept(Boolean b) {
		// TODO something with consumer from QueueEntryViewController
		
	}
}
