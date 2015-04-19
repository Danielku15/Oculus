package at.itb13.oculus.util;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class GUIUtil {
	
	private static final Color COLOR_FAIL = Color.RED;
	private static final Color COLOR_SUCCESS = Color.BLACK;
	
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
}
