package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class profController {
    @FXML
    private Button btnBack;    
    
    private void initialize() {

    }
    
    public void fillProfile() throws IOException {
		/*fills labels once a book is selected from booksLst
		bookImage.setImage(Book.getImage());
		bookTitleLbl.setText("Title - " + Book.getTitle());
		bookAuthorLbl.setText("Author - " + Book.getAuthor());
		bookPubLbl.setText("Publisher - " + Book.getPublisher());
		bookGenreLbl.setText("Genre - " + Book.getGenre());
		bookLangLbl.setText("Language - " + Book.getLanguage());
		bookISBNLbl.setText("ISBN - " + Book.getISBN());*/
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
