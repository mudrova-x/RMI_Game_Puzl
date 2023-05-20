package client;

import java.io.IOException;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sharedInterface.GameConfiguration;
import sharedInterface.Rules;
import javafx.scene.Node;

public class SettingsController implements Initializable{

	private Stage stage;
	private Scene scene;
	private Parent root;
//	private Integer [] levelVariants = {1,2,3};
	private String [] levelVariants = {"начальный","средний","сложный"};
	private String [] themeVariants = {"абстракция","портреты","цветы"};
	
	@FXML
	private ChoiceBox<String> levelChoice;
	@FXML
	private ChoiceBox<String> themeChoice;
	
	public void switchSettingsToField(ActionEvent event) throws IOException {
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Field.fxml"));
		root=loader.load();
		
		FieldController fieldController = loader.getController();
		
		if (levelChoice.getValue()!=null && themeChoice.getValue()!=null)
		{
			try {

				Registry registry = LocateRegistry.getRegistry(1099);
				Rules rulesClient = (Rules)registry.lookup("gameRules");
				System.out.println(rulesClient.test());

				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
//				System.out.println(stage);
				GameConfiguration gameConfiguration = new GameConfiguration();
				gameConfiguration.setStartSettings( convertTheme(themeChoice.getValue()), convertLevel(levelChoice.getValue()));
				
				fieldController.getSettings(gameConfiguration, stage, rulesClient);

				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				
			}
			catch (Exception e) {
				System.err.println("Ошибка на стороне клиента: " + e.toString());
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		System.out.println("initialize settings form");

		themeChoice.getItems().addAll(themeVariants);
		themeChoice.setOnAction(this::setTheme);
		
		levelChoice.getItems().addAll(levelVariants);
		levelChoice.setOnAction(this::setLevel);
	}
	
	private void setLevel(ActionEvent event) {
		String val = levelChoice.getValue();
		System.out.println("setLevel = "+val);
	}
	
	private void setTheme(ActionEvent event) {
		String val = themeChoice.getValue();
		System.out.println("setTheme = "+val);
	}
	
	private String convertTheme(String theme) {
		return (theme=="абстракция") ? "abstract" : ((theme=="портреты") ? "people" : "flowers");
	}
	
	private int convertLevel(String level) {
		return (level=="начальный") ? 1 : ((level=="средний") ? 2 : 3);
	}
}
