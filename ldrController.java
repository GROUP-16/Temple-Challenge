package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class ldrController {

    @FXML
    private Button btnBack;
    private void initialize() {
    	ObservableList<String> leaderboardList = listView.getItems();
    	try (BufferedReader reader = new BufferedReader(new FileReader(new File("leaderboardvalues.txt")))) {
            String leaderboardToRead;
            while ((leaderboardToRead = reader.readLine()) != null)
            	leaderboardlist.add(leaderboardToRead);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
	private void openMainMenu() throws IOException {
    	Stage oldStage = (Stage) btnBack.getScene().getWindow();
    	oldStage.close();
		Parent root2 = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Main Menu");
		stage.setScene(new Scene(root2));

		stage.show();
	}


   }


