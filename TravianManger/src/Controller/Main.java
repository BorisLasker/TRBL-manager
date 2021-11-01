package Controller;

import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application {
	public static Connection con=Logic.DBConnector.createConnection();
	@Override
	public void start(Stage primaryStage) {
		try {
		
			primaryStage.setResizable(true);
			Parent rootContainer = FXMLLoader.load(getClass().getResource("/fxml/signIn.fxml"));
			Scene scene = new Scene(rootContainer,601.6, 428.2);
			
			primaryStage.setTitle("TRBL 1.0");

			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
