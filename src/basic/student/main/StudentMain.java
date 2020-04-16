package basic.student.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = 
				new FXMLLoader(getClass().getResource("../view/StudentMain.fxml"));
			
			Parent root = loader.load();
			
			// Controller객체 구하기
			StudentMainController stdController = loader.getController();
			
			// Controller객체에 메인 Stage객체를 셋팅한다.
			stdController.setPrimaryStage(primaryStage);
			
			
			Scene scene = new Scene(root);
			
			primaryStage.setTitle("학생 성적 처리");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
