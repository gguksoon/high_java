package basic.student.main;

import basic.student.vo.StudentVO;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BarChartForm extends Pane {
	private ObservableList<StudentVO> dataList;
	
	// 생성자
	public BarChartForm(ObservableList<StudentVO> dataList) {
		this.dataList = dataList;
		
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		
		yAxis.setAutoRanging(false);
		yAxis.setTickUnit(5);
		
		BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
		
		// 국어
		XYChart.Series<String, Number> serKor = new XYChart.Series<String, Number>();
		serKor.setName("국어");
		for(StudentVO stdVo : dataList) {
			serKor.getData().add(
				new Data<String, Number>(stdVo.getStd_name(), stdVo.getStd_kor()));
		}
		
		// 영어
		XYChart.Series<String, Number> serEng = new XYChart.Series<String, Number>();
		serEng.setName("영어");
		for(StudentVO stdVo : dataList) {
			serEng.getData().add(
				new Data<String, Number>(stdVo.getStd_name(), stdVo.getStd_eng()));
		}
		
		// 수학
		XYChart.Series<String, Number> serMat = new XYChart.Series<String, Number>();
		serMat.setName("수학");
		for(StudentVO stdVo : dataList) {
			serMat.getData().add(
				new Data<String, Number>(stdVo.getStd_name(), stdVo.getStd_mat()));
		}
		
		// 만든 Series들을 BarChart에 추가하기
		barChart.getData().addAll(serKor, serEng, serMat);
		
		Button btnClose = new Button("닫 기");
		btnClose.setOnAction(e->{
			Stage cStage = (Stage) btnClose.getScene().getWindow();
			cStage.close();
		});
		
		VBox vbox = new VBox(20);
		vbox.setPadding(new Insets(20));
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(barChart, btnClose);
		
		this.getChildren().add(vbox);
		
	}
}







