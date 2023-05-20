package client;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
//			System.out.println(primaryStage);
			Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.setTitle("Курсовая работа Мудровой К.Р. гр. 6415: Игра \"Пазл\" ");
			primaryStage.show();
//			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//		          public void handle(WindowEvent we) {
//		              System.out.println("Stage is closing");
//		              Platform.exit();
//		          }
//		    });        

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	 @Override
	    public void stop() throws InterruptedException {
	        System.out.println("Stop called: try to let background threads complete...");
	        System.out.println("Stage is closing");
            Platform.exit();
            System.exit(0); 
	    }

}
