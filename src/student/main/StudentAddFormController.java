package student.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import student.service.IStudentService;
import student.service.StudentServiceImpl;
import student.vo.StudentVO;

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
    
    public StudentAddFormController() {
		this.dataList = dataList;
	}

	@FXML
    void btnCancelClick(ActionEvent event) {
		// 현재 창의 Stage객체 구하기
		// 형식) 현재창에 있는 컨트롤객체.getScene().getWindow()
		
    }

    @FXML
    void btnSaveClick(ActionEvent event) {

    }

    // Service객체 변수 선언
    private IStudentService service;
    
    @FXML
    void initialize() {
    	// Service객체 생성
    	service = StudentServiceImpl.getInstance();
    }
}
