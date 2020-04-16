package basic.chart;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class PieChartTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		ObservableList<PieChart.Data> pieData = 
			FXCollections.observableArrayList(
				new PieChart.Data("에이어퍼얼", 100),	
				new PieChart.Data("핏ㅊ치", 80),	
				new PieChart.Data("어륀지", 40),	
				new PieChart.Data("브어내이너", 90),	
				new PieChart.Data("미앵고", 10),
				new PieChart.Data("감", 50)
			);
		
		PieChart pChart = new PieChart();
		pChart.setData(pieData);
		
		pChart.setTitle("과일별 재고량");
		pChart.setLabelLineLength(30); // 선 길이
		pChart.setLegendSide(Side.LEFT); // 범례 위치
		
		
		Scene scene = new Scene(pChart, 800, 600);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("PieChart 연습");
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
