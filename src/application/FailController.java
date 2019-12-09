package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FailController {
	/**
	 * Button to take you back to the menu
	 */
	@FXML
	private Button btnMenu;
	/**
	 * Button to take you try the game again
	 */
	@FXML
	private Button btnAgain;
	
/**
 * Opens the main menu
 * @throws IOException
 */
	@FXML
 	private void openMainMenu() throws IOException {
     	Stage oldStage = (Stage) btnMenu.getScene().getWindow();
     	oldStage.close();
 		Parent root2 = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
 		Stage stage = new Stage();
 		stage.setTitle("Main Menu");
 		stage.setScene(new Scene(root2));
 		
 		stage.show();  
 	}
 /**
 * Opens the main menu
 * @throws IOException
 */
	@FXML
 	 private void tryAgain() throws IOException {
 	     	Stage oldStage = (Stage) btnMenu.getScene().getWindow();
 	     	oldStage.close();
 	 		Parent root2 = FXMLLoader.load(getClass().getResource("gameScreen.fxml"));
 	 		Stage stage = new Stage();
 	 		stage.setTitle("Level 1");
 	 		stage.setScene(new Scene(root2));
 	 		
 	 		stage.show();  
 	 	}
}
