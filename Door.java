package application;
/**
 * Class that is responsible for all variables and operations that are specific to a Door cell
 * @author George Sims
 *
 */

public class Door extends Cell { // I have decided to merge KeyDoor and TokenDoor into just Door
	private DoorType doorType;
	private String condition; //condition is dependent on if it is a KeyDoor or a TokenDoor
	
	/**
	 * Basic constructor that calls the Super classes constructor 
	 * @param cellType Enumeration that represents the type of Cell
	 */
	public Door(CellType cellType) {
		super(cellType);
	}
	
	/**
	 * Simple getter of the doorType
	 * @return Enumeration that represents the type of Door
	 */
	public DoorType getDoorType(){
		return this.doorType;
	}
	
	/**
	 * Simple getter of the doors opening condition
	 * @return String value that represents the doors opening condition e.g. Key colour or Token number
	 */
	public String getCondition() {
		return condition;
	}
	
	/**
	 * Simple Setter of the doorType variable
	 * @param doorType Enumeration that represents the DoorType 
	 */
	public void setDoorType(DoorType doorType) {
		this.doorType = doorType;
	}
	
	/**
	 * Simple setter of the doors opening condition
	 * @param condition String that represents the doors opening condition e.g. Key colour or Token number
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

}
