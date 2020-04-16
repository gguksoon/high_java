package basic.student.main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import basic.student.service.IStudentService;
import basic.student.service.StudentServiceImpl;
import basic.student.vo.StudentVO;
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

public class StudentMainController {
	
	// 메인창의 Stage객체가 저장될 변수 선언
	private Stage primaryStage;

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
    	addForm.initOwner(primaryStage);
    	addForm.setTitle("자료추가");
    	
    	FXMLLoader loader = 
    		new FXMLLoader(getClass().getResource("../view/StudentAddForm.fxml"));
    	Parent addFormRoot = loader.load();
    	
    	StudentAddFormController addController = loader.getController();
    	
    	// TableView에 셋팅된 List를 추가 창에 전달한다.
    	addController.setDataList(dataList);
    	
    	Scene scene = new Scene(addFormRoot);
    	addForm.setScene(scene);
    	addForm.show();
    	
    	
    }
    
    
    @FXML
    void btnViewBarChartClick(ActionEvent event) {
    	Stage barChartForm = new Stage(StageStyle.UTILITY);
    	barChartForm.initModality(Modality.WINDOW_MODAL);
    	barChartForm.initOwner(primaryStage);
    	barChartForm.setTitle("막대 그래프");
    	
    	BarChartForm bcForm = new BarChartForm(dataList);
    	
    	Scene scene = new Scene(bcForm, 600,500);
    	barChartForm.setScene(scene);
    	barChartForm.show();
    	
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
    	
    	// Sevice객체 생성하기
    	service = StudentServiceImpl.getInstance();
    	
    	// DB에서 전체 데이터 가져와 TableView에 나타내기
    	stdList = service.getAllStudent();
    	if(stdList!=null) {
    		dataList = FXCollections.observableArrayList(stdList);
    	}else {
    		dataList = FXCollections.observableArrayList();
    	}
    	stdTable.setItems(dataList);
    	
    	
    	// TableView를 클릭했을 때 이벤트 처리
    	stdTable.setOnMouseClicked(e->{
    		if(stdTable.getSelectionModel().isEmpty()) {
    			return;
    		}
    		
    		// TableView에서 클릭한 데이터 구하기
    		StudentVO stdVo = stdTable.getSelectionModel().getSelectedItem();
    		
    		Stage pieChartForm = new Stage(StageStyle.UTILITY);
    		pieChartForm.initModality(Modality.WINDOW_MODAL);
    		pieChartForm.initOwner(primaryStage);
    		pieChartForm.setTitle("원형 그래프");
    		try {
    		
	    		FXMLLoader loader = 
	    			new FXMLLoader(getClass().getResource("../view/StudentPieChartForm.fxml"));
				Parent pieChartRoot = loader.load();
				
				StudentPieChartController pieChartController = loader.getController();
				
				pieChartController.setStdVo(stdVo);
				
				Scene scene = new Scene(pieChartRoot);
				pieChartForm.setScene(scene);
				pieChartForm.show();
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    		
    		
    	});
    	
    }
}
