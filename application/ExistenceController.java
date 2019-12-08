package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ExistenceController {
    @FXML
    private Button btnOK;

    @FXML
    private void close() throws IOException {
        Stage oldStage = (Stage) btnOK.getScene().getWindow();
        oldStage.close();
        Parent root2 = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Profiles");
        stage.setScene(new Scene(root2));
        stage.show();
    }
}
