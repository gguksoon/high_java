package basic.animation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class rootController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogin;

    @FXML
    void initialize() {
    	btnLogin.setOnAction(e -> handleBtnLogin(e));
    }

	public void handleBtnLogin(ActionEvent event) {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));
			
			// 현재 창의 Root컨테이너 객체 구하기
			// 형식) 현재창의 컨트롤 객체.getScene().getRoot();
			StackPane root = (StackPane) btnLogin.getScene().getRoot();
			root.getChildren().add(login);
			
			
			
//			// 이동 애니메이션
//			
//			// x축으로 평행이동 할 양 설정(애니메이션을 시작할 위치로 설정한다.)
//			login.setTranslateX(350); 
//			
//			// 타겟 속성과 종료값을 설정하는 객체
//			// 형식) new KeyValue(변경될대상속성, 종료값)
//			KeyValue keyValue = new KeyValue(login.translateXProperty(), 0);
//			
//			// 애니메이션 지속 시간과 KeyValue를 설정하는 객체 생성
//			// 형식1) new KeyFrame(지속시간, KeyValue객체);
//			// 형식2) new KeyFrame(지속시간, 애니메이션이 종료된 후에 처리될 이벤트, KeyValue객체);			
//			KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), keyValue);
			
			
			
//			// Fade효과 애니메이션(값의 범위 : 0.0(투명) ~ 1.0(불투명))
//			login.setOpacity(0); // 시작값
//			
//			KeyValue keyValue = new KeyValue(login.opacityProperty(), 1);
//			KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), keyValue);
			
			
			
//			// 회전 애니메이션
//			login.setRotate(180); // 시작값
//			KeyValue keyValue = new KeyValue(login.rotateProperty(), 0);
//			KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), keyValue);
			
			
			
			// 확대 축소 배율 애니메이션
			login.setScaleX(0); // 값이 2이면 두배 크기라는 의미이다.
			login.setScaleY(0);
			
			KeyValue keyValue = new KeyValue(login.scaleXProperty(), 1);
			KeyValue keyValue2 = new KeyValue(login.scaleYProperty(), 1);
			
			KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), keyValue);
			KeyFrame keyFrame2 = new KeyFrame(Duration.millis(1000), keyValue2);
			
			
			
			// KeyFrame에 설정된 내용대로 애니메이션을 진행하는 객체 생성
			Timeline timeline = new Timeline();
//			timeline.getKeyFrames().add(keyFrame); // 확대 축소 배율을 제외하고 다
			timeline.getKeyFrames().addAll(keyFrame, keyFrame2); // 확대 축소 배율 전용
			
			// 애니메이션 실행
			timeline.play();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
