package basic.control;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ComboBoxTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		HBox hbox = new HBox();
		TextArea taResult = new TextArea();
		
		//콤보박스 생성 후 데이터 셋팅 방법 1
		ComboBox<String> combo = new ComboBox<String>();
		combo.getItems().addAll("한강","금강","낙동강","영산강");
		combo.setValue("낙동강");
		
		// 콤보박스에서 선택값이 변경될 때의 처리. 즉, change이벤트 처리와 같다.
		combo.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				taResult.setText("첫번째 콤보박스에서 선택한 데이터: " + newValue);
			}
		});
		
		//방법 2 (ObservavleList에 담는다네유~~)
		ObservableList<String> comboData = FXCollections.observableArrayList(
				"사과", "복숭아", "배", "포도", "자두");
		ComboBox<String> combo2 = new ComboBox<String>(comboData);
		combo2.setValue(comboData.get(0));
		
		//방법 3 
//		ComboBox<String> combo2 = new ComboBox<String>();
//		combo2.setItems(comboData);
		
		combo2.getItems().addAll("감","대추");
		
		Button btnOk = new Button("확인");
		btnOk.setOnAction(e -> {
//			taResult.setText( String.valueOf(combo2.getValue() == null) );
			if( combo.getValue() != null && combo2.getValue() != null ) {
				taResult.setText(combo.getValue() + " 지역의 과일은 ");
				taResult.appendText(combo2.getValue() + "가 유명합니다.");
			}
		});
		
		Button btnClose = new Button("닫기");
		btnClose.setOnAction(event -> {System.exit(0);});
		
		hbox.setSpacing(10);
		hbox.getChildren().addAll(combo, combo2, btnOk, btnClose);
		hbox.setPadding(new Insets(5,5,15,5));
		
		root.setTop(hbox);
		root.setCenter(taResult);
		root.setPadding(new Insets(15));
		
		Scene sc = new Scene(root, 500, 400);
		primaryStage.setScene(sc);
		primaryStage.setTitle("combobox 연습이라네유~");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
