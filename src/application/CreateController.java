package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CreateController {

	@FXML
	private TextArea txtUser;

	@FXML
    private Button btnBack;
    
    @FXML
	private void openProf() throws IOException {
    	Stage oldStage = (Stage) btnBack.getScene().getWindow();
    	oldStage.close();
		Parent root2 = FXMLLoader.load(getClass().getResource("Profile.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Profiles");
		stage.setScene(new Scene(root2));
		stage.show();
	}
    
    @FXML
    private void createProf() throws IOException {
		//name.setPromptText("Enter your first name.");
		//name.setPrefColumnCount(10);

		Profile.putProfile(txtUser.getText());
		Stage oldStage = (Stage) btnBack.getScene().getWindow();
		oldStage.close();
		Parent root2 = FXMLLoader.load(getClass().getResource("Profile.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Profiles");
		stage.setScene(new Scene(root2));

		stage.show();
	}
    
}
