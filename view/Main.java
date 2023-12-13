package view;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		HomeView lv= new HomeView();
		//ManagerView lv= new ManagerView();
		primaryStage.setScene(lv.showView(primaryStage));
		
		primaryStage.show();
		primaryStage.setResizable(false);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
