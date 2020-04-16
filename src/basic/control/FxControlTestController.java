package basic.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class FxControlTestController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name;

    @FXML
    private RadioButton male;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton female;

    @FXML
    private CheckBox ck1;

    @FXML
    private CheckBox ck2;

    @FXML
    private CheckBox ck3;

    @FXML
    private CheckBox ck4;

    @FXML
    private CheckBox ck5;

    @FXML
    private CheckBox ck6;

    @FXML
    private CheckBox ck7;

    @FXML
    private CheckBox ck8;

    @FXML
    private Button btn;

    @FXML
    private TextArea ta;

    @FXML
    void printInfo(ActionEvent event) {
    	String namee = name.getText();
    	// 이름
    	if(namee.isEmpty()) {
    		alert("입력오류", "이름입력하셈");
    	}
    	ta.setText("이름은 " + name.getText() + " 입니다.\n");
    	
    	// 성별
    	RadioButton selectedRadioButton = (RadioButton) gender.getSelectedToggle();
    	String selectedGender = selectedRadioButton.getText();
    	ta.appendText("성별은 " + selectedGender + "자입니다.\n");
    	
    	// 취미
    	ta.appendText("취미는 ");
    	boolean hasHobby = false;
    	if(ck1.isSelected()) {
    		ta.appendText("여행 ");
    		hasHobby = true;
    	}
    	if(ck2.isSelected()) {
    		ta.appendText("등산 ");
    		hasHobby = true;
    	}
    	if(ck3.isSelected()) {
    		ta.appendText("독서 ");
    		hasHobby = true;
    	}
    	if(ck4.isSelected()) {
    		ta.appendText("바둑 ");
    		hasHobby = true;
    	}
    	if(ck5.isSelected()) {
    		ta.appendText("장기 ");
    		hasHobby = true;
    	}
    	if(ck6.isSelected()) {
    		ta.appendText("게임 ");
    		hasHobby = true;
    	}
    	if(ck7.isSelected()) {
    		ta.appendText("테니스 ");
    		hasHobby = true;
    	}
    	if(ck8.isSelected()) {
    		ta.appendText("배드민턴 ");
    		hasHobby = true;
    	}
    	
    	
    	if(hasHobby) ta.appendText("입니다.\n");
    	else ta.appendText("없습니다.\n");
    }
    
    public void alert(String head,String msg) {
    	Alert warning = new Alert(AlertType.WARNING);
    	warning.setTitle("경고창");
    	warning.setHeaderText("msg");
    	warning.setContentText(null);
    	warning.showAndWait();
    }

    @FXML
    void initialize() {
    }
}
