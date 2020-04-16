package basic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class FlowPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		// FlowPane ==> 컨트롤들을 차례로 배치하는데 가로 크기보다 컨트롤들이 더 많으면
		//				끝에서 자동으로 줄바꿈되어 배치되는 컨테이너
		FlowPane root = new FlowPane();
		root.setPrefSize(300, 100);
		root.setHgap(10); // 수평 간격
		root.setVgap(10); // 수직 간격
		root.setPadding(new Insets(10));
		
		Button btn1 = new Button("버튼1");
		Button btn2 = new Button("버튼2");
		Button btn3 = new Button("버튼3");
		Button btn4 = new Button("버튼4");
		Button btn5 = new Button("버튼5");
		Button btn6 = new Button("버튼6");
		Button btn7 = new Button("버튼7");
		
		root.getChildren().addAll(btn1, btn2, btn3, btn4, btn5, btn6, btn7);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("FlowPane 연습");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
