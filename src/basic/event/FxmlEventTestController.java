package basic.event;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FxmlEventTestController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private TextField tfMsg;

    @FXML
    private TextArea txtArea;

    // FXML문서에서 설정한 이벤트처리 메서드 --> 첫번째 버튼 클릭 이벤트 처리
    @FXML
    void btn1Click(ActionEvent event) {
    	String temp = tfMsg.getText();
    	txtArea.appendText(temp + "\n");
    	
    	// TextField나 TextArea의 내용 지우기
    	// setText("") 또는 clear()메서드를 이용한다.
//    	tfMsg.setText("");
    	tfMsg.clear();
    	
    	tfMsg.requestFocus(); // 해당 객체에 포커스를 준다.
    }

    @FXML
    void initialize() {
    	// 이 메서드에는 주로 초기화 작업이나 이벤트 설정 작업등을 기술한다.
    	
    	// 두번째 버튼에 대한 이벤트 설정
    	btn2.setOnAction(e->{
    		String str = tfMsg.getText();
    		txtArea.appendText("두번째 버튼: " + str + "\n");
    		
    		tfMsg.clear();
    		tfMsg.requestFocus();
    	});
    }
}
