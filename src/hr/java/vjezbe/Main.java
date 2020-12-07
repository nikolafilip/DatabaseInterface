package hr.java.vjezbe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main klasa programa u kojoj se inicijalizira program.
 * 
 * @author Nikola Filip - 0246082092
 * @version 7.0 - ugradeno graficko sucelje (GUI) pomocu JavaFX
 */
public class Main extends Application {

	private static Stage stage;

	@Override
	public void start(Stage primaryStage) {

		stage = primaryStage;

		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("PocetnaStranica.fxml"));
			Scene scene = new Scene(root, 600, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void setMainPage(BorderPane root) {
		Scene scene = new Scene(root, 600, 500);
		stage.setScene(scene);
		stage.show();
	}

}
