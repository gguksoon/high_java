package basic.event.gugudan;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FxmlEventController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tf;

    @FXML
    private Button btn;

    @FXML
    private TextArea ta;

    @FXML
    void printGugu(ActionEvent event) {
    	String dan = tf.getText();
    	
    	String pattern = "(^[0-9]*$)";

    	boolean check = Pattern.matches(pattern, dan);

    	if(!dan.equals("")) {
	    	if(check) {
		    	ta.setText("[" + dan + "단]\n");
		    	for(int i = 1; i <= 9; i++) {
		    		ta.appendText(dan + " x " + i + " = " + (Integer.parseInt(dan) * i) + "\n");
		    	}
	    	} else {
	    		ta.setText("[에러] 숫자를 입력하세요.");
	    	}
    	}
    	
    	tf.clear();
    	
    	tf.requestFocus(); // 해당 객체에 포커스를 준다.
    }

    @FXML
    void initialize() {
        assert tf != null : "fx:id=\"tf\" was not injected: check your FXML file 'FxmlEvent.fxml'.";
        assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'FxmlEvent.fxml'.";
        assert ta != null : "fx:id=\"ta\" was not injected: check your FXML file 'FxmlEvent.fxml'.";

    }
}
