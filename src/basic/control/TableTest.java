package basic.control;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TableTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		// [좌측(테이블뷰)]==================================================================

		// 컨트롤에서 사용할 리스트들은 대부분 ObservableList
		
		// 방법 1
//		List<Member> memList = new ArrayList<Member>();
//		memList.add(new Member("홍길동", "gildong", 33, "010-1111-1111", "대전"));
//		memList.add(new Member("홍길서", "gildseo", 43, "010-2222-2222", "서울"));
//		memList.add(new Member("홍길남", "gildnam", 23, "010-3333-3333", "제주"));
//		memList.add(new Member("홍길북", "gildbuk", 53, "010-4444-4444", "원주"));
//		ObservableList<Member> data = FXCollections.observableArrayList(memList);
		
		// 방법2
		ObservableList<Member> data =
			FXCollections.observableArrayList(
				new Member("홍길동", "gildong", 33, "010-1111-1111", "대전"),
				new Member("홍길서", "gildseo", 43, "010-2222-2222", "서울"),
				new Member("홍길남", "gildnam", 23, "010-3333-3333", "제주"),
				new Member("홍길북", "gildbuk", 53, "010-4444-4444", "원주")
			);
		
		
		
		
		
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(15));
		
		
		
		
		
		// TableView 객체 생성 및 데이터 셋팅
//		TableView<Member> table = new TableView<Member>();		// 방법1
//		table.setItems(data);

		TableView<Member> table = new TableView<Member>(data);	// 방법2
		
		
		
		
		
		// TableView 컬럼 설정
		TableColumn<Member, String> nameCol = new TableColumn<Member, String>("이  름");
		
		TableColumn<Member, String> korNameCol = new TableColumn<Member, String>("한글"); 
		korNameCol.setCellValueFactory(
				new PropertyValueFactory<Member, String>("korName")); // 열에 넣을 변수명 기입

		TableColumn<Member, String> engNameCol = new TableColumn<Member, String>("영문"); 
		engNameCol.setCellValueFactory(
				new PropertyValueFactory<Member, String>("engName")); // 열에 넣을 변수명 기입
		
		nameCol.getColumns().addAll(korNameCol, engNameCol); // nameCol안에 kor와 eng를 넣기
		
		TableColumn<Member, Integer> ageCol = new TableColumn<Member, Integer>("나  이");
		ageCol.setCellValueFactory(
				new PropertyValueFactory<Member, Integer>("age"));	  // 열에 넣을 변수명 기입
		
		TableColumn<Member, String> telCol = new TableColumn<Member, String>("전화번호"); 
		telCol.setCellValueFactory(
				new PropertyValueFactory<Member, String>("tel")); // 열에 넣을 변수명 기입
		
		TableColumn<Member, String> addrCol = new TableColumn<Member, String>("주  소"); 
		addrCol.setCellValueFactory(
				new PropertyValueFactory<Member, String>("addr")); // 열에 넣을 변수명 기입
		
		
		
		
		
		// 생성된 컬럼들을 TableView에 등록
//		table.getColumns().addAll(nameCol, ageCol, telCol, addrCol); // 방법1
		table.getColumns().setAll(nameCol, ageCol, telCol, addrCol); // 방법2
		
		
		
		
		
		// [하단부(텍스트필드)]===============================================================
		
		GridPane grid = new GridPane();
		Text txt1 = new Text("한글이름");
		Text txt2 = new Text("영문이름");
		Text txt3 = new Text("나  이");
		Text txt4 = new Text("전화번호");
		Text txt5 = new Text("주  소");
		
		TextField tfKorName = new TextField();
		TextField tfEngName = new TextField();
		TextField tfAge = new TextField();
		TextField tfTel = new TextField();
		TextField tfAddr = new TextField();
		
		grid.add(txt1, 1, 1);
		grid.add(txt2, 2, 1);
		grid.add(txt3, 3, 1);
		grid.add(txt4, 4, 1);
		grid.add(txt5, 5, 1);
		
		grid.add(tfKorName, 1, 2);
		grid.add(tfEngName, 2, 2);
		grid.add(tfAge, 3, 2);
		grid.add(tfTel, 4, 2);
		grid.add(tfAddr, 5, 2);
		
		grid.setVgap(10);
		grid.setHgap(5);
		
		
		

		
		// [우측(버튼)]====================================================================
				
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(10));
		
		Button btnAdd = new Button("추  가");
		
		btnAdd.setOnAction(e -> { // 추가버튼의 기능
			if(tfKorName.getText().isEmpty() || tfEngName.getText().isEmpty() ||
			   tfAge.getText().isEmpty()     || tfTel.getText().isEmpty()     ||
			   tfAddr.getText().isEmpty() ) {
				alert("빈 항목이 있습니다.");
				return;
			}
			
			Member mem = new Member(tfKorName.getText(), tfEngName.getText(),
					Integer.parseInt(tfAge.getText()), tfTel.getText(), tfAddr.getText());
			
			// TableView에 데이터 추가
			data.add(mem);
			info(tfKorName.getText() + "씨의 정보가 추가되었습니다.");
			tfKorName.clear();
			tfEngName.clear();
			tfAge.clear();
			tfTel.clear();
			tfAddr.clear();
		});
		
		
		
		Button btnEdit = new Button("수  정");
		
		btnEdit.setOnAction(e -> {
			// 선택한 항목이 있는지 검사
			if(table.getSelectionModel().isEmpty()) {
				alert("수정할 항목을 선택한 후 사용하세요.");
				return;
			}
			
			if(tfKorName.getText().isEmpty() || tfEngName.getText().isEmpty() ||
			   tfAge.getText().isEmpty()     || tfTel.getText().isEmpty()     ||
			   tfAddr.getText().isEmpty() ) {
				alert("빈 항목이 있습니다.");
				return;
			}
			
			int index = table.getSelectionModel().getSelectedIndex();
			
			data.set(index, new Member(tfKorName.getText(), tfEngName.getText(), 
					Integer.parseInt(tfAge.getText()), tfTel.getText(), tfAddr.getText()));
			
			info(tfKorName.getText() + "씨의 정보가 수정되었습니다.");
			tfKorName.clear();
			tfEngName.clear();
			tfAge.clear();
			tfTel.clear();
			tfAddr.clear();
		});
		
		
		
		Button btnDel = new Button("삭  제");
		
		btnDel.setOnAction(e -> {
			if(table.getSelectionModel().isEmpty()) {
				alert("삭제 항목을 선택한 후 사용하세요.");
				return;
			}
			
			ButtonType btnType = confirm("정말로 삭제하시겠습니까?");
			if(btnType == ButtonType.OK) {
				data.remove(table.getSelectionModel().getSelectedIndex());
				
				info(tfKorName.getText() + "씨의 정보가 삭제되었습니다.");
				tfKorName.clear();
				tfEngName.clear();
				tfAge.clear();
				tfTel.clear();
				tfAddr.clear();
			}
			
		});
		
		
		
		// 객체.setDisable()메서드에 true값을 설정하면 해당 객체가 비활성화되고
		// 						 false값을 설정하면 활성화 된다.
		btnAdd.setDisable(true);
		
		// 객체.setEditable()메서드에 false값을 설정하면 해당 객체는 읽기 전용이 되고
		//						  true값을 설정하면 쓰기 가능
		tfKorName.setEditable(false);
		
		vbox.getChildren().addAll(btnAdd, btnEdit, btnDel);
		
		// TableView를 클릭했을 때 처리
		table.setOnMouseClicked(e -> {
			// 선택한 객체 구하기
			Member mem = table.getSelectionModel().getSelectedItem();
			if(mem != null) {
				tfKorName.setText(mem.getKorName());
				tfEngName.setText(mem.getEngName());
				tfAge.setText( String.valueOf( mem.getAge() ) );
				tfTel.setText(mem.getTel());
				tfAddr.setText(mem.getAddr());
				
			}
		});
		
		
		
		// [조합]=========================================================================
		
		root.setCenter(table);
		root.setRight(vbox);
		root.setBottom(grid);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("TableTest");
		primaryStage.show();
		
		
		
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

	public static void main(String[] args) {
		launch(args);
	}
	
	// InnerClass로 VO객체 선언
	public class Member {
		private String korName;
		private String engName;
		private int age;
		private String tel;
		private String addr;
		
		public Member() { }

		public Member(String korName, String engName, int age, String tel, String addr) {
			this.korName = korName;
			this.engName = engName;
			this.age = age;
			this.tel = tel;
			this.addr = addr;
		}

		public String getKorName() {
			return korName;
		}

		public void setKorName(String korName) {
			this.korName = korName;
		}

		public String getEngName() {
			return engName;
		}

		public void setEngName(String engName) {
			this.engName = engName;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
		}
		
	}
	
}
