package at.itb13.oculus.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import at.itb13.oculus.lang.LangFacade;

/**
 * 
 * MainViewController saves all the main views and its status
 * @category ViewController
 *
 */
public class MainViewController implements Initializable  {

	private static Map<MainViewContent, Node> _contentMap;
	
	@FXML
	private AnchorPane _content;
	
	static {
		_contentMap = new HashMap<MainViewContent, Node>();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resBundle) {
		initContentMap();
	}
	
	public void setContent(MainViewContent content) {
		ObservableList<Node> nodes = _content.getChildren();
		if(nodes.isEmpty()) {
			nodes.add(_contentMap.get(content));
		} else {
			nodes.set(0, _contentMap.get(content));
		}
	}
	
	private void initContentMap() {
		for(MainViewContent mainView : MainViewContent.values()) {
	    	try {
	    		_contentMap.put(mainView, FXMLLoader.load(getClass().getResource(mainView.getFxmlFile()), LangFacade.getInstance().getResourceBundle()));		    	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
