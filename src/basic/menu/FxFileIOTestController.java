package basic.menu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FxFileIOTestController {
	private String fileName = "NoName.txt";
	private File fileDir;
	private Stage primaryStage;  // 부모 Stage객체를 저장할 변수

    public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem newMenuItem;

    @FXML
    private MenuItem openMenuItem;

    @FXML
    private MenuItem saveMenuItem;

    @FXML
    private MenuItem closeMenuItem;
    
    @FXML
    private TextArea taMain;

    @FXML
    void closeMenuClick(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void newMenuClick(ActionEvent event) {
    	fileName = "NoName.txt";
    	taMain.clear();
    	primaryStage.setTitle(fileName);
    }

    @FXML
    void openMenuClick(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().addAll(
    		new ExtensionFilter("텍스트 문서(*.txt)", "*.txt"),
    		new ExtensionFilter("모든 문서(*.*)", "*.*")
    	);
    	
    	fileChooser.setInitialDirectory(fileDir);
    	
    	File selFile = fileChooser.showOpenDialog(primaryStage);
    	
    	if(selFile==null) {
    		return;
    	}
    	
    	fileDir = selFile.getParentFile(); 	// 선택한 파일의 경로 저장
    	fileName = selFile.getName();		// '파일명.확장자' 구하기
    	
    	BufferedReader br = null;
    	try {
			br = new BufferedReader(new FileReader(selFile));
			taMain.clear();
			
			char[] data = new char[1024];
			int len = 0;
			while((len = br.read(data))>0) {
				String strTmp = new String( Arrays.copyOf(data, len));
				taMain.appendText(strTmp);
			}
			
		} catch (IOException e) {
			fileName = "NoName.txt";
			e.printStackTrace();
		} finally {
			if(br!=null) 
				try { br.close(); }catch(IOException ee) {}
		}
    	
    	primaryStage.setTitle(fileName);
    }

    @FXML
    void saveMenuClick(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().addAll(
    		new ExtensionFilter("텍스트 문서(*.txt)", "*.txt"),
    		new ExtensionFilter("모든 문서(*.*)", "*.*")
    	);
    	
    	fileChooser.setInitialDirectory(fileDir);  // 저장할 기본 폴더 설정
    	fileChooser.setInitialFileName(fileName);  // 저장할 기본 파일명 설정
    	
    	File selFile = fileChooser.showSaveDialog(primaryStage);
    	if(selFile==null) {
    		return;
    	}
    	
    	fileDir = selFile.getParentFile();
    	fileName = selFile.getName();
    	
    	BufferedWriter bw = null;
    	try {
			bw = new BufferedWriter(new FileWriter(selFile));
			
			//  TextArea의 줄바꿈(\n)을 파일의 줄바꿈(\r\n)으로 변환해서 저장한다.
			bw.write(taMain.getText().replaceAll("\\n", "\r\n"));
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(bw!=null)
				try { bw.close(); } catch(IOException ee) {}
		}
    	
    	primaryStage.setTitle(fileName);
    	
    	
    }
    
    @FXML
    void textChanged(KeyEvent event) {
    	
    }

    @FXML
    void initialize() {
    	fileDir = new File(System.getProperty("user.home") + "\\Documents");
    	//System.out.println(System.getProperty("user.home"));
    }
}
