package basic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BorderPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		root.setPrefSize(300, 200);
		
		ToolBar toolbar = new ToolBar(
			new Button("첫번째"),
			new Button("두번째"),
			new Button("세번째"),
			new Button("네번째")
		);
		
		TextArea txtArea = new TextArea();
		
		BorderPane bottomPane = new BorderPane();
		TextField tfTest = new TextField();
		Button btnOk = new Button("확  인");
		bottomPane.setCenter(tfTest);
		bottomPane.setRight(btnOk);
		
		root.setTop(toolbar);
		root.setCenter(txtArea);
		root.setBottom(bottomPane);
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("BorderPane 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
