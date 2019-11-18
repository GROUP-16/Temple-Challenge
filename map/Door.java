
public class Door extends Cell { // I have decided to merge KeyDoor and TokenDoor into just Door
	private DoorType doorType;
	private String condition; //condition is dependent on if it is a KeyDoor or a TokenDoor
	
	public Door(CellType cellType) {
		super(cellType);
	}
	
	public DoorType getDoorType(){
		return this.doorType;
	}
	
	public String getCondition() {
		return condition;
	}
	
	public void setDoorType(DoorType doorType) {
		this.doorType = doorType;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
