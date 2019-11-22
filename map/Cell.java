/**
 * This class is responsible for all operations and variables that are related to individual cells
 * @author George Sims 
 */
public class Cell {
	protected CellType cellType;
	
	/**
	 * Simple constructor that creates a new Cell
	 * @param cellType Enumeration type that represents the type of Cell
	 */
	public Cell (CellType cellType) {
		this.cellType = cellType;
	}
	
	/**
	 * A simple getter that returns the type of the cell
	 * @return returns an enumeration of CellType that represents the type of cell
	 */
	public CellType getCellType(){
		return this.cellType;
	}
	
	public char getCellChar() {
		switch(this.cellType) {
		case FIRE_CELL:
			return 'F';
		case FLOOR_CELL:
			return ' ';
		case WATER_CELL:
			return 'W';
		case WALL_CELL:
			return '#';
		case DOOR_CELL:
			return 'D';
		case TELEPORTER:
			return 'T';
		case GOAL_CELL:
			return 'G';
		case ITEM_CELL:
			return 'I';
		default:
			return ' ';
		}
	}
	
	
}
