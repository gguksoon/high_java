package basic.student.main;

import java.net.URL;
import java.util.ResourceBundle;

import basic.student.vo.StudentVO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StudentPieChartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClose;

    @FXML
    private PieChart stdPieChart;

    // 부모창의 TableView에서 클릭한 데이터를 저장할 변수
    private StudentVO stdVo;
    
	public void setStdVo(StudentVO stdVo) {
		this.stdVo = stdVo;
		
		// 셋팅한 데이터를 이용하여 PieChart를 그려준다.
		stdPieChart.setTitle(stdVo.getStd_name() + " 학생의 성적 분포");
		stdPieChart.setData(FXCollections.observableArrayList(
			new PieChart.Data("국어", stdVo.getStd_kor()),
			new PieChart.Data("영어", stdVo.getStd_eng()),
			new PieChart.Data("수학", stdVo.getStd_mat())
		));
		
	}

	@FXML
    void btnCloseClick(ActionEvent event) {
    	Stage cStage = (Stage) btnClose.getScene().getWindow();
    	cStage.close();
    }

    @FXML
    void initialize() {

    }
}
