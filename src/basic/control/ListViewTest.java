package basic.control;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListViewTest extends Application {

	@Override
	public void start(Stage primaryStage) {

		// javaFx에서 많은 양의 데이터를 처리할 때 List를 사용하는데
		// javaFx의 컨트롤에 사용하는 List는 ObservableList객체를 사용한다.
		ObservableList<String> dataList = FXCollections.observableArrayList(
				"green", "gold", "red", "blue", "black", "brown",
				"pink", "blueviolet", "chocolate", "yellow"
		); // ==> ArrayList와 비슷함(Fx버전)
		
		Label label = new Label();
		label.setFont(new Font(20));

		// ListView에 데이터 셋팅하기(방법1) 
//		ListView<String> list = new ListView<String>();
//		list.setItems(dataList);
		
		// ListView에 데이터 셋팅하기(방법2)
		ListView<String> list = new ListView<String>(dataList);
		
		// ListView에서 데이터를 선택했을 때의 처리
		list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				label.setText(newValue);
				label.setTextFill(Color.web(newValue)); // 글자색 변경
			}	
		});
		
		// ListView의 원래 데이터는 변경되지 않고 화면에 보이는 부분을 변경하기
		list.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

			@Override
			public ListCell<String> call(ListView<String> param) {
				return new ListCell<String>() { // 익명구현체

					@Override
					protected void updateItem(String item, boolean empty) {
						// item변수는 원래 데이터
						// empty ==> 현재 cell에 데이터가 없으면 true
						super.updateItem(item, empty);
						Rectangle rect = new Rectangle(200, 20);
						if (item != null) {
							rect.setFill(Color.web(item));
							setGraphic(rect); // 보이는 데이터 변경
//							setText(item + " ==> 새로운 값");

							// 보이는 데이터를 변경하는 메서드는
							// setText()메서드와 setGraphic()메서드가 있다.
							// setText()메서드는 '문자열'로 나타낼 때 사용하고,
							// setGraphic()메서드는 '도형'이나 '컨트롤'객체로
							//
						}
					}
				};
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(10));
		vbox.getChildren().addAll(list, label);
		
		Scene scene = new Scene(vbox);
		primaryStage.setScene(scene);
		primaryStage.setTitle("ListView 연습");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
