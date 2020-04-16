package basic.chart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class BarChartTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		// 축 설정
		CategoryAxis xAxis = new CategoryAxis(); // X축
		NumberAxis yAxis = new NumberAxis(); 	 // X축
		
		// BarChart객체 생성
		BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
		
		bc.setTitle("나라별 데이터");
		xAxis.setLabel("나라명");
		yAxis.setLabel("데이터값");
		
		// 각 막대바는 Series객체로 구성한다.
		XYChart.Series<String, Number> ser1 = new XYChart.Series<String, Number>();
		ser1.setName("2017");
		ser1.getData().add(new XYChart.Data<String, Number>("한국", 100));
		ser1.getData().add(new XYChart.Data<String, Number>("미국", 50));
		ser1.getData().add(new XYChart.Data<String, Number>("중국", 70));
		ser1.getData().add(new XYChart.Data<String, Number>("영국", 60));
		ser1.getData().add(new XYChart.Data<String, Number>("호주", 90));
		
		XYChart.Series<String, Number> ser2 = new XYChart.Series<String, Number>();
		ser2.setName("2018");
		ser2.getData().add(new XYChart.Data<String, Number>("한국", 200));
		ser2.getData().add(new XYChart.Data<String, Number>("미국", 150));
		ser2.getData().add(new XYChart.Data<String, Number>("중국", 170));
		ser2.getData().add(new XYChart.Data<String, Number>("영국", 160));
		ser2.getData().add(new XYChart.Data<String, Number>("호주", 190));
		
		XYChart.Series<String, Number> ser3 = new XYChart.Series<String, Number>();
		ser3.setName("2019");
		ser3.getData().add(new XYChart.Data<String, Number>("한국", 150));
		ser3.getData().add(new XYChart.Data<String, Number>("미국", 100));
		ser3.getData().add(new XYChart.Data<String, Number>("중국", 120));
		ser3.getData().add(new XYChart.Data<String, Number>("영국", 110));
		ser3.getData().add(new XYChart.Data<String, Number>("호주", 140));
		
		// 생성된 Series들을 BarChart에 설정한다.
		bc.getData().addAll(ser1, ser2, ser3);
		
		Scene scene = new Scene(bc, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("");
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
