package basic.student.main;

import java.net.URL;
import java.util.ResourceBundle;

import basic.student.service.IStudentService;
import basic.student.service.StudentServiceImpl;
import basic.student.vo.StudentVO;
import basic.util.MyAlert;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class StudentAddFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfKor;

    @FXML
    private TextField tfEng;

    @FXML
    private TextField tfMat;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;
    
    // TableView와 연결될 List객체 변수 선언
    private ObservableList<StudentVO> dataList;
    
    public ObservableList<StudentVO> getDataList() {
		return dataList;
	}

	public void setDataList(ObservableList<StudentVO> dataList) {
		this.dataList = dataList;
	}

	@FXML
    void btnCancelClick(ActionEvent event) {
		// 현재 창의 Stage객체 구하기
		// 형식) 현재창에 있는 컨트롤객체.getScene().getWindow()
		Stage currentStage = (Stage) btnCancel.getScene().getWindow();
		currentStage.close();
    }

	// 저장 버튼
    @FXML
    void btnSaveClick(ActionEvent event) {
    	String name = tfName.getText().trim();
    	String strKor = tfKor.getText().trim();
    	String strEng = tfEng.getText().trim();
    	String strMat = tfMat.getText().trim();
    	
    	if(name.isEmpty()) {
    		MyAlert.alert("이름을 입력하세요.");
    		tfName.requestFocus();
    		return;
    	}
    	
    	if(strKor.isEmpty()) {
    		MyAlert.alert("국어점수를 입력하세요.");
    		tfKor.requestFocus();
    		return;
    	}
    	if(strEng.isEmpty()) {
    		MyAlert.alert("영어점수를 입력하세요.");
    		tfEng.requestFocus();
    		return;
    	}
    	if(strMat.isEmpty()) {
    		MyAlert.alert("수학점수를 입력하세요.");
    		tfMat.requestFocus();
    		return;
    	}
    	
    	// 입력한 데이터를 StudentVO에 담는다.
    	StudentVO stdVo = new StudentVO(name, 
    		Integer.parseInt(strKor), Integer.parseInt(strEng), 
    		Integer.parseInt(strMat));
    	
    	// DB에 저장하기
    	int cnt = service.insertStudent(stdVo);
    	
    	if(cnt>0) {
    		dataList.add(stdVo);
    		MyAlert.info(name + "학생의 성적을 추가했습니다.");
    	}else {
    		MyAlert.info(name + "학생의 성적 추가 실패!!");
    	}
    	
    	tfName.clear();
    	tfKor.clear();
    	tfEng.clear();
    	tfMat.clear();
    	
    }

    // Service객체 변수 선언
    private IStudentService service;
    
    private 
    @FXML
    void initialize() {
    	// Service객체 생성
    	service = StudentServiceImpl.getInstance();
    }
}
