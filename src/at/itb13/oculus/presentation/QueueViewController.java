package at.itb13.oculus.presentation;

import java.io.Serializable;

import at.itb13.oculus.application.QueueControllerImpl;
import at.itb13.oculus.application.QueueEntryControllerImpl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

public class QueueViewController implements Serializable{

	private QueueControllerImpl _queueController;
	private QueueEntryControllerImpl _queueEntryController;
	
	@FXML
	private Button _queueViewAddQEntryButton;
	
	@FXML
	private ComboBox _queueViewEmployeeSelection;
	
	@FXML
	private ListView _queueViewListView;
	
	public void initialize(){
		//TODO implement method
	}
	
	public void refresh(){
		//TODO implement method
	}
}
