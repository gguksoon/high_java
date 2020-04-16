package basic.control;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RadioButtonTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		// 라디오 버튼들을 묶음으로 처리할 객체 생성
		ToggleGroup group = new ToggleGroup();
				
		ImageView icon = new ImageView();
		
		RadioButton rb1 = new RadioButton("Home");
		rb1.setSelected(true); // 선택시켜놓기
		rb1.setToggleGroup(group); 	// 토글그룹에 라디오버튼 추가
		rb1.setUserData("Home"); 	// 선택한 값을 나타내기 위해 설정 
		
		RadioButton rb2 = new RadioButton("Calendar");
		rb2.setToggleGroup(group); 		// 토글그룹에 라디오버튼 추가
		rb2.setUserData("Calendar"); 	// 선택한 값을 나타내기 위해 설정 
		
		RadioButton rb3 = new RadioButton("Contacts");
		rb3.setToggleGroup(group); 		// 토글그룹에 라디오버튼 추가
		rb3.setUserData("Contacts"); 	// 선택한 값을 나타내기 위해 설정
		
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable,
					Toggle oldValue, Toggle newValue) {
				// 이 메서드에서의 Toggle객체에는 RadioButton객체가 저장되어 있다.
				
				// getSelectedToggle() ==>
				// 			ToggleGroup에서 현재 선택된 객체를 반환하는 메서드
				if(group.getSelectedToggle().getUserData() != null) {
//				if(newValue.getUserData() != null) {
					// 방법1 ==> Toggle객체를 RadioButton객체로 변환해서 처리
					RadioButton rb = (RadioButton) newValue; 
					String url = rb.getText(); // 라디오버튼에 설정된 문자열 구하기
					// 방법2 
//					String url = group.getSelectedToggle().getUserData().toString();
					Image img = new Image(getClass().getResourceAsStream(
							"../../images/" + url + ".jpg"));
					icon.setImage(img);
				}
			}
		});
		
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(rb1, rb2, rb3);
		
		HBox root = new HBox(50);
		root.setPadding(new Insets(10));
		root.getChildren().addAll(vbox, icon);
		
		Scene scene = new Scene(root, 250, 150);
		primaryStage.setTitle("RadioButton 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
