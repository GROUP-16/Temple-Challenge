import java.util.ArrayList;

public class Player {
	private int x;
	private int y;
	private ArrayList<Item> items;
	private Map m;
	
	
	public Player(int x, int y, ArrayList<Item> items, Map m) {
		this.x = x;
		this.y = y;
		this.items = items;
		this.m = m;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public ArrayList<Item> getItems() {
		return items;
	}


	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	private void addItem(Item i) {
		this.items.add(i);
	}
	
	public void removeItem(ItemType itemToRemove, String condition) {//if its not a key condition is null
		boolean a = false;
		Item I = null;
		for (Item i : this.items) {
			System.out.println("GAY");
			ItemType itemType = i.getItemType();
			
			if (!a) { //only 1 item is removed 
				if(itemType == itemToRemove && i.getColour().equals(condition)) {
					I = i;
					//if item type is the same and item condition is the same
					a = true; //once one item has been removed no more items can be removed
					//items.remove(i); // Can't remove in the loop as it causes a exception
				}
			}
		}
		items.remove(I);
			
	}
	
	public boolean checkInventory(ItemType itemToCheck,String condition) { //if its not a key condition is null
		boolean a = false;
		for (Item i : this.items) {
			ItemType itemType = i.getItemType();
			if (!a) { //only 1 item needs to be found
				if(itemType == itemToCheck && i.getColour().equals(condition)) { 
					//if item type is the same and item condition is the same
					a = true; //once one item has been found we don't need to find anymore
				}
			}
		}
		return a;
	}
	
	private Object[] newCell(DirectionType d) {
		int newX = this.x;	//X is Row
		int newY = this.y; //Y is Column
		switch(d){
		case up:
			newX--;
			break;
		case down:
			newX++;
			break;
		case left:
			newY--;
			break;
		case right:
			newY++;
			break;
		}
		int[] returnValue = {newX,newY};
		Cell c =  getNewCell(returnValue);
		Object[] tempObject = new Object[3];
		tempObject[0] = newX;
		tempObject[1] = newY;
		tempObject[2] = c;
		return tempObject;
	}
	
	private Cell getNewCell(int[] temp) {
		int newX = temp[0];
		int newY = temp[1];
		Cell c = m.getCell(newX, newY);
		System.out.println(newX);
		System.out.println(newY);
		return c;
		
	}
	public void move(DirectionType d) {
		Object[] tempObject = newCell(d);
		int newX = (int) tempObject[0];
		int newY = (int) tempObject[1];
		Cell c = (Cell) tempObject[2];
		CellType type = c.getCellType();
		//If the Type is anything other than Item or Door we move them there
		if(type != CellType.DOOR_CELL  && type != CellType.ITEM_CELL && type != CellType.TELEPORTER) {
			if (validMove(type)) {
				this.x = newX;
				this.y = newY;
			} else { 
				//Invalid Move player doesn't move
			}
		} else if (type == CellType.DOOR_CELL) { //It is of type Door
			// Cast to door and determine type of Door then check players inventory for item
			Door door = (Door) c;
			if (door.getDoorType() == DoorType.KEY_DOOR) {
				//check player for a key with same colour as condition
				@SuppressWarnings("unused")
				boolean a = false;
				String requirement = door.getCondition();
				@SuppressWarnings("unused")
				Item item = null;
				for (Item i : this.items) {
					ItemType itemType = i.getItemType();
					if(itemType == ItemType.KEY && i.getColour().equals(requirement)) {
						a = true;
						item = i;
					} 
				}
				if(a) {
					m.replaceCell(newX, newY);
					this.x = newX;
					this.y = newY;
					removeItem(ItemType.KEY,requirement);
				}
				
				
			} else if(door.getDoorType() == DoorType.TOKEN_DOOR) { //it is a token door
				//check players inventory for number of tokens 
				int counter = 0;
				for (Item i : this.items) {
					ItemType itemType = i.getItemType();
					if(itemType == ItemType.TOKEN) {
						counter ++;
					} 
				}
				int requirement = Integer.parseInt(door.getCondition());
				if(!(counter < requirement)) {
					m.replaceCell(newX, newY);  //X is row Y is Column 
					this.x = newX;
					this.y = newY;
					//Remove tokens from player inventory
					for(int i = 0; i < requirement; i++) {
						removeItem(ItemType.TOKEN, null);
					}
					
				} 
			}
		} else if (type == CellType.ITEM_CELL){ //if it is of type item
			Item itemCell = (Item) c;
			addItem(itemCell);
			m.replaceCell(newX, newY);
			this.x = newX;
			this.y = newY;
		} else { //it is of type Teleporter
			Teleporter t = (Teleporter) c;
			newX =  t.getPairX();	//X is Row
			newY = t.getPairY();	//Y is Column
			switch(d){
			case up:
				newX--;
				break;
			case down:
				newX++;
				break;
			case left:
				newY--;
				break;
			case right:
				newY++;
				break;
			}
			this.x = newX;
			this.y = newY;
		}
	}


	private boolean validMove(CellType type) {
		// only invalid cell type is a wall
		if(type == CellType.WALL_CELL) {
			return false;
		}
		return true;
	}

	public boolean isDead(){
		boolean a = false;
		CellType t = m.getCell(this.x, this.y).getCellType();
		//need to check if the cell is Fire or Water then we need to compare X,Y with every enemy 
		if(t == CellType.FIRE_CELL && !checkInventory(ItemType.FIRE_BOOTS, null)) {
			System.out.println("DEAD");
			a = true;
		} else if(t == CellType.WATER_CELL && !checkInventory(ItemType.FLIPPERS, null)) {
			a = true;
		}
		ArrayList<Enemy> enemies = m.getEnemies();
		for(Enemy e : enemies) { //for every enemy look for player overlap 
			int enemyX = e.getXCoord();
			int enemyY = e.getYCoord();
			if(this.x == enemyX && this.y == enemyY) {
				a = true;
			}
		}
		return a;
	}
	
	public boolean gameWon() {
		CellType t = m.getCell(this.x, this.y).getCellType();
		if(t == CellType.GOAL_CELL) {
			return true;
		} else {
			return false;
		}
	}


}
