package basic.menu;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxFileIOTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			//Parent root = FXMLLoader.load(getClass().getResource("FxFileIOTest.fxml"));
			
			// Fxml문서를 load한 후 Fxml문서에 설정한 Controller객체 구하기 ---
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FxFileIOTest.fxml"));
			Parent root = loader.load();
			
			FxFileIOTestController fxioController = loader.getController();
			//------------------------------------------------------
			
			fxioController.setPrimaryStage(primaryStage);
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("NoName.txt");
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
