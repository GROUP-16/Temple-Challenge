package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HomeController {
    @FXML
    private TextArea txtMsg;
    @FXML
    private Button btnStart;

    @FXML
    private void initialize() {
        try {
        	txtMsg.setText(application.Motd.getMessage("http://cswebcat.swan.ac.uk/message?solution=" + Motd.getPuzzle()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
	private void openMainMenu() throws IOException {
    	Stage oldStage = (Stage) btnStart.getScene().getWindow();
    	oldStage.close();
		Parent root2 = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Main Menu");
		stage.setScene(new Scene(root2));
		
		stage.show();  
	}
}
