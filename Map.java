package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList; //using for creating enemies
/**
 * This class is responsible for all operations relating to the Map Class, these operations include map creation
 * from a file and storing the map. The Map will be heavily relied upon by the other classes in the project
 * 
 * @author George Sims
 *
 *
 *
 *X represents the row 
 *Y Represents the column 
 */

public class Map {
	
	private Cell[][] cells;
	private ArrayList<Enemy> enemies; //This is a test array
	//As i create the enemies we will add them to the array
	private Player player;
	//constants for use when reading from a file
	private final char SENTINEL_CHAR = '>';
	private final char FIRE_CHAR = 'F';
	private final char FLOOR_CHAR = ' ';
	private final char WATER_CHAR = 'W';
	private final char WALL_CHAR = '#';
	private final char DOOR_CHAR = 'D';
	private final char TELEPORTER_CHAR = 'P';
	private final char GOAL_CHAR = 'G';
	private final char ITEM_CHAR = 'I';
	
	
	//constants for additional information
	private final String START_STRING = "START";
	private final String KEY_STRING = "KEY";
	private final String FIRE_BOOT_STRING = "FIRE_BOOTS";
	private final String FLIPPER_STRING = "FLIPPER";
	private final String TOKEN_STRING = "TOKEN";
	private final String ENEMY_STRING = "ENEMY";

	/**
	 * Basic constructor that initialises the map and Cell array to a default size of 50*50 
	 */
	public Map() {
		this.cells = new Cell[50][50]; //maximum map size
		this.enemies = new ArrayList<Enemy>(); //array List to store enemies as they are created 
	}
	
	/**
	 * Basic toString method that loops through the cells array outputting the type of cell that is stored in each
	 */
	public String toString() {
		char outputString[][] = new char[50][50];
		String newString = "";
		int x = 0; //counters
		int y = 0;
		for (Cell[] X : cells) {
			for(Cell c : X) {
				if(c != null) {
					CellType type = c.getCellType();
					if(type == CellType.TELEPORTER) {
						outputString[x][y] = 'P';
					} else {
						outputString[x][y] = c.getCellChar(); //2d Char array to recreate the map						
					}
					
				}
				y++;
			}
			x++;
			y = 0;
		}
		for(char[] X : outputString) {
			for(char Y : X) {
				newString = newString + Y;			
			}
				if(X[0] == '\u0000') {
				} else {
					newString = newString + "\n"; //convert 2d array into string 
				}
			//time for additional information WOOOOOOOO
		}
		newString = newString + ">\n";
		//TELEPORTER
		//DOOR_CELL
		//ITEM_CELL
		x = 0;
		y = 0;
		for(Cell[] row : this.cells) {
			y = 0;
			for(Cell c : row) {
				if(!(c == null)) {
					Cell currentCell = c;
					CellType currentCellType = currentCell.getCellType();
					if(currentCellType == CellType.TELEPORTER || currentCellType == CellType.DOOR_CELL || currentCellType == CellType.ITEM_CELL ) {
						newString = newString + x + " " + y + " ";
						switch(currentCellType) {
						case TELEPORTER:
							Teleporter t = (Teleporter) c;
							newString = newString + t.getPairX() + " " + t.getPairY() + "\n";
							break;
						case DOOR_CELL:
							Door d = (Door) c;
							String doorType = "";
							if(d.getDoorType() == DoorType.KEY_DOOR) {
								doorType = "K";
							} else {
								doorType = "T";
							}
							newString = newString + doorType + " " + d.getCondition() + "\n";
							break;
						case ITEM_CELL:
							Item i = (Item) c;
							ItemType itemType = i.getItemType();
							if(itemType == ItemType.KEY) {
								newString = newString + i.getItemType() + " " + i.getColour() + "\n";
							} else {
								newString = newString + i.getItemType() + "\n";
							}
							break;
						default:
							break;
						
						}
					}
				} else {
					
				}
				y++;
			}
			x++;
		}
		Player p = this.player;
		newString = newString + p.getX() + " " + p.getY() + " START";
		ArrayList<Item> items = p.getItems();
		for(Item i : items) {
			if(i.getItemType() == ItemType.KEY) {
				newString = newString + i.getItemType() + " " + i.getColour();
			} else {
				newString = newString + i.getItemType();
			}
		}
		newString = newString + "\n";
		
		//time to add enemies
		 ArrayList<Enemy> enemies = this.enemies;
		 for(Enemy e : enemies) {
			 newString = newString + e.getYCoord() + " " + e.getXCoord() + " ENEMY " + e.getEnemyType() + " ";
			 if(e.getEnemyType() == EnemyType.STRAIGHT ) {
				 StraightLineAI enemy = (StraightLineAI) e;
				 newString = newString + enemy.getDirection() ;
			 } else if(e.getEnemyType() == EnemyType.WALLHUGGER) {
				 WallHuggingAI enemy = (WallHuggingAI) e;
				 newString = newString + enemy.getDirectionWall();
			 } else if(e.getEnemyType() == EnemyType.DUMBTARGETNG) {
				 
			 } else if(e.getEnemyType() == EnemyType.SMART) {
				 
			 }
			 newString = newString + "\n";
			 
		 }
		return newString;
	}
	
	/**
	 * Method to get the current map state in the form of Characters 
	 * @return A 2d char array that represents the current state of the map
	 * with enemies and player on it 
	 */
	public char[][] mapCharArray(){
		char outputString[][] = new char[50][50];
		int x = 0; //counters
		int y = 0;
		for (Cell[] X : cells) {
			for(Cell c : X) {
				if(c != null) {
					outputString[x][y] = c.getCellChar();
				}
				y++;
			}
			x++;
			y = 0;
		}
		
		for(Enemy enemy: enemies) {
			x = enemy.getXCoord();
			y = enemy.getYCoord();
			EnemyType s = enemy.getEnemyType();
			char enemyChar= 'E';
			if (s.equals(EnemyType.STRAIGHT)) {
				enemyChar = 'S';
			} else if(s.equals(EnemyType.WALLHUGGER)) {
				enemyChar = 'H';
			} else if(s.equals(EnemyType.DUMBTARGETNG)) {
				enemyChar = '*';
			} else if(s.equals(EnemyType.SMART)) {
				enemyChar = 'R';
			}
			outputString[y][x] = enemyChar;
		}
		
		x = player.getX();
		y = player.getY();
		outputString[x][y] = 'P';
		
		return outputString;
	}
	/**
	 * Simple getter for a specific cell
	 * @param x X coordinate of the requested cell
	 * @param y Y coordinate of the requested cell
	 * @return returns the value stored in the location of the given location, this value will be of type Cell 
	 */
	public Cell getCell(int x, int y) {
		return this.cells[x][y];
	}
	
	
	/** 
	 * simple getter for the map its self 
	 * @return returns a Cell[][] array which stores the current version of the map
	 */
	public Cell[][] getMap() {
		return this.cells;
	}
	
	/**
	 * Method to return a Map object that is identical to the main map
	 * @return A map object that is identical to the main map 
	 */
	public Map mapCopy() {
		Map m = new Map();
		m.setPlayer(this.player);
		m.setMap(this.cells);
		m.setEnemies(this.enemies);
		return m;
	}
	
	public ArrayList<Enemy> getEnemies(){
		return this.enemies;
	}
	/**
	 * Method for the creation of a specified CellType at a given location
	 * @param cellType This is a enumeration of CellType @see CellType.java which represents the type of cell that is to be created 
	 * @param x X coordinate for the location of the to be created Cell
	 * @param y Y Coordinate for the location of the to be created Cell
	 */
	public void createCell(CellType cellType, int x, int y) { //will be private only public at moment for testing
		switch(cellType) {
		case DOOR_CELL:
			this.cells[x][y] = new Door(cellType);
			break;
		case TELEPORTER:
			this.cells[x][y] = new Teleporter(cellType);
			break;
		case ITEM_CELL:
			this.cells[x][y] = new Item(cellType);
			break;
		default:
			this.cells[x][y] = new Cell(cellType);
			break;
		}
	}
	/**
	 * Method to replace the Cell in the given location with a Floor_Cell
	 * @param x X coordinate for the location of the to be replaced Cell
	 * @param y Y coordinate for the location of the to be replaced Cell
	 */
	public void replaceCell(int x, int y) {
		this.cells[x][y] = new Cell(CellType.FLOOR_CELL);
	}
	/**
	 * Alternate version of the replaceCell method that allows for the specification of the new Cell 
	 * @param t The type of Cell that it is to be replaced with
	 * @param x X coordinate for the location of the to be replaced Cell
	 * @param y Y coordinate for the location of the to be replaced Cell
	 */
	public void replaceCell(CellType t, int x, int y) {
		this.cells[x][y] = new Cell(t);
	}
	
	/**
	 * This is the Staring point for the file reader and Map Creation 
	 * @param fileName A String that represents the name of the file that is to be opened 
	 */
	public void readFile(String fileName) {
		
		Scanner in = openFile(fileName);//Open File
		clearMap();
		int x = 0; //counter
		String l = in.nextLine();
		
		while(l.charAt(0) != SENTINEL_CHAR) { //until the sentinel is encountered
			Scanner line = new Scanner(l);
			createLine(line, x);
			x ++;
			l = in.nextLine();
		}
		
		while(in.hasNext()) {
			l= in.nextLine();
			Scanner line = new Scanner(l);
			additionalInformation(line);
		}
		
	}
	
	/**
	 * This is a part of the readFile method, this method is focused on reading the information from the final section
	 * of the file which specifies additional information for the created cells 
	 * @param in This is a Scanner for the line that is currently being read
	 */
	private void additionalInformation(Scanner in) {
		int x = in.nextInt();
		int y = in.nextInt();
		Cell c = getCell(x,y);
		CellType t = c.getCellType();//get type of cell
		switch(t){
		case TELEPORTER:
			int x2 = in.nextInt();
			int y2 = in.nextInt();
			Teleporter T = (Teleporter) c; //need to cast the type Cell into the relevant subclass 
			T.setPairedPoint(x2, y2);
			break;
		case ITEM_CELL:
			Item item =(Item) c;
			String itemType = in.next();
			switch(itemType) {
			case(KEY_STRING):
				String colour = in.next();
				item.setItemType(ItemType.KEY);
				item.setColour(colour);
				break;
			case(FIRE_BOOT_STRING):
				item.setItemType(ItemType.FIRE_BOOTS);
				break;
			case(FLIPPER_STRING):
				item.setItemType(ItemType.FLIPPERS);
				break;
			case(TOKEN_STRING):
				item.setItemType(ItemType.TOKEN);
				break;
			default:
				item.setItemType(ItemType.TOKEN); //default item in case of error in map file
			}
			break;
		case DOOR_CELL:
			String doorType = in.next();
			System.out.println(doorType);
			Door door = (Door) c;
			if(doorType.equals("K")) {
				door.setDoorType(DoorType.KEY_DOOR);
			} else if(doorType.equals("T")) {
				door.setDoorType(DoorType.TOKEN_DOOR);
			}
			String condition = in.next();
			door.setCondition(condition);
			break;
		case FLOOR_CELL: //this is for player start or enemy additional information
			String s = in.next();
			if(s.equals(START_STRING)) { //its player start 
				ArrayList<Item> i = new ArrayList<Item>(); 
				while (in.hasNext()){
					Item savedItem = new Item(CellType.ITEM_CELL);
					s = in.next();
					switch(s) {
					case(KEY_STRING):
						String colour = in.next();
						savedItem.setItemType(ItemType.KEY);
						savedItem.setColour(colour);
						i.add(savedItem);
						break;
					case(FIRE_BOOT_STRING):
						savedItem.setItemType(ItemType.FIRE_BOOTS);
						i.add(savedItem);
						break;
					case(FLIPPER_STRING):
						savedItem.setItemType(ItemType.FLIPPERS);
						i.add(savedItem);
						break;
					case(TOKEN_STRING):
						savedItem.setItemType(ItemType.TOKEN);
						i.add(savedItem);
						break;
					default:
						savedItem.setItemType(ItemType.TOKEN);
						i.add(savedItem);
						break;//default item in case of error in map file
					}
				}
				this.player = new Player(x,y,i,this);
			} else {
				if(s.equals(ENEMY_STRING)) {
					String enemyType = in.next();
					switch(enemyType) {
						case "SMART":
							SmartAI E1 = new SmartAI(y,x,0,0,this);
							enemies.add(E1);
							break;
						case "STRAIGHT":
							int yDirection = 0;
							int xDirection = 0;
							switch(in.next()) {
							case "UP":
								yDirection = -1;
								break;
							case "DOWN":
								yDirection = 1;
								break;
							case "LEFT":
								xDirection = -1;
								break;
							case "RIGHT":
								xDirection = 1;
								break;
							default:
								;
							}
							StraightLineAI E2 = new StraightLineAI(y,x,yDirection,xDirection,this);
							enemies.add(E2);
							break;
						case "WALLHUGGER":
							WallHuggingAI E3 = new WallHuggingAI(y,x,0,0,this,in.next());
							enemies.add(E3);
							break;
						case "DUMBTARGETNG":
							//add an alt route if shortest isnt possible
							DumbPlayerFinderAI E4 = new DumbPlayerFinderAI(y,x,0,0,this);
							enemies.add(E4);
							break;
						default:
					}
				}
			} 
			
		default:
			break;			
		}
		
	}
	
	/**
	 * This is a part of the readFile method, This method is for reading from the first section of the file
	 * which specifies what cells are where
	 * @param in A Scanner representing the current line from the First section of the file 
	 * @param x A counter variable that is used for the X coordinate in the creation of Cells
	 */
	private void createLine(Scanner in, int x) {
		String line = in.nextLine();
		for(int i = 0; i < line.length(); i++ ) {
			char c = line.charAt(i);
			//System.out.println(c);
			CellType t;
			switch(c) {
			case WALL_CHAR:
				t = CellType.WALL_CELL;
				break;
			case FLOOR_CHAR:
				t = CellType.FLOOR_CELL;
				break;
			case GOAL_CHAR:
				t = CellType.GOAL_CELL;
				break;
			case FIRE_CHAR:
				t = CellType.FIRE_CELL;
				break;
			case WATER_CHAR:
				t = CellType.WATER_CELL;
				break;
			case DOOR_CHAR:
				t = CellType.DOOR_CELL;
				break;
			case TELEPORTER_CHAR:
				t = CellType.TELEPORTER;
				break;
			case ITEM_CHAR:
				t = CellType.ITEM_CELL;
				break;
			default:
				t = CellType.WALL_CELL;
			}
			createCell(t,x,i);
			//System.out.println(t);

		} 
	}	
	
	/**
	 * This is a method that is used to open the file, it is a error handler for if the file
	 * does not exist
	 * @param fileName The name of the file that is to be opened
	 * @return This returns a Scanner of the opened file 
	 */
	private Scanner openFile(String fileName) {
		File file = new File(fileName);
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
			System.exit(0);
		}
		return in;
	}
	
	/**
	 * This is a simple method that loops through the cells array clearing every cell in it and setting it to null
	 */
	public void clearMap(){ //loops through file deleting cells, this is needed when loading the next map
		 /**for(int i=0; i<cells.length; i++) {
		        for(int j=0; j<cells[i].length; j++) {
		            //cells[i][j] = new Cell(CellType.FLOOR_CELL);
		        	cells[i][j] = null;
		        }
		    }
		    **/ //Alternate method is to just create a new Array and store it in the same part
		this.cells = new Cell[50][50]; //maximum map size
		this.enemies = new ArrayList<Enemy>(); //creating new ArrayList
		 
	}
	
	/**
	 * Simple getter for the player
	 * @return player object 
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * A player setter that sets the map player object 
	 * @param player is the player object 
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	/**
	 * Simple setter for the map Array
	 * @param c the 2d cell array that is to be stored 
	 */
	public void setMap(Cell[][] c) {
		this.cells = c;
	}
	
	/**
	 * Simple setter for the enemy Array list
	 * @param e the enemy array list that is to be set
	 */
	public void setEnemies (ArrayList<Enemy> e) {
		this.enemies = e;
	}
	public void getTokens(){
		
	}
}
	
	
