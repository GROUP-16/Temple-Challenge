import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Map {
	private Cell[][] cells;
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
	
	
	public Map() {
		this.cells = new Cell[50][50]; //maximum map size
	}
	
	public String toString() {
		for (Cell[] x : cells) {
			for(Cell c : x) {
				if(c != null) {
					System.out.print(c.getCellType() + " ");					
				}				
			}
			System.out.println();
		}
		return " ";
	}
	public Cell getCell(int x, int y) {
		return this.cells[x][y];
	}
	
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
	
	public void replaceCell(int x, int y) {
		this.cells[x][y] = new Cell(CellType.FLOOR_CELL);
	}
	
	public void replaceCell(CellType t, int x, int y) {
		this.cells[x][y] = new Cell(t);
	}
	
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
	
	private void clearMap(){ //loops through file deleting cells, this is needed when loading the next map
		 for(int i=0; i<cells.length; i++) {
		        for(int j=0; j<cells[i].length; j++) {
		            //cells[i][j] = new Cell(CellType.FLOOR_CELL);
		        	cells[i][j] = null;
		        }
		    }
	}	
}
	
	

