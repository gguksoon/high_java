package basic.menu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		
		MenuBar menuBar = new MenuBar();
		// 창의 너비의 변화에 맞춰 메뉴바의 너비도 같이 변화되도록 설정
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		root.setTop(menuBar);
		
		// File 메뉴 -- New, save, exit
		Menu fileMenu = new Menu("File");
		MenuItem newMenuItem = new MenuItem("New");
		MenuItem saveMenuItem = new MenuItem("Save");
		MenuItem exitMenuItem = new MenuItem("Exit");
		// 메뉴를 선택했을 때 이벤트 처리
		exitMenuItem.setOnAction(e->{
			//primaryStage.close();
			Platform.exit();
		});
		
		
		// Menu에 MenuItem을 추가한다.
		fileMenu.getItems().addAll(
				newMenuItem, saveMenuItem, new SeparatorMenuItem() ,exitMenuItem);
		//-------------------------------------
		
		Menu webMenu = new Menu("Web");
		CheckMenuItem htmlMenuItem = new CheckMenuItem("HTML");
		CheckMenuItem cssMenuItem = new CheckMenuItem("CSS");
		CheckMenuItem scriptMenuItem = new CheckMenuItem("JavaScript");
		
		scriptMenuItem.setSelected(true);
		
		webMenu.getItems().addAll(htmlMenuItem, cssMenuItem, scriptMenuItem);
		
		//-------------------------------------
		Menu sqlMenu = new Menu("SQL");
		
		ToggleGroup tg = new ToggleGroup();
		RadioMenuItem oracleMenuItem = new RadioMenuItem("Oracle");
		oracleMenuItem.setToggleGroup(tg);
		
		RadioMenuItem mysqlMenuItem = new RadioMenuItem("MySql");
		mysqlMenuItem.setToggleGroup(tg);
		
		RadioMenuItem mssqlMenuItem = new RadioMenuItem("MS-Sql");
		mssqlMenuItem.setToggleGroup(tg);
		
		sqlMenu.getItems().addAll(oracleMenuItem, mysqlMenuItem, mssqlMenuItem);
		
		Menu tutorialMenu = new Menu("Tutorial");
		tutorialMenu.getItems().addAll(
			new CheckMenuItem("Java초급"),
			new CheckMenuItem("Java고급"),
			new CheckMenuItem("오라클")
		);
		
		sqlMenu.getItems().addAll(new SeparatorMenuItem(), tutorialMenu);
		
		// MenuBar에 Menu를 추가한다.
		menuBar.getMenus().addAll(fileMenu, webMenu, sqlMenu);
		
		Scene scene = new Scene(root, 300, 250);
		primaryStage.setTitle("메뉴 만들기 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
		
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
