package basic.control;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ComboBoxTest2 extends Application {

	@Override
	public void start(Stage primaryStage) {
		ObservableList<MyFriend> list = FXCollections.observableArrayList(
			new MyFriend("aaa", "홍길동", "010-1111-1111", "대전"),
			new MyFriend("bbb", "이순신", "010-2222-1111", "광주"),
			new MyFriend("ccc", "성춘향", "010-3333-1111", "인천"),
			new MyFriend("ddd", "변학도", "010-4444-1111", "강릉"),
			new MyFriend("eee", "이몽룡", "010-5555-1111", "대구"),
			new MyFriend("fff", "일지매", "010-6666-1111", "포항")
		);
		
		VBox vbox = new VBox(10);
		TextArea taResult = new TextArea();
		
		ComboBox<MyFriend> combo = new ComboBox<MyFriend>();
		combo.setItems(list);
		
		// 콤보박스의 목록이 보여지는 곳의 내용 변경하기
		
		// 목록이 나타나는 부분을 Cell이라고 하고 이 부분의 원본은 변경되지 않고 화면에 보여주는 부분만 변경되게 한다.
		combo.setCellFactory(new Callback<ListView<MyFriend>, ListCell<MyFriend>>() {
			@Override
			public ListCell<MyFriend> call(ListView<MyFriend> param) {
				return new ListCell<MyFriend>() {
					@Override
					protected void updateItem(MyFriend item, boolean empty) {
						super.updateItem(item, empty);
						// 화면에 보여줄 내용(목록)을 기술한다.
						if(empty) {
							setText(null);
						} else {
							setText(item.getName() + "[" + item.getId()+ "]");
						}
					}
				};
			}
		});
		
		// 콤보박스에서 항목을 하나 선택하면 선택된 내용이 콤보박스의 버튼 영역에 나타나는데
		// 이 부분의 내용도 변경해주어야 한다. (이 부분은 buttonCell이라고 함)
		combo.setButtonCell(new ListCell<MyFriend>() {
			@Override
			protected void updateItem(MyFriend item, boolean empty) {
				super.updateItem(item, empty);
				if(empty) {
					setText(null);
				} else {
					setText(item.getName() + "[" + item.getId()+ "]");
				}
			}
		});
		
		// 콤보박스를 클릭했을 때 이벤트 처리
		combo.setOnAction(e -> {
			// 현재 선택된 데이터 구하기
//			MyFriend selData = combo.getSelectionModel().getSelectedItem(); // 방법 1
			MyFriend selData = combo.getValue(); // 방법2
			
			taResult.setText("ID: " + selData.getId());
			taResult.appendText("\n이름: " + selData.getName());
			taResult.appendText("\n전화번호: " + selData.getTel());
			taResult.appendText("\n주소: " + selData.getAddr());
		});
		
		vbox.getChildren().addAll(combo, taResult);
		
		Scene scene = new Scene(vbox);
		primaryStage.setScene(scene);
		primaryStage.setTitle("콤보박스 연습2");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	// InnerClass로 VO객체 선언
	class MyFriend {
		private String id;
		private String name;
		private String tel;
		private String addr;
		
		// 기본 생성자
		public MyFriend() { }
		
		// 데이터를 세팅하는 생성자
		public MyFriend(String id, String name, String tel, String addr) {
			super();
			this.id = id;
			this.name = name;
			this.tel = tel;
			this.addr = addr;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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
