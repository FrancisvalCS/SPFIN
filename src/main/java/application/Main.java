package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static Scene mainScene;
	private static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			Pane pane = loader.load();
			
			Main.primaryStage = primaryStage;
			
		    mainScene = new Scene(pane);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("GPFIN");
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}


	public static void closeMainScene() {
		primaryStage.close();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
