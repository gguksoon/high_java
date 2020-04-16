package zipSearch.controller;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import util.myAlert;
import zipSearch.service.IZipSearchService;
import zipSearch.vo.ZipVO;

public class ZipSearchController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbSelect;

    @FXML
    private TextField tfData;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView<ZipVO> zipTable;

    @FXML
    private TableColumn<?, ?> zipCol;

    @FXML
    private TableColumn<?, ?> sidoCol;

    @FXML
    private TableColumn<?, ?> gugunCol;

    @FXML
    private TableColumn<?, ?> dongCol;

    @FXML
    private TableColumn<?, ?> riCol;

    @FXML
    private TableColumn<?, ?> bldgCol;

    @FXML
    private TableColumn<?, ?> bunjiCol;
    
    // Service객체가 저장될 변수 선언
    private IZipSearchService service;
    
    // TableView에서 사용할 List
    private ObservableList<ZipVO> zipList = FXCollections.observableArrayList();
    
    // 검색버튼을 클릭했을 때의 이벤트 처리
    @FXML
    void zipSearch(ActionEvent event) throws RemoteException {
    	String searchData = tfData.getText();
    			
    	if(searchData.isEmpty()) {
    		myAlert.alert("검색할 값을 입력하세요.");
    		tfData.requestFocus();
    		return;
    	}
    	
    	// 콤보박스에서 선택한 항목 저장
    	String selData = cmbSelect.getValue();
    	
    	List<ZipVO> resultList = null; // 검색 결과가 저장될 List변수
    	
    	if(selData.equals("동이름")) {
    		resultList = service.getZipSearchDong(searchData);
    	} else if(selData.equals("우편번호")) {
    		resultList = service.getZipSearchCode(searchData);
    	}
    	
    	// 검색 결과를 TableView에서 사용하는 List에 넣어주기
    	zipList.clear();
    	if(resultList != null) {
    		zipList.addAll(resultList);
    	} 
    	
    }

    @FXML
    void initialize() {
    	try {
    		// RMI서버에 접속하기
    		// (매개변수는 서버의 주소, 포트번호)
			Registry reg = LocateRegistry.getRegistry("localhost", 9988);
			// RMI에 등록한 service들 중에서 사용할 service객체를 얻어온다.
    		// (매개변수는 서버의 메인에서 설정한 값임)
			service = (IZipSearchService) reg.lookup("zipService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	// 콤보박스 설정
    	cmbSelect.getItems().addAll("동이름", "우편번호"); // 값 입력
    	cmbSelect.setValue("동이름"); // 처음 나올 값
    	
    	// TableView 각 컬럼과 VO의 멤버변수를 매핑하기
    	zipCol.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
    	sidoCol.setCellValueFactory(new PropertyValueFactory<>("sido"));
    	gugunCol.setCellValueFactory(new PropertyValueFactory<>("gugun"));
    	dongCol.setCellValueFactory(new PropertyValueFactory<>("dong"));
    	riCol.setCellValueFactory(new PropertyValueFactory<>("ri"));
    	bldgCol.setCellValueFactory(new PropertyValueFactory<>("bldg"));
    	bunjiCol.setCellValueFactory(new PropertyValueFactory<>("bunji"));
    	
    	// TableView에 데이터 셋팅
    	zipTable.setItems(zipList);
    	
    }
}
