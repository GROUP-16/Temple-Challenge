package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ProfController {

    @FXML
    private Button btnBack;   
    
    @FXML
    private Button btnCreate; 
    
    @FXML
    private Button btnLoad;

    @FXML
	private Button btnDelete;

    @FXML
	private Button btnLvl1;

	@FXML
	private Button btnLvl2;

	@FXML
	private Button btnLvl3;

	@FXML
	private Button btnLvl4;

	@FXML
	private Button btnLvl5;

	@FXML
	private Button btnLvl6;
    
    @FXML
    private TableView<Profile2> lstProf;

	@FXML
	private ArrayList<Profile2> profiles;

    @FXML
    private void initialize() throws IOException {
    	TableColumn<Profile2, String> NameColumn = new TableColumn<>("Name");
    	NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

    	TableColumn<Profile2, Integer> levelColumn = new TableColumn<>("Highest Level");
    	levelColumn.setCellValueFactory(new PropertyValueFactory<>("highestLevel"));
    	

    	lstProf.getColumns().add(NameColumn);
    	lstProf.getColumns().add(levelColumn);

    	profiles = Profile.readProfiles("Profiles.txt");

    	for(Profile2 item : profiles) {
    		lstProf.getItems().add(item);
		}

        }

    @FXML
 	private void openLoad() throws IOException {
     	Profile2 selectedProfile = lstProf.getSelectionModel().getSelectedItem();

		if(selectedProfile == null) {
			Stage oldStage = (Stage) btnBack.getScene().getWindow();
			oldStage.close();
			Parent root = FXMLLoader.load(getClass().getResource("NotSelected.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Not Selected");
			stage.setScene(new Scene(root));
			stage.show();
		} else {
			Profile.loadProfile(selectedProfile.getName());
			Stage oldStage = (Stage) btnBack.getScene().getWindow();
			oldStage.close();
			Parent root2 = FXMLLoader.load(getClass().getResource("Load.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Select a Level");
			stage.setScene(new Scene(root2));
			stage.show();
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
    @FXML
 	private void openCreate() throws IOException {
     	Stage oldStage = (Stage) btnBack.getScene().getWindow();
     	oldStage.close();
 		Parent root2 = FXMLLoader.load(getClass().getResource("Create.fxml"));
 		Stage stage = new Stage();
 		stage.setTitle("Create");
 		stage.setScene(new Scene(root2));
 		
 		stage.show();  
 	}

 	@FXML
	private void deleteProfile() throws IOException {
		Profile2 selectedProfile = lstProf.getSelectionModel().getSelectedItem();
		if(selectedProfile == null) {
			Stage oldStage = (Stage) btnBack.getScene().getWindow();
			oldStage.close();
			Parent root = FXMLLoader.load(getClass().getResource("NotSelected.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Not Selected");
			stage.setScene(new Scene(root));
			stage.show();
		} else {
			Profile.deleteProfile(selectedProfile.getName());
			lstProf.getItems().clear();
			profiles = Profile.readProfiles("Profiles.txt");

			for(Profile2 item : profiles) {
				lstProf.getItems().add(item);
			}
		}
	}
 	@FXML
 	private void onPressLev1() throws IOException{
    	Stage oldStage = (Stage) btnBack.getScene().getWindow();
    	oldStage.close();
		Parent root2 = FXMLLoader.load(getClass().getResource("gameScreen.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Level 1");
		stage.setScene(new Scene(root2));
		
		stage.show();  
	}
 	@FXML
 	private void onPressLev2() throws IOException{
    	Stage oldStage = (Stage) btnBack.getScene().getWindow();
    	oldStage.close();
		Parent root2 = FXMLLoader.load(getClass().getResource("gameScreen2.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Level 2");
		stage.setScene(new Scene(root2));
		
		stage.show();  
	}
 	@FXML
 	private void onPressLev3() throws IOException{
    	Stage oldStage = (Stage) btnBack.getScene().getWindow();
    	oldStage.close();
		Parent root2 = FXMLLoader.load(getClass().getResource("gameScreen3.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Level 3");
		stage.setScene(new Scene(root2));
		
		stage.show();  
	}
 	@FXML
 	private void onPressLev4() throws IOException{
    	Stage oldStage = (Stage) btnBack.getScene().getWindow();
    	oldStage.close();
		Parent root2 = FXMLLoader.load(getClass().getResource("gameScreen4.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Level 4");
		stage.setScene(new Scene(root2));
		
		stage.show();  
	}
 	@FXML
 	private void onPressLev5() throws IOException{
    	Stage oldStage = (Stage) btnBack.getScene().getWindow();
    	oldStage.close();
		Parent root2 = FXMLLoader.load(getClass().getResource("gameScreen5.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Level 5");
		stage.setScene(new Scene(root2));
		
		stage.show();  
	}
 	@FXML
 	private void onPressLev6() throws IOException{
    	Stage oldStage = (Stage) btnBack.getScene().getWindow();
    	oldStage.close();
		Parent root2 = FXMLLoader.load(getClass().getResource("gameScreen6.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Level 6");
		stage.setScene(new Scene(root2));
		
		stage.show();  
	}
 	
	@FXML
	private void loadProfile() throws IOException {
		Profile2 selectedProfile = lstProf.getSelectionModel().getSelectedItem();
		if(selectedProfile == null) {
			Stage oldStage = (Stage) btnBack.getScene().getWindow();
			oldStage.close();
			Parent root = FXMLLoader.load(getClass().getResource("NotSelected.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Not Selected");
			stage.setScene(new Scene(root));
			stage.show();
		} else {
			if(Profile.loadProfile(selectedProfile.getName()) == false) {
				Stage oldStage = (Stage) btnBack.getScene().getWindow();
				oldStage.close();
				Parent root = FXMLLoader.load(getClass().getResource("Existence.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Existence");
				stage.setScene(new Scene(root));
				stage.show();
			} else {
				int highestLevel = selectedProfile.getHighestLevel();
				if (highestLevel == 0 || highestLevel == 1) {
					btnLvl1.setDisable(false);
					btnLvl2.setDisable(true);
					btnLvl3.setDisable(true);
					btnLvl4.setDisable(true);
					btnLvl5.setDisable(true);
					btnLvl6.setDisable(true);
				} else if (highestLevel == 2) {
					btnLvl1.setDisable(false);
					btnLvl2.setDisable(false);
					btnLvl3.setDisable(true);
					btnLvl4.setDisable(true);
					btnLvl5.setDisable(true);
					btnLvl6.setDisable(true);
				} else if (highestLevel == 3) {
					btnLvl1.setDisable(false);
					btnLvl2.setDisable(false);
					btnLvl3.setDisable(false);
					btnLvl4.setDisable(true);
					btnLvl5.setDisable(true);
					btnLvl6.setDisable(true);
				} else if (highestLevel == 4) {
					btnLvl1.setDisable(false);
					btnLvl2.setDisable(false);
					btnLvl3.setDisable(false);
					btnLvl4.setDisable(false);
					btnLvl5.setDisable(true);
					btnLvl6.setDisable(true);
				} else if (highestLevel == 5) {
					btnLvl1.setDisable(false);
					btnLvl2.setDisable(false);
					btnLvl3.setDisable(false);
					btnLvl4.setDisable(false);
					btnLvl5.setDisable(false);
					btnLvl6.setDisable(true);
				} else {
					btnLvl1.setDisable(false);
					btnLvl2.setDisable(false);
					btnLvl3.setDisable(false);
					btnLvl4.setDisable(false);
					btnLvl5.setDisable(false);
					btnLvl6.setDisable(false);
				}
			}
		}

	}

}
