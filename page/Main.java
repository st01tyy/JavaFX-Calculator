package page;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane mainpane=new MainPane();
		Scene scene=new Scene(mainpane,mainpane.getPrefWidth(),mainpane.getPrefHeight());
		primaryStage.setScene(scene);
		primaryStage.setTitle("¼òÒ×¼ÆËãÆ÷");
		primaryStage.show();
	}

}
