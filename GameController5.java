package application;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameController5 {
	
	public Map newMap = new Map();
	char[][] allCells = null;
	Player newPlayer = null;
	Integer x = null;
	Integer y = null;
	Instant start;
	String url;
	Integer level;
    @FXML
    private ImageView tileX1Y1;
    @FXML
    private ImageView tileX2Y1;
    @FXML
    private ImageView tileX3Y1;
    @FXML
    private ImageView tileX4Y1;
    @FXML
    private ImageView tileX5Y1;
    @FXML
    private ImageView tileX1Y2;
    @FXML
    private ImageView tileX2Y2;
    @FXML
    private ImageView tileX3Y2;
    @FXML
    private ImageView tileX4Y2;
    @FXML
    private ImageView tileX5Y2;
    @FXML
    private ImageView tileX1Y3;
    @FXML
    private ImageView tileX2Y3;
    @FXML
    private ImageView tileX3Y3;
    @FXML
    private ImageView tileX4Y3;
    @FXML
    private ImageView tileX5Y3;
    @FXML
    private ImageView tileX1Y4;
    @FXML
    private ImageView tileX2Y4;
    @FXML
    private ImageView tileX3Y4;
    @FXML
    private ImageView tileX4Y4;
    @FXML
    private ImageView tileX5Y4;
    @FXML
    private ImageView tileX1Y5;
    @FXML
    private ImageView tileX2Y5;
    @FXML
    private ImageView tileX3Y5;
    @FXML
    private ImageView tileX4Y5;
    @FXML
    private ImageView tileX5Y5;
    @FXML
    private ImageView redKeys;
    @FXML
    private ImageView BlueKeys;
    @FXML
    private ImageView GreenKeys;
    @FXML
    private ImageView YellowKeys;
    @FXML
    private ImageView flippers;
    @FXML
    private ImageView fireBoots;
    @FXML
    private TextField tokenTotal;
    @FXML
    private TextField blueTotal;
    @FXML
    private TextField greenTotal;
    @FXML
    private TextField redTotal;
    @FXML
    private TextField yellowTotal;
    @FXML
    private Button btnUp;
    @FXML
    private Button btnLeft;
    @FXML
    private Button btnRight;
    @FXML
    private Button btnDown;
    @FXML
    private Label lblTime;
    @FXML
    private Button btnBack;
    
    @FXML
    private void initialize() throws IOException {
    	
    	newMap.readFile("src/application/Level5.txt");
    	allCells = (newMap.mapCharArray());
    	newPlayer = newMap.getPlayer();
    	x = newPlayer.getX();
    	y = newPlayer.getY();
    	refreshMap(x,y, newMap, allCells);
    	start = Instant.now();
    	}
    	
    	

    @FXML
 	private void openProf() throws IOException {
     	Stage oldStage = (Stage) btnBack.getScene().getWindow();
     	oldStage.close();
 		Parent root2 = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
 		Stage stage = new Stage();
 		stage.setTitle("Profile");
 		stage.setScene(new Scene(root2));
 		
 		stage.show();  
 	}
    private Image changeTile(Integer y, Integer x) {
    	
    	allCells = (newMap.mapCharArray());
    	Image imageLocation = null;
    	if (allCells[x][y] == 'F') {
    		imageLocation = new Image("models/fire.png");
    	}
    	else if (allCells[x][y] == ' ') {
    		imageLocation = new Image("models/floor.png");
    	}
    	else if (allCells[x][y] == 'W') {
    		imageLocation = new Image("models/water.png");
    	}
    	else if (allCells[x][y] == '#') {
    		imageLocation = new Image("models/wall.png");
    	}
    	else if (allCells[x][y] == 'D') {
    		Cell c = newMap.getCell(x, y);
    		Door d = (Door) c;
    		if (d.getDoorType() == DoorType.KEY_DOOR) {
    			
    			if(d.getCondition().equalsIgnoreCase("RED")) {
    	    		imageLocation = new Image("models/RedDoor.png");

    			}
    			else if(d.getCondition().equalsIgnoreCase("YELLOW")) {
    	    		imageLocation = new Image("models/YellowDoor.png");

    			}
    			else if(d.getCondition().equalsIgnoreCase("BLUE")) {
    	    		imageLocation = new Image("models/BlueDoor.png");

    			}
    			else if(d.getCondition().equalsIgnoreCase("GREEN")) {
    	    		imageLocation = new Image("models/GreenDoor.png");
    			}
    		}
    		else {
    			if(d.getCondition().equalsIgnoreCase("3")) {
	    		imageLocation = new Image("models/TokenDoor3.png");
    			}
    			else if(d.getCondition().equalsIgnoreCase("2")) {
    	    		imageLocation = new Image("models/TokenDoor2.png");
        			}
       			if(d.getCondition().equalsIgnoreCase("1")) {
    	    		imageLocation = new Image("models/TokenDoor1.png");
        			}
    		}
    	}
    	else if (allCells[x][y] == 'T') {
    		imageLocation = new Image("models/portal.png");
    	}
    	else if (allCells[x][y] == 'G') {
    		imageLocation = new Image("models/Goal.png");
    	}
    	else if (allCells[x][y] == 'I') {
    		Cell c = newMap.getCell(x, y);
    		Item i = (Item) c;
    		if(i.getItemType() == ItemType.KEY) {
    			if(i.getColour().equalsIgnoreCase("RED")) {
    	    		imageLocation = new Image("models/RedKey.png");
    			}
    			else if (i.getColour().equalsIgnoreCase("YELLOW")) {
    	    		imageLocation = new Image("models/YellowKey.png");
    			}
    			else if (i.getColour().equalsIgnoreCase("BLUE")) {
    	    		imageLocation = new Image("models/BlueKey.png");
    			}
    			else if (i.getColour().equalsIgnoreCase("GREEN")) {
    	    		imageLocation = new Image("models/GreenKey.png");
    			}
    			
    		}
    		else if (i.getItemType() == ItemType.FLIPPERS) {
	    		imageLocation = new Image("models/Flippers.png");
    		}
    		else if (i.getItemType() == ItemType.FIRE_BOOTS) {
	    		imageLocation = new Image("models/FireBoots.png");
	    		
    		}
    		else if (i.getItemType() == ItemType.TOKEN) {
	    		imageLocation = new Image("models/Token.png");
	    		
    		}
    	}
    	else if (allCells[x][y] == 'S') {
    		imageLocation = new Image("models/StraightEnemy.png");
    	}
    	else if (allCells[x][y] == 'H') {
    		imageLocation = new Image("models/WallEnemy.png");
    	}
    	else if (allCells[x][y] == '*') {
    		imageLocation = new Image("models/DumbEnemy.png");
    	}
    	else if (allCells[x][y] == 'R') {
    		imageLocation = new Image("models/SmartEnemy.png");
    	}
    	else {
    		imageLocation = new Image("models/Player.png");
    	}
    	

    	return imageLocation;
    }
    private void refreshMap(Integer y, Integer x, Map map, char[][] allCells) throws IOException {
    	if(newPlayer.isDead() == true) {

    		Parent root2 = FXMLLoader.load(getClass().getResource("GameFail.fxml"));
    		Stage stage = new Stage();
    		stage.setTitle("Game Over");
    		stage.setScene(new Scene(root2));
    		stage.show();
    	}
    	else if(newPlayer.gameWon() == true) {
    		Instant finish = Instant.now();
    		long timeElapsed = Duration.between(start, finish).toMillis();
    		
    		Stage newStage = new Stage();
    		VBox comp = new VBox();
    		Label Congrats = new Label("CONGRATULATIONS");
    		Label Time = new Label("You completed the level in " + (new SimpleDateFormat("mm:ss:SSS").format(new Date(timeElapsed))) + " seconds");
    		comp.getChildren().add(Congrats);
    		comp.getChildren().add(Time);

    		Scene stageScene = new Scene(comp, 300, 100);
    		newStage.setScene(stageScene);
    		newStage.show();}
    	else {
    	tileX1Y1.setImage(changeTile(x-2,y-2));
    	tileX2Y1.setImage(changeTile(x-1,y-2));
    	tileX3Y1.setImage(changeTile(x,y-2));
    	tileX4Y1.setImage(changeTile(x+1,y-2));
    	tileX5Y1.setImage(changeTile(x+2,y-2));
    	tileX1Y2.setImage(changeTile(x-2,y-1));
    	tileX2Y2.setImage(changeTile(x-1,y-1));
    	tileX3Y2.setImage(changeTile(x,y-1));
    	tileX4Y2.setImage(changeTile(x+1,y-1));
    	tileX5Y2.setImage(changeTile(x+2,y-1));
    	tileX1Y3.setImage(changeTile(x-2,y));
    	tileX2Y3.setImage(changeTile(x-1,y));
    	tileX3Y3.setImage(changeTile(x,y));
    	tileX4Y3.setImage(changeTile(x+1,y));
    	tileX5Y3.setImage(changeTile(x+2,y));
    	tileX1Y4.setImage(changeTile(x-2,y+1));
    	tileX2Y4.setImage(changeTile(x-1,y+1));
    	tileX3Y4.setImage(changeTile(x,y+1));
    	tileX4Y4.setImage(changeTile(x+1,y+1));
    	tileX5Y4.setImage(changeTile(x+2,y+1));
    	tileX1Y5.setImage(changeTile(x-2,y+2));
    	tileX2Y5.setImage(changeTile(x-1,y+2));
    	tileX3Y5.setImage(changeTile(x,y+2));
    	tileX4Y5.setImage(changeTile(x+1,y+2));
    	tileX5Y5.setImage(changeTile(x+2,y+2));
    	}
    	}
    @FXML
    private void moveUp() throws IOException{
    	newPlayer.move(DirectionType.up);
    	if (newPlayer.checkInventory(ItemType.FIRE_BOOTS, null) == true) {
    		Image fireboots = new Image("models/FireBoots.png");
    		fireBoots.setImage(fireboots);
    	}
    	if (newPlayer.checkInventory(ItemType.FLIPPERS, null) == true) {
    		Image flip = new Image("models/Flippers.png");
    		flippers.setImage(flip);
    	}
    	
    	tokenTotal.setText((newPlayer.totalTokens()).toString());
    	blueTotal.setText((newPlayer.totalBlue()).toString());
    	greenTotal.setText((newPlayer.totalGreen()).toString());
    	redTotal.setText((newPlayer.totalRed()).toString());
    	yellowTotal.setText((newPlayer.totalYellow()).toString());
    	x = newPlayer.getX();
    	y = newPlayer.getY();
    	
    	updateEnemy(x,y);
    	refreshMap(x,y, newMap, allCells);
    	System.out.println(newMap);

    }
    @FXML
    private void moveLeft() throws IOException {
    	newPlayer.move(DirectionType.left);
    	if (newPlayer.checkInventory(ItemType.FIRE_BOOTS, null) == true) {
    		Image fireboots = new Image("models/FireBoots.png");
    		fireBoots.setImage(fireboots);
    	}
    	if (newPlayer.checkInventory(ItemType.FLIPPERS, null) == true) {
    		Image flip = new Image("models/Flippers.png");
    		flippers.setImage(flip);
    	}
    	tokenTotal.setText((newPlayer.totalTokens()).toString());
    	blueTotal.setText((newPlayer.totalBlue()).toString());
    	greenTotal.setText((newPlayer.totalGreen()).toString());
    	redTotal.setText((newPlayer.totalRed()).toString());
    	yellowTotal.setText((newPlayer.totalYellow()).toString());
    	x = newPlayer.getX();
    	y = newPlayer.getY();
    	updateEnemy(x,y);
    	refreshMap(x,y, newMap, allCells);
    	System.out.println(newMap);

    }
    @FXML
    private void moveRight() throws IOException {
    	newPlayer.move(DirectionType.right);
    	if (newPlayer.checkInventory(ItemType.FIRE_BOOTS, null) == true) {
    		Image fireboots = new Image("models/FireBoots.png");
    		fireBoots.setImage(fireboots);
    	}
    	if (newPlayer.checkInventory(ItemType.FLIPPERS, null) == true) {
    		Image flip = new Image("models/Flippers.png");
    		flippers.setImage(flip);
    	}
    	tokenTotal.setText((newPlayer.totalTokens()).toString());
    	blueTotal.setText((newPlayer.totalBlue()).toString());
    	greenTotal.setText((newPlayer.totalGreen()).toString());
    	redTotal.setText((newPlayer.totalRed()).toString());
    	yellowTotal.setText((newPlayer.totalYellow()).toString());
    	x = newPlayer.getX();
    	y = newPlayer.getY();
    	updateEnemy(x,y);
    	refreshMap(x,y, newMap, allCells);
    	System.out.println(newMap);
    }
    
    @FXML
    private void moveDown() throws IOException {
    	newPlayer.move(DirectionType.down);
    	if (newPlayer.checkInventory(ItemType.FIRE_BOOTS, null) == true) {
    		Image fireboots = new Image("models/FireBoots.png");
    		fireBoots.setImage(fireboots);
    	}
    	if (newPlayer.checkInventory(ItemType.FLIPPERS, null) == true) {
    		Image flip = new Image("models/Flippers.png");
    		flippers.setImage(flip);
    	}
    	System.out.println(newPlayer.totalBlue());
    	System.out.println(newPlayer.totalGreen());
    	System.out.println(newPlayer.totalRed());
    	System.out.println(newPlayer.totalYellow());
    	
    	tokenTotal.setText((newPlayer.totalTokens()).toString());
    	blueTotal.setText((newPlayer.totalBlue()).toString());
    	greenTotal.setText((newPlayer.totalGreen()).toString());
    	redTotal.setText((newPlayer.totalRed()).toString());
    	yellowTotal.setText((newPlayer.totalYellow()).toString());
    	x = newPlayer.getX();
    	y = newPlayer.getY();

    	updateEnemy(x,y);
    	refreshMap(x,y, newMap, allCells);
    	
    	System.out.println(newMap);

    }
    private void updateEnemy(Integer x, Integer y) throws IOException {
    	refreshMap(x,y, newMap, allCells);
    	ArrayList<Enemy> enemies = newMap.getEnemies();
    	for (Enemy enemyX: enemies) {
    		if(enemyX.getEnemyType().equals(EnemyType.SMART)) {
    			SmartAI E1 = new SmartAI(enemyX.getXCoord(),enemyX.getYCoord(),0,0,newMap);
    			enemies.remove(enemyX);
				enemies.add(E1);
    			
    		}
    		else {
    			enemyX.move(y, x,newMap);
    		}
    	}
    	
    	for (Enemy enemyX: enemies) {
    		if(enemyX.getEnemyType().equals(EnemyType.SMART)) {
    			enemyX.move(y, x,newMap);
    		}
    		
    	}
    	
    }
    
    
}