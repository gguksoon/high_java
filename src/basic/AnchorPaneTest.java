package basic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnchorPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		// AnchorPane ==> 컨트롤들을 x좌표, y좌표를 이용해서 배치한다.
		AnchorPane root = new AnchorPane();
		root.setPrefSize(300, 150);
		
		Label lblId = new Label("아 이 디: ");
		lblId.setLayoutX(62); // x축 좌표 설정
		lblId.setLayoutY(22); // y축 좌표 설정
		
		Label lblPass = new Label("패스워드: ");
		lblPass.setLayoutX(62);
		lblPass.setLayoutY(68);
		
		TextField tfId = new TextField();
		tfId.setLayoutX(132);
		tfId.setLayoutY(18);
		
		PasswordField tfPass = new PasswordField();
		tfPass.setLayoutX(132);
		tfPass.setLayoutY(64);
		
		Button btnLogin = new Button("로그인");
		btnLogin.setLayoutX(86);
		btnLogin.setLayoutY(106);
		
		Button btnCancel = new Button("취  소");
		btnCancel.setLayoutX(160);
		btnCancel.setLayoutY(106);
		
		root.getChildren().addAll(lblId, lblPass, tfId, tfPass, btnLogin, btnCancel);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("AnchorPane 연습");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
