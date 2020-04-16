package basic.pagination;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TablePaginationTest extends Application {

	private int rowsPerPage = 15; // 한 화면에 보여줄 데이터(레코드) 수
	private TableView<Sample> table;
	private List<Sample> data;
	
	private List<Sample> createData() {
		List<Sample> data = new ArrayList<Sample>();
		for(int i = 0; i < 123; i++) {
			data.add(new Sample(i, "이름-" + i, "주소-" + i));
		}
		return data;
	}
	
	// 생성자
	public TablePaginationTest() {
		data = createData();
	}



	@Override
	public void start(Stage primaryStage) {
		table = new TableView<Sample>();
		
		TableColumn<Sample, Integer> idCol = new TableColumn<Sample, Integer>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		idCol.setPrefWidth(80);
		
		TableColumn<Sample, String> nameCol = new TableColumn<Sample, String>("이름");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameCol.setPrefWidth(150);
		
		TableColumn<Sample, String> addrCol = new TableColumn<Sample, String>("주소");
		addrCol.setCellValueFactory(new PropertyValueFactory<>("addr"));
		addrCol.setPrefWidth(150);
		
		table.getColumns().addAll(idCol, nameCol, addrCol);
		
		// Pagination객체 생성 및 초기화
		
		int totalPageCount = (int)Math.ceil((double)data.size()/ rowsPerPage);
		
		// 방법1 ==> 생성할 때는 기본 생성자로 생성하고 setPageCount()메서드로 '전체페이지수'를 설정하고
		//			setCurrentPageIndex()메서드로 '처음 선택될 페이지 index'를 설정한다.
//		Pagination pagination = new Pagination();
//		pagination.setPageCount(totalPageCount);
//		pagination.setCurrentPageIndex(0);
		
		// 방법2 ==> 생성할 때는 '전체 페이지수'만 넣어서 생성한 후 '처음 선택될 페이지 index'는 
		//			'setCurrentPageIndex() 메서드로 설정한다.
//		Pagination pagination = new Pagination(totalPageCount);
//		pagination.setCurrentPageIndex(0);
		
		// 방법3 ==> 초기값으로 '전체페이지수'와 '처음선택될 페이지 index'를 넣어준다.
		Pagination pagination = new Pagination(totalPageCount, 0);
		
		// 한 화면에 보여줄 최대 페이지 수 설정(default: 10)
		pagination.setMaxPageIndicatorCount(15);
		
		changeTableView(0);
		
		// Pagination의 페이지 번호를 변경했을 때 이벤트 처리
		pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				// 현재 선택한 페이지 번호의 index값을 넣어서 changeTableView()메서드를 호출한다.
				changeTableView(newValue.intValue()); 
			}
		});
		
		
		
		BorderPane root = new BorderPane();
		root.setCenter(table);
		root.setBottom(pagination);
		
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pagination 연습");
		primaryStage.show();
		
		
		
		
	}
	
	// Pagination에서 선택한 페이지의 index값을 매개변수로 받아서
	// 그 index번째에 맞는 데이터를 가져와 TableView에 다시 셋팅하는 메서드
	// ex) index가 3일 때 ==> 시작(15)
	public void changeTableView(int index) {
		int fromIndex = index * rowsPerPage; // 시작위치
//		int toIndex = (index + 1) * rowsPerPage; // 종료위치
		int toIndex = Math.min(fromIndex + rowsPerPage, data.size()); // 종료위치
		
//		data.subList(fromIndex, toIndex); // 시작위치부터 종료위치 이전까지
		table.setItems(
			FXCollections.observableArrayList(
				data.subList(fromIndex, toIndex)
			)
		);
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	// TableView에 출력된 데이터를 저장할 Class선언
	public class Sample {
		private int id;
		private String name;
		private String addr;
		
		public Sample() { }

		public Sample(int id, String name, String addr) {
			this.id = id;
			this.name = name;
			this.addr = addr;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
		}			
	}
}
