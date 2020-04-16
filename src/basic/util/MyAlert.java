package basic.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class MyAlert {
	
	public static void info(String msg) {
		Alert infor = new Alert(AlertType.INFORMATION);
		infor.setTitle("정보");
		infor.setHeaderText(msg);
		
		infor.showAndWait();
	}
    
    public static void alert(String msg) {
		Alert warning = new Alert(AlertType.WARNING);
		warning.setTitle("경고");
		warning.setHeaderText(msg);
		
		warning.showAndWait();
	}
	
	public static ButtonType confirm(String msg) {
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("확인");
		confirm.setHeaderText(msg);
		
		return confirm.showAndWait().get();
	}
}


