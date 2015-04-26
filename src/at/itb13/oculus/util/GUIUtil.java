package at.itb13.oculus.util;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.presentation.View;

public final class GUIUtil {
	
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private static final Color COLOR_FAIL = Color.RED;
	private static final Color COLOR_SUCCESS = Color.web("0x333333ff");
	
	public static void validate(Label label, boolean valid) {
		validate(label, valid, COLOR_SUCCESS, COLOR_FAIL);
	}
	
	public static void validate(Label label, boolean valid, Color success, Color fail) {
		if(valid) {
			label.setTextFill(success);
		} else {
			label.setTextFill(fail);
		}
	}
	
	public static <T> T showView(View view, Stage stage) {
		if((view != null) && (stage != null)) {
			try {
				FXMLLoader loader = new FXMLLoader(View.class.getResource(view.getFxmlFile()), LangFacade.getInstance().getResourceBundle());
				Parent root = loader.load();
				prepareStage(view, stage);
				stage.setScene(new Scene(root));
				stage.show();
				return loader.getController();
			} catch (IOException e) {
				LOGGER.severe(e.getMessage());
			}
		}
		return null;
	}
	
	public static <T> void showView(T controller, View view, Stage stage) {
		if((view != null) && (stage != null)) {
			try {
				FXMLLoader loader = new FXMLLoader(View.class.getResource(view.getFxmlFile()), LangFacade.getInstance().getResourceBundle());
				loader.setController(controller);
				Parent root = loader.load();
				prepareStage(view, stage);
				stage.setScene(new Scene(root));
				stage.show();
			} catch (IOException e) {
				LOGGER.severe(e.getMessage());
			}
		}
	}
	
	private static void prepareStage(View view, Stage stage) {
		String title = view.getTitle();
		if(title != null) {
			stage.setTitle(title);	
		}
		Integer width = view.getWidth();
		if(width != null) {
			stage.setWidth(width);
		}
		Integer height = view.getHeight();
		if(height != null) {
			stage.setHeight(height);
		}
		stage.setResizable(view.isResizeable());
		Modality modality = view.getModality();
		if(modality != null) {
			stage.initModality(modality);
		}
	}
}
