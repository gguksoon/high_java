package basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProgramLayout extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		VBox root = new VBox();  		// VBox 레이아웃 객체 생성
		root.setPrefWidth(650);			// VBox의 너비
		root.setPrefHeight(150); 		// VBox의 높이
//		root.setPrefSize(650, 150);		// VBox의 너비와 높이 동시에 설정
		
		root.setAlignment(Pos.CENTER); 	// 배치될 컨드롤들을 가운데 정렬
		root.setSpacing(20);			// 배치될 컨트롤간의 간격
		
		// 안쪽 여백
		// Insets객체는 상, 우, 하, 좌 순으로 값을 설정한다.
		root.setPadding(new Insets(10, 10, 10, 10)); 
//		root.setPadding(new Insets(10)); // 4방향의 값이 모두 같을 때 1개만 사용
		
		Label lblMessage = new Label();				// Label 컨트롤 객체 생성
		lblMessage.setText("안녕하세요. javaFx입니다."); // 메시지 저장
		lblMessage.setFont(new Font(50));			// 크기 지정
		
		Button btnOk = new Button();
		btnOk.setText("확  인");
		
		Button btnClose = new Button("닫  기"); // 한번에 setText() 가능
		
		// 닫기 버튼에 대한 클릭 이벤트 설정
		btnClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// 이벤트가 발생했을 때 처리할 내용을 기술한다.
				
				// 프로그램 종료하기(셋 중 하나로 종료)
//				primaryStage.close();
//				System.exit(0);
				Platform.exit(); 
			}
		}); // --> 나중에 람다식으로 변경할 듯

		// 
		TextField txtField = new TextField();
		txtField.setPrefWidth(300);
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10));
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER); // 중앙 정렬
		
		// HBox에 컨트롤 객체들을 추가하기
		// HBox의 ObservableList객체 구하기
		ObservableList<Node> list = hbox.getChildren();
		
		// 생성한 컨트롤들을 ObservableList에 추가한다.
		list.add(txtField);
		list.add(btnOk);
		
//		list.addAll(txtField, btnOk); // 한번에 add() 가능
		
		// VBox에 컨트롤들 추가하기
		root.getChildren().add(lblMessage);
		root.getChildren().add(hbox);
		root.getChildren().add(btnClose);
//		root.getChildren().addAll(lblMessage, hbox, btnClose); // 한번에 add() 가능
		
		// VBox를 루트 컨테이너로 지정해서 Scene객체 생성 
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("프로그램으로 레이아웃 설정하기"); // 프로그램 제목
		primaryStage.setScene(scene); // Stage에 Scene 추가
		primaryStage.show(); // 윈도우(Stage) 보이기
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
