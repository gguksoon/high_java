package basic.control;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class AlertTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		// 메시지 창 연습
//		Alert infor = new Alert(AlertType.INFORMATION);
//		infor.setTitle("INFORMATION");
//		infor.setHeaderText("Information 보기");
//		infor.setContentText("Information Alert창 입니다.");
////		infor.showAndWait(); // 출력 후 대기
//		infor.show(); // 출력
		
		// ----------------------------------------------
		
//		Alert warning = new Alert(AlertType.WARNING);
//		warning.setTitle("WARNING");
//		warning.setHeaderText("WARNING 보기");
//		warning.setContentText("WARNING Alert창 입니다.");
//		warning.showAndWait();
		
		// ----------------------------------------------
		
//		Alert error = new Alert(AlertType.ERROR);
//		error.setTitle("ERROR");
//		error.setHeaderText("ERROR 보기");
//		error.setContentText("ERROR Alert창 입니다.");
//		error.showAndWait();
		
		// ----------------------------------------------
		
//		Alert confirm = new Alert(AlertType.CONFIRMATION);
//		confirm.setTitle("CONFIRMATION");
//		confirm.setHeaderText("CONFIRMATION 보기");
//		confirm.setContentText("CONFIRMATION 입니다.");
//		
//		// Alert창을 보여주고 사용자가 선택한 버튼 값 읽어오기
//		ButtonType confirmResult = confirm.showAndWait().get();
//		
//		if(confirmResult == ButtonType.OK) {
//			System.out.println("OK버튼이 눌렸습니다.");
//		} else if(confirmResult == ButtonType.CANCEL) {
//			System.out.println("취소버튼이 눌렸습니다.");
//		}
		
		// ----------------------------------------------
		
		// 자바스크립트의 prompt창과 같은 기능
		TextInputDialog  prompt = new TextInputDialog("기본값");
		prompt.setTitle("Prompt 창");
		prompt.setHeaderText("Prompt 보기");
		prompt.setContentText("자료 입력: ");
		
		// 창을 보여주고 사용자가 입력한 값 읽어오기
		Optional<String> result = prompt.showAndWait();
		
		String input = "none";
		if(result.isPresent()) { // 값이 있으면
			input = result.get(); // 값 가져오기
		}
		System.out.println("결과값: " + input);
		
				
	}

	public static void main(String[] args) {
		launch(args);
	}
}
