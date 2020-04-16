package basic.mvc.main;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import basic.control.TableTest.Member;
import basic.mvc.service.IMemberService;
import basic.mvc.service.MemberServiceImpl;
import basic.mvc.vo.MemberVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class FxmlMemberController {

	private IMemberService service;
	private int buttonState = 0; // 1: add, 2: edit
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField memId;

    @FXML
    private TextField memName;

    @FXML
    private TextField memTel;

    @FXML
    private TextField memAddr;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnCancel;

    @FXML
    private TableView<MemberVO> table;

    @FXML
    private TableColumn<MemberVO, String> tableId;

    @FXML
    private TableColumn<MemberVO, String> tableName;

    @FXML
    private TableColumn<MemberVO, String> tableTel;

    @FXML
    private TableColumn<MemberVO, String> tableAddr;

    @FXML
    void initialize() {
    	service = MemberServiceImpl.getInstance();
    	
    	printTable(); // 테이블 출력
    	
    	tfSwitch();
    	
    	btnCheck.setDisable(true);
    	btnCancel.setDisable(true);
    	
    }
    
    @FXML
    void memAdd(ActionEvent event) {
    	btnSwitch();
    	tfClear();
    	tfSwitch();
    	buttonState = 1;
    }

    @FXML
    void memEdit(ActionEvent event) {
    	btnSwitch();
    	tfSwitch();
    	memId.setEditable(!memId.isEditable());
    	buttonState = 2;
    }
    
    @FXML
    void memDelete(ActionEvent event) {
    	if(table.getSelectionModel().isEmpty()) {
			alert("항목을 선택하세요.");
			return;
		}
    	
    	ButtonType btnType = confirm("정말로 삭제하시겠습니까?");
		if(btnType == ButtonType.OK) {
			MemberVO mem = table.getSelectionModel().getSelectedItem();
			service.deleteMember(mem.getMem_id());
			info(mem.getMem_name() + "씨의 정보가 삭제되었습니다.");
		}
		printTable();
    	
    }

    
    @FXML
    void memCheck(ActionEvent event) {
    	if(buttonState == 1) { // 추가
    		
    		if(memId.getText().isEmpty() || memName.getText().isEmpty() ||
    		   memTel.getText().isEmpty()|| memAddr.getText().isEmpty() ) {
    			alert("빈 항목이 있습니다.");
    			return;
    		}
    		
    		MemberVO mem = new MemberVO(memId.getText(), memName.getText(),
    				memTel.getText(), memAddr.getText());
			
    		service.insertMember(mem);
    		info(memName.getText() + "씨의 정보가 추가되었습니다.");
    		tfClear();
    		btnSwitch();
        	tfSwitch();
    		printTable();
    	} else if(buttonState == 2) { // 수정
    		
    		if(table.getSelectionModel().isEmpty()) {
				alert("수정할 항목을 선택한 후 사용하세요.");
				return;
			}
    		
    		if(memId.getText().isEmpty() || memName.getText().isEmpty() ||
    	    		   memTel.getText().isEmpty()|| memAddr.getText().isEmpty() ) {
    	    			alert("빈 항목이 있습니다.");
    	    			return;
    	    }
    		
    		int index = table.getSelectionModel().getSelectedIndex();
			
    		HashMap<String, String> params = new HashMap<String, String>();
    		params.put("mem_id", memId.getText());
    		params.put("mem_name", memName.getText());
    		params.put("mem_tel", memTel.getText());
    		params.put("mem_addr", memAddr.getText());
    		service.updateMember(params);
    		info(memName.getText() + "씨의 정보가 수정되었습니다.");
    		tfClear();
    		btnSwitch();
        	tfSwitch();
    		printTable();
    		memId.setEditable(!memId.isEditable());
    	} 
    }
    
    @FXML
    void memCancel(ActionEvent event) {
    	tfSwitch();
    	btnSwitch();
    	tfClear();
    	if(buttonState == 2)
    		memId.setEditable(!memId.isEditable());
    }
    
    @FXML
    void tableClick(MouseEvent event) {
    	MemberVO mem = table.getSelectionModel().getSelectedItem();
		if(mem != null) {
			memId.setText(mem.getMem_id());
			memName.setText(mem.getMem_name());
			memTel.setText(mem.getMem_tel());
			memAddr.setText(mem.getMem_addr());
		}
    }
    
    
    public void info(String msg) {
		Alert infor = new Alert(AlertType.INFORMATION);
		infor.setTitle("정보");
		infor.setHeaderText(msg);
		
		infor.showAndWait();
	}
    
    public void alert(String msg) {
		Alert warning = new Alert(AlertType.WARNING);
		warning.setTitle("경고");
		warning.setHeaderText(msg);
		
		warning.showAndWait();
	}
	
	public ButtonType confirm(String msg) {
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("확인");
		confirm.setHeaderText(msg);
		
		return confirm.showAndWait().get();
	}
	
    // textfield를 지우는 메서드
    void tfClear() {
    	memId.clear();
    	memName.clear();
    	memTel.clear();
    	memAddr.clear();
    }
    
    // textfield상태를 반대로 뒤집는 메서드
    void tfSwitch() {
		memId.setEditable(!memId.isEditable());
		memName.setEditable(!memName.isEditable());
		memTel.setEditable(!memTel.isEditable());
		memAddr.setEditable(!memAddr.isEditable());
    }
    
    // 버튼의 disable상태를 반대로 뒤집는 메서드
    void btnSwitch() {
    	btnAdd.setDisable(!btnAdd.isDisable());
        btnEdit.setDisable(!btnEdit.isDisable());
        btnDel.setDisable(!btnDel.isDisable());
        btnCheck.setDisable(!btnCheck.isDisable());
        btnCancel.setDisable(!btnCancel.isDisable());
    }
    
    void printTable() {
    	ObservableList<MemberVO> data =
    			FXCollections.observableArrayList(service.getAllMember());
	    
	    tableId.setCellValueFactory(
				new PropertyValueFactory<MemberVO, String>("mem_id"));
		tableName.setCellValueFactory(
				new PropertyValueFactory<MemberVO, String>("mem_name"));
		tableTel.setCellValueFactory(
				new PropertyValueFactory<MemberVO, String>("mem_tel"));
		tableAddr.setCellValueFactory(
				new PropertyValueFactory<MemberVO,String>("mem_addr"));

		table.setItems(data);
    }
}
