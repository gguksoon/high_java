package basic;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlLayout extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		// Fxml문서를 읽어와 현재 Stage에 적용하는 방법
		
		// Fxml문서를 읽어오는 방법1 --> Try Catch를 이용해야 하지만 여기선 throws를 사용했음
//		VBox root = FXMLLoader.load(getClass().getResource("FxmlLayout.fxml")); // --> Root(최상위 객체)자료형 정하기
//		Parent root = FXMLLoader.load(getClass().getResource("FxmlLayout.fxml")); // --> 가장 최상위(Parent)로 넓게 설정하기
		
		// Fxml문서를 읽어오는 방법2
		FXMLLoader loader = new FXMLLoader(getClass().getResource("FxmlLayout.fxml"));
		Parent root = loader.load();
		
		// 방법1이 편해보이지만, 방법2를 사용해야 할 경우가 발생함(이벤트 연결; 자바코드사용)
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Fxml문서를 이용한 레이아웃 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
