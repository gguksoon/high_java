package basic.thread;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ThreadTimerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblTime;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnStop;
    
    private boolean stop; // 시계 작동여부를 나타내는 변수

    @FXML
    void initialize() {
        btnStart.setOnAction(e -> { // 방법1
        	stop = false;
        	
        	Thread th = new Thread() {
        		@Override
        		public void run() {
        			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        			while(!stop) {
        				String strTime = sdf.format(new Date());
        				// 사용자가 만든 Thread에서 JavaFx의 컨트롤의 값을 변경시키려면
        				// Platform.runLater()메서드를 이용해야 한다.
        				Platform.runLater(() -> {
        					// 이 영역에서 JavaFx의 컨트롤 값을 변경하는 명령을 기술한다.
        					lblTime.setText(strTime);
        				});
        				
        				try {
							Thread.sleep(100);
						} catch (InterruptedException e2) {
							e2.printStackTrace();
						}
        			}
        		}
        	};
        	
        	th.setDaemon(true); // 데몬쓰레드 만들기
        	th.start();
        });
        
        btnStop.setOnAction(e -> handleBtnStop(e)); // 방법2
    }
    
    private void handleBtnStop(ActionEvent evt) {
    	stop = true;
    }
}
