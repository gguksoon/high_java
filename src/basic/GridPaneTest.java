package basic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GridPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		GridPane grid = new GridPane();
		grid.setPrefSize(300, 200);
		grid.setHgap(10);
		grid.setVgap(20);
		grid.setPadding(new Insets(10));
		// Style 적용하기
		grid.setStyle("-fx-background-color: yellow;");
		
		Label lblId = new Label("아 이 디: ");
		Label lblPass = new Label("패스워드: ");
		
		TextField tfId = new TextField();
		PasswordField pfPass = new PasswordField();
		
		// Style 적용하기
		tfId.setStyle("-fx-background-color: black;"
					+ "-fx-text-fill: red");
		
		
		Button btnLogin = new Button("로그인");
		Button btnCancel = new Button("취 소");
		
		// GridPane에 컨트롤들을 추가하는 방법
		// 형식) 객체변수.add(추가할 컨트롤, 칸번호, 행번호, colspan, rowspan);
		// 					colspan: 좌우 합치기 / rowspan: 위아래 합치기
		grid.add(lblId, 1, 1, 2, 1);
		grid.add(lblPass, 1, 2, 2, 1);
		grid.add(tfId, 3, 1, 3, 1);
		grid.add(pfPass, 3, 2, 3, 1);
		grid.add(btnLogin, 3, 4);
		grid.add(btnCancel, 4, 4);
		
		Scene scene = new Scene(grid);
		primaryStage.setScene(scene);
		primaryStage.setTitle("GridPane 연습");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
