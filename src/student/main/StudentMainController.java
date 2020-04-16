package student.main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import student.service.IStudentService;
import student.vo.StudentVO;

public class StudentMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<StudentVO> stdTable;

    @FXML
    private TableColumn<?, ?> stdNameCol;

    @FXML
    private TableColumn<?, ?> stdKorCol;

    @FXML
    private TableColumn<?, ?> stdEngCol;

    @FXML
    private TableColumn<?, ?> stdMatCol;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnViewBarChart;

    // 추가버튼
    @FXML
    void btnAddClick(ActionEvent event) throws IOException {
    	Stage addForm = new Stage(StageStyle.UTILITY);
    	addForm.initModality(Modality.WINDOW_MODAL);
    	addForm.setTitle("자료추가");
    	
    	FXMLLoader loader =
    			new FXMLLoader(getClass().getResource("../view/StudentAddForm.xml"));
    	Parent addFormRoot = loader.load();
    	
    	StudentAddFormController addController = loader.getController();
    	
    	// TableView에 셋팅된 List를 추가 창에 전달한다.
    	
    	Scene scene = new Scene(addFormRoot);
    	addForm.setScene(scene);
    	addForm.show();
    	
    }

    @FXML
    void btnViewBarChartClick(ActionEvent event) {

    }
    
    // Service객체변수 선언
    private IStudentService service;
    
    private List<StudentVO> stdList;
    private ObservableList<StudentVO> dataList;

    @FXML
    void initialize() {
    	// TableView의 각 컬럼 설정
    	stdNameCol.setCellValueFactory(new PropertyValueFactory<>("std_name"));
    	stdKorCol.setCellValueFactory(new PropertyValueFactory<>("std_kor"));
    	stdEngCol.setCellValueFactory(new PropertyValueFactory<>("std_eng"));
    	stdMatCol.setCellValueFactory(new PropertyValueFactory<>("std_mat"));
    	
    	// DB에서 전체 데이터를 가져와 TableView에 나타내기
    	stdList = service.getAllStudent();
    	if(stdList != null) {
    		dataList = FXCollections.observableArrayList(stdList);
    	} else {
    		dataList = FXCollections.observableArrayList();
    	}
    	stdTable.setItems(dataList);
    	
    }
}
