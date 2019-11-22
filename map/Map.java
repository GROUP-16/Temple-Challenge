import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is responsible for all operations relating to the Map Class, these operations include map creation
 * from a file and storing the map. The Map will be heavily relied upon by the other classes in the project
 * 
 * @author George Sims
 *
 */
public class Map {
	
	private Cell[][] cells;
	private Enemy[][] stuff;
	
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
	
	/**
	 * Basic constructor that initialises the map and Cell array to a default size of 50*50 
	 */
	public Map() {
		this.cells = new Cell[50][50]; //maximum map size
	}
	
	/**
	 * Basic toString method that loops through the cells array outputting the type of cell that is stored in each
	 */
	public String toString() {
		for (Cell[] x : cells) {
			for(Cell c : x) {
				if(c != null) {
					System.out.print(c.getCellChar() + " ");					
				}				
			}
			System.out.println();
		}
		return " ";
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
			case("Key"):
				String colour = in.next();
				item.setItemType(ItemType.KEY);
				item.setColour(colour);
				break;
			case("FireBoots"):
				item.setItemType(ItemType.FIRE_BOOTS);
				break;
			case("Flippers"):
				item.setItemType(ItemType.FLIPPERS);
				break;
			case("Token"):
				item.setItemType(ItemType.TOKEN);
				break;
			default:
				item.setItemType(ItemType.TOKEN); //default item in case of error in map file
			}
			break;
		case DOOR_CELL:
			String doorType = in.next();
			Door door = (Door) c;
			if(doorType == "Key") {
				door.setDoorType(DoorType.KEY_DOOR);
				
			} else {
				door.setDoorType(DoorType.TOKEN_DOOR);
			}
			String condition = in.next();
			door.setCondition(condition);
			break;
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
	private void clearMap(){ //loops through file deleting cells, this is needed when loading the next map
		 for(int i=0; i<cells.length; i++) {
		        for(int j=0; j<cells[i].length; j++) {
		            //cells[i][j] = new Cell(CellType.FLOOR_CELL);
		        	cells[i][j] = null;
		        }
		    }
	}	
}
	
	

