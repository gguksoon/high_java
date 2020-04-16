package basic.animation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class loginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane login;

    @FXML
    private Button btnMain;

    @FXML
    void initialize() {
    	btnMain.setOnAction(e -> handleBtnMain(e));
    }
    
    public void handleBtnMain(ActionEvent event) {
    	StackPane root = (StackPane) btnMain.getScene().getRoot();
//    	root.getChildren().remove(login); // 메인화면으로 이동(메인화면 위에 있는 로그인 화면 삭제)
    	
    	login.setTranslateX(0); // 시작값
    	
    	KeyValue keyValue = new KeyValue(login.translateXProperty(), 350);
    	
    	KeyFrame keyFrame = new KeyFrame(
    			Duration.millis(1000), 
    			new EventHandler<ActionEvent>() {
    				@Override
    				public void handle(ActionEvent event) {
    					root.getChildren().remove(login);
    				}
    			},
    			keyValue);
    	
    	Timeline timeline = new Timeline();
    	timeline.getKeyFrames().add(keyFrame);
    	
    	timeline.play();
    }
}
