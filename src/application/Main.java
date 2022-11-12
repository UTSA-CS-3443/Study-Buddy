package application;
	
import application.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public static Stage stage;
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./view/loginView.fxml"));
			LoginController controller = new LoginController();
			loader.setController(controller);
			
			BorderPane layout = (BorderPane) loader.load();
			
			Scene scene = new Scene(layout);
			stage.setScene(scene);
			stage.setTitle("Welcome");
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
