package basic.event;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlEventTest extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		// load()메서드가 하는 일
		// - FXML문서를 읽어와 FXML문서에 구성해 놓은 컨테이너 및 컨트롤 객체들을 생성한다.
		// - FMXL문서에 설정된 Controller객체를 생성한 후 FXML문서에서 만들어진 객체들을 매핑시킨다. 
		Parent root = FXMLLoader.load(getClass().getResource("FxmlEventTest.fxml"));
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("FXML문서의 이벤트 처리 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
