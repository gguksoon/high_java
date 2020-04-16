package basic.control.gugudan;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class ComboBoxGugudanController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private Button btn;

    @FXML
    private TextArea ta;
    
    @FXML
    void printGugudan(ActionEvent event) {
    	ta.clear();
    	int dan = Integer.parseInt(combo.getValue().substring(0, 1));
    	for(int i = 1; i <= 9; i++) {
    		ta.appendText(dan + " * " + i + " = " + (dan * i) + "\n");
    	}
    }

    @FXML
    void initialize() {
        assert combo != null : "fx:id=\"combo\" was not injected: check your FXML file 'ComboBoxGugudan.fxml'.";
        assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'ComboBoxGugudan.fxml'.";
        assert ta != null : "fx:id=\"ta\" was not injected: check your FXML file 'ComboBoxGugudan.fxml'.";
        combo.getItems().addAll("1단", "2단", "3단", "4단", "5단", "6단", "7단", "8단", "9단");
        combo.setValue("1단");
    }
}
