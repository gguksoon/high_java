package basic.control;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CheckBoxTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		String[] names = new String[]{"Security", "Project", "Chart"};
		Image[] images = new Image[names.length];
		ImageView[] icons = new ImageView[names.length];
		
		CheckBox[] chkboxs = new CheckBox[names.length];
		
		Rectangle rect = new Rectangle(90, 30);
		rect.setArcWidth(10);	// 꼭지점 모따기
		rect.setArcHeight(10);
		rect.setFill(Color.rgb(41, 41, 41));
		
		for(int i = 0; i < names.length; i++) {
			final Image img = images[i] = new Image(
					getClass().getResourceAsStream("../../images/"+ names[i] + ".png"));
			final ImageView imgView = icons[i] = new ImageView();
			chkboxs[i] = new CheckBox(names[i]);
			
			// CheckBox의 체크 상태를 감시하고 체크 상태가 변경되었을 때의 처리
//			chkboxs[i].selectedProperty().addListener(new ChangeListener<Boolean>() {
//				@Override
//				public void changed(ObservableValue<? extends Boolean> observable,
//						Boolean oldValue, Boolean newValue) {
//					imgView.setImage(newValue ? img : null); 	// 익명구현체에서는 final변수만 사용해야되는데, 
//																	// i는 변하는 수라서 에러가 발생함
//				}
//			});
			
			// CheckBox를 클릭했을 때 처리 ==> 이벤트 설정
			chkboxs[i].setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// 이벤트 객체의 getSource()메서드는 해당 이벤트가 발생한 객체를 반환한다.
					CheckBox chk = (CheckBox) event.getSource(); // 오브젝트형이므로 형변환해줌
					
					// isSelected() ==> CheckBox의 현재 체크 상태를 나타내는 메서드
					//				==> 체크상태: true, 미체크상태: false
					imgView.setImage(chk.isSelected() ? img : null);
				}
			});
		}
		
		Button btnOk = new Button("확  인");
		btnOk.setOnAction(e -> {
			if(chkboxs[0].isSelected()) {
				System.out.println("첫번째 체크박스 선택");
			} else {
				System.out.println("첫번째 체크박스 해제");
			}
			
			// setSelected(논리값); ==> 논리값에 true를 설정하면 체크상태가 되고
			//					  ==> 논리값에 false를 설정하면 체크가 해제된 상태가 된다.
			chkboxs[2].setSelected(!chkboxs[0].isSelected());
		});
		
		VBox vbox = new VBox(5);
		vbox.getChildren().addAll(chkboxs);
		vbox.getChildren().add(btnOk);
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(0, 0, 0, 5));
		hbox.getChildren().addAll(icons);
		
		StackPane stack = new StackPane();
		stack.getChildren().addAll(rect, hbox);
		StackPane.setAlignment(rect, Pos.TOP_CENTER);
		
		HBox root = new HBox(40);
		root.setPadding(new Insets(20, 10, 10, 20));
		root.getChildren().addAll(vbox, stack);
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("CheckBox 연습");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
