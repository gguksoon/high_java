package basic.event;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventTest extends Application {
	private TextArea txtArea =  new TextArea();
	private TextField tfMessage = new TextField();
	
	@Override
	public void start(Stage primaryStage) {
		VBox root = new VBox(10);
		root.setPadding(new Insets(10));
		
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(10));
		
		Button btn1 = new Button("첫번째");
		Button btn2 = new Button("두번째");
		
		
		
		// < 이벤트 처리 방법1 >
		// 객체.setOn이벤트명()메서드에 EventHandler 인터페이스를 익명 구현체 형식으로 구현한다.
		// (이 인터페이스에는 handle()메서드가 있는데, 이 메서드에 처리할 내용을 기술한다.) 
		 
		/*
		btn1.setOnAction(new EventHandler<ActionEvent>() { // 익명구현체 형식
			
			@Override
			public void handle(ActionEvent event) {
				// 이벤트가 발생했을 때 처리할 내용을 이 곳에 기술한다.
				txtArea.setText("첫번째 버튼입니다.");
			}
		});
		*/
		
		// 위 내용(이벤트 설정)을 람다식으로 작성할 수 있다.
		btn1.setOnAction(
			event -> { txtArea.setText("람다식의 첫번째 버튼입니다."); }
		);
		

		
		// < 이벤트 처리 방법2 >
		// 객체.addEventHandler()메서드를 사용한다.
		// 이 메서드의 첫번째 매개변수에는 '이벤트 종류'를 지정하고
		// 두번째 매개변수에는 EventHandler인터페이스를 구현한 객체를 지정한다.
		
		/*
		btn2.addEventHandler(
			ActionEvent.ACTION,
			new EventHandler<Event>() {
				public void handle(Event event) {
					String temp = tfMessage.getText(); // textArea의 텍스트 읽어오기
//					txtArea.setText(temp + "\n");
					txtArea.appendText(temp + "\n"); // TextArea에 있는 기존 내용 뒤쪽에 추가
				}
			} 
		);
		*/
		
		// 위 내용(이벤트 설정)을 람다식으로 작성할 수 있다.
		btn2.addEventHandler(
			ActionEvent.ACTION,		
			e -> {
				txtArea.setText("람다식으로 처리하는 두번째 버튼...\n");
				String temp = tfMessage.getText();
				txtArea.appendText(temp + "\n");
			}
		);
		
		
		
		Button btn3 = new Button("세번째");
		
		// < 이벤트 처리 방법3-2 >
		// setOn이벤트명()메서드나 addEventHandler()메서드의 매개변수로
		// EventHandler인터페이스를 구현한 클래스의 인스턴스를 설정한다.
//		btn3.setOnAction(new MyEventHandler());
		btn3.addEventHandler(ActionEvent.ACTION, new MyEventHandler());
		
		
		
		Button btn4 = new Button("네번째");
		
		// < 이벤트 처리 방법4-2 >
		// 방법3과 동일하게 처리
//		btn4.setOnAction(new MyEventHandler2(txtArea, tfMessage));
		btn4.addEventHandler(ActionEvent.ACTION, new MyEventHandler2(txtArea, tfMessage));
		
		

		//=============================================
		
		
		hbox.getChildren().addAll(btn1, btn2, btn3, btn4);
		
		root.getChildren().addAll(hbox, tfMessage, txtArea);
		
		Scene scene = new Scene(root, 500, 350);
		
		primaryStage.setTitle("Event 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	// < 이벤트 처리 방법3-1 >
	// EventHandler인터페이스를 inner클래스 형식으로 구현하는 클래스를 작성한다.
	class MyEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			String temp = tfMessage.getText();
			txtArea.setText("세번째 inner클래스 이용하기");
			txtArea.appendText(temp);
		}
	}
	

}

// < 이벤트 처리 방법4-1 >
// 외부의 독립된 형태의 객체를 이용하는 방법
// EventHandler인터페이스를 외부클래스 형식으로 구현하는 클래스를 작성한다.
class MyEventHandler2 implements EventHandler<ActionEvent> {
	
	// EventTest클래스의 txtArea와 tfMessage를 사용할 수 없다.
	// 그래서 이 곳에도 만들어 줘야 한다. (이 후 매개변수로 값을 받아준다.)
	private TextArea ta;
	private TextField tf;
	
	// 생성자
	public MyEventHandler2(TextArea ta, TextField tf) {
		this.ta = ta;
		this.tf = tf;
	}
	
	@Override
	public void handle(ActionEvent event) {
		ta.setText("네번째 방법입니다.\n");
		String str = tf.getText();
		ta.appendText(str);
	}
	
}