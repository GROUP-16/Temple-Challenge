package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MenuController {
	
    @FXML
    private Button btnProf;    
    @FXML
    private Button btnLdr;    
    @FXML
    private Button btnTheme;    
    @FXML
    private Button btnBack;
    
    @FXML
	private void openProf() throws IOException {
    	Stage oldStage = (Stage) btnProf.getScene().getWindow();
    	oldStage.close();
		Parent root2 = FXMLLoader.load(getClass().getResource("Profile.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Profiles");
		stage.setScene(new Scene(root2));
		
		stage.show();  
	}
    @FXML
	private void openLdr() throws IOException {
    	Stage oldStage = (Stage) btnLdr.getScene().getWindow();
    	oldStage.close();
		Parent root2 = FXMLLoader.load(getClass().getResource("leaderboards.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Leaderboard");
		stage.setScene(new Scene(root2));
		
		stage.show();  
	}
    @FXML
	private void openTheme() throws IOException {
    	Stage oldStage = (Stage) btnTheme.getScene().getWindow();
    	oldStage.close();
		Parent root2 = FXMLLoader.load(getClass().getResource("Theme.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Themes");
		stage.setScene(new Scene(root2));
		
		stage.show();  
	}
    @FXML
	private void openStart() throws IOException {
    	Stage oldStage = (Stage) btnBack.getScene().getWindow();
    	oldStage.close();
		Parent root2 = FXMLLoader.load(getClass().getResource("Home.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Start");
		stage.setScene(new Scene(root2));
		
		stage.show();  
	}
}
