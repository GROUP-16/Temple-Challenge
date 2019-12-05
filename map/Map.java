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
 *X represents the Column 
 *Y Represents the row 
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
	private final char ENEMY_CHAR = 'E';
	
	
	//constants for additional information
	private final String START_STRING = "START";
	private final String KEY_STRING = "KEY";
	private final String FIRE_BOOT_STRING = "FIRE_BOOTS";
	private final String FLIPPER_STRING = "FLIPPER";
	private final String TOKEN_STRING = "TOKEN";

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
			outputString[y][x] = 'E';
		}
		
		x = player.getX();
		y = player.getY();
		outputString[x][y] = 'P';
		
		for(char[] X : outputString) {
			for(char Y : X) {
				System.out.print(Y);
			}
			System.out.println("");
		}

		return " ";
	}
	
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
			outputString[y][x] = 'E';
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
		//System.out.println(l);
		
		while(l.charAt(0) != SENTINEL_CHAR) { //until the sentinel is encountered
			Scanner line = new Scanner(l);
			createLine(line, x);
			x ++;
			l = in.nextLine();
			//System.out.println(l);
		}
		//l= in.nextLine();
		
		while(in.hasNext()) {
			l= in.nextLine();
			//System.out.println(l);
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
		//System.out.println(x + " " + y  ); //for testing
		Cell c = getCell(x,y);
		CellType t = c.getCellType();//get type of cell
		//System.out.println(t); //for testing
		switch(t){
		case TELEPORTER:
			int x2 = in.nextInt();
			int y2 = in.nextInt();
			//Item item = (Item) map.getCell(0, 0);
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
					//Item item = (Item) map.getCell(0, 0);
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
				//enemy stuff
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
			case ENEMY_CHAR:
				t = CellType.FLOOR_CELL;
				String enemyType = "Smart";
				switch(enemyType) {
					case "Smart":
						SmartAI E1 = new SmartAI(i,x,0,0,this);
						enemies.add(E1);
						break;
					case "Straight":
						StraightLineAI E2 = new StraightLineAI(i,x,0,-1,this);
						enemies.add(E2);
						break;
					case "WallHugger":
						WallHuggingAI E3 = new WallHuggingAI(i,x,0,0,this,"upRight");
						enemies.add(E3);
						break;
					case "Dumb":
						//add an alt route if shortest isnt possible
						DumbPlayerFinderAI E4 = new DumbPlayerFinderAI(i,x,0,0,this);
						enemies.add(E4);
						break;
					default:
				}
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setMap(Cell[][] c) {
		this.cells = c;
	}
	
	public void setEnemies (ArrayList<Enemy> e) {
		this.enemies = e;
	}
}
	
	

