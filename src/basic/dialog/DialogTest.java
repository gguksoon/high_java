package basic.dialog;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DialogTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		HBox root = new HBox(10);
		root.setPadding(new Insets(10));
		
		VBox leftVbox = new VBox(10);
		VBox rightVbox = new VBox(10);
		
		
		
		Button btnFileOpen = new Button("Open File Chooser 실행");
		btnFileOpen.setOnAction(e -> {
			// FileChooser객체 생성
			FileChooser filechooser = new FileChooser();
			
			// Dialog에서 구분할 파일의 확장자 구성하기
			filechooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text File", "*.txt"),
				new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"),
				new ExtensionFilter("Audio File", "*.wav", "*.mp3", "*.mp4"),
				new ExtensionFilter("All File", "*.*")
			);
			
			// Dialog창을 열고 선택한 파일 정보 읽어오기
			File selectedFile = filechooser.showOpenDialog(primaryStage);
			
			// 선택한 파일이 있으면
			if(selectedFile != null) { 
				System.out.println("읽어올 파일명: " + selectedFile.getPath());
			}
		});
		
		
		
		Button btnFileSave = new Button("Save File Chooser 실행");
		btnFileSave.setOnAction(e -> {
			FileChooser filechooser = new FileChooser();
			
			filechooser.getExtensionFilters().addAll(
				new ExtensionFilter("All Files", "*.*")
			);
			
			File selectedFile = filechooser.showSaveDialog(primaryStage);
			
			if(selectedFile != null) {
				System.out.println("저장할 파일명: " + selectedFile.getPath());
			}
		});
		
		
		
		Button btnDirectory = new Button("Directory Chooser 실행");
		btnDirectory.setOnAction(e -> {
			DirectoryChooser dir = new DirectoryChooser();
			
			File selectedDir = dir.showDialog(primaryStage);
			
			if(selectedDir != null) {
				System.out.println("선택한 폴더: " + selectedDir.getPath());
			}
		});
		
		Button btnPopup = new Button("Popup 실행");
		btnPopup.setOnAction(e -> {
			Popup popup = new Popup();
			
			// Popup창을 구성한다.
			HBox popRoot = new HBox();
			popRoot.setAlignment(Pos.CENTER_LEFT);
			popRoot.setStyle("-fx-background-color:black; -fx-background-radius:20;");
			
			ImageView imgView = new ImageView();
			imgView.setImage(
					new Image(getClass().getResourceAsStream("../../images/ok.png")));
			
			// 보여줄 이미지의 크기 설정
			imgView.setFitWidth(30);
			imgView.setFitHeight(30);
		
			imgView.setOnMouseClicked(evt -> {
				popup.hide();
			});
			
			Label lblMsg = new Label();
			lblMsg.setText("메시지가 왔습니다.");
			lblMsg.setStyle("-fx-text-fill:red;");
			
			
			HBox.setMargin(lblMsg,  new Insets(0, 5, 0, 5));
			
			popRoot.getChildren().addAll(imgView, lblMsg);
			
			// Popup에 추가하기
			popup.getContent().add(popRoot);
			popup.setAutoHide(true);
			popup.show(primaryStage);
		});
		
		
		
		Button btnCustom = new Button("Custom Dialog 실행");
		btnCustom.setOnAction(e -> {
			try {
			/*	
				StageStyle.DECORATED
					==> 단색 배경 및 플랫폼 장식으로 일반 Stage 스타일
				StageStyle.UNDECORATED
					==> 단색 배경 및 장식이 없는 Stage 스타일
				StageStyle.UNIFIED
					==> 플랫폼 장식의 Stage스타일을 정의하고 클라이언트 영역과 장식 사이의
						경계를 제거한다.
				StageStyle.UTILITY
					==> 단색 배경과 최소한의 플랫폼 장식의 Stage스타일
			*/		
//				Stage dialog = new Stage(StageStyle.DECORATED);
//				Stage dialog = new Stage(StageStyle.UNDECORATED);
//				Stage dialog = new Stage(StageStyle.UNIFIED);
				Stage dialog = new Stage(StageStyle.UTILITY);
				
				// 부모창 지정하기
				dialog.initOwner(primaryStage);
				
				// 모달여부 설정 -> 자식창이 떠있을 때 부모창을 선택 못하는 것
				dialog.initModality(Modality.WINDOW_MODAL);
				
				dialog.setTitle("사용자 정의 창 연습");
				
				// FXML 문서 load하기
				Parent dialogRoot = FXMLLoader.load(
					getClass().getResource("customDialog.fxml")
				);
				
				// FXML문서에 만들어 놓은 컨트롤 객체 구해서 사용하기
				// (id를 이용한다.)
				// 형식) load한객체변수.lookup("#id속성값");
				
				Button btnOk = (Button) dialogRoot.lookup("#btnOk");
				Label lblMsg = (Label) dialogRoot.lookup("#lblMsg");
				btnOk.setOnAction(ee -> {
					dialog.close();
					lblMsg.setText("새로운메시지입니다.");
				});
				
				Scene scene = new Scene(dialogRoot);
				dialog.setScene(scene);
				dialog.setResizable(false);
				dialog.show();
				
			} catch (IOException ee) {
				ee.printStackTrace();
			}
		});
		
		leftVbox.getChildren().addAll(btnFileOpen, btnFileSave, btnDirectory);
		rightVbox.getChildren().addAll(btnPopup, btnCustom);
		
		root.getChildren().addAll(leftVbox, rightVbox);
		
		Scene scene = new Scene(root, 500, 400);
		primaryStage.setTitle("Dialog 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
