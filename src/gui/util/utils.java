package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class utils {
	
	public static Stage currentStage(ActionEvent evento) {
		return (Stage)((Node) evento.getSource()).getScene().getWindow();
	}
	
	public static Integer tryParseToInt(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException ex) {
			return null;
		}
	}
}
