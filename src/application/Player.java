package application;
import java.util.ArrayList;

/**
 * The player class is responsible for all of the players interactions with the map
 * e.g. the players movement, adding items to the inventory and removing items 
 * @author George Sims
 *
 */
public class Player {
	private int x;
	private int y;
	private ArrayList<Item> items;
	private Map m;
	
	/**
	 * A basic constructor for the player object
	 * @param x a integer that represents the players row
	 * @param y a integer that represents the players column
	 * @param items the arrayList of items that represents 
	 * @param m
	 */
	public Player(int x, int y, ArrayList<Item> items, Map m) {
		this.x = x;
		this.y = y;
		this.items = items;
		this.m = m;
	}

	/**
	 * A basic getter for the X value of the player 
	 * @return X value for the player
	 */
	public int getX() {
		return x;
	}

	/**
	 * Simple Setter for the players X value
	 * @param x value that is to be set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Simple getter for the Y value of the player
	 * @return Y value of the player
	 */
	public int getY() {
		return y;
	}

	/**
	 * Simple setter for the y value
	 * @param y value that is to be set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the players arraylist that represents the players inventory
	 * @return the player inventory 
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * Simple setter for the player inventory 
	 * @param items is the ArrayList to be stored
	 */
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	/**
	 * Add type Item to the player Arraylist<Item>
	 * @param i Item that is to be added
	 */
	private void addItem(Item i) {
		this.items.add(i);
	}
	
	/**
	 * Method to remove an item from the players inventory 
	 * @param itemToRemove is the Itemtype we are going to remove 
	 * @param condition if it is a key we need to know the colour of the key
	 */
	public void removeItem(ItemType itemToRemove, String condition) {//if its not a key condition is null
		boolean a = false;
		Item I = null;
		for (Item i : this.items) {
			ItemType itemType = i.getItemType();
			if (!a) { //only 1 item is removed
				if(itemType == itemToRemove && i.getColour() == condition) {
					I = i;
					//if item type is the same and item condition is the same
					a = true; //once one item has been removed no more items can be removed
					//items.remove(i); // Can't remove in the loop as it causes a exception
				}
			}
		}
		if(!(I == null)) {
			items.remove(I);
		}
		
			
	}
	/**
	 * Method to check if the player has the required item in his inventory 
	 * @param itemToCheck the Item type we are looking for
	 * @param condition If it is a key we need to know the colour of the key
	 * @return a boolean value that is if we have the item or not 
	 */
	public boolean checkInventory(ItemType itemToCheck,String condition) { //if its not a key condition is null
		boolean a = false;
		for (Item i : this.items) {
			ItemType itemType = i.getItemType();
			if (!a) { //only 1 item needs to be found
				if(i.getColour() == null) {
					if(itemType == itemToCheck) {
						a = true;
					}
				} else if(itemType == itemToCheck && i.getColour().equals(condition)) {
					a = true;
				}
			}
		}
		return a;
	}
	
	/**
	 * Method to determine where the player is going to move to and the cell he is going to
	 * @param d the direction the player is moving to 
	 * @return a object[] array that stores 3 values where the first is the x, second is the y and the third is the Cell the player is heading to 
	 */
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
	
	/**
	 * Gets the Cell the player is trying to move to
	 * @param temp a int[] array that stores the x and y values 
	 * @return type Cell that represents the Cell 
	 */
	private Cell getNewCell(int[] temp) {
		int newX = temp[0];
		int newY = temp[1];
		Cell c = m.getCell(newX, newY);
		System.out.println(newX);
		System.out.println(newY);
		return c;
		
	}
	
	/**
	 * The main method that is responsible for moving the player 
	 * @param d enumerator that represents the direction the player is trying to move in 
	 */
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
					//Remove tokens from player inventory
					for(int i = 0; i < requirement; i++) {
						System.out.println("Q");
						removeItem(ItemType.TOKEN, null);						
					}
					this.x = newX;
					this.y = newY;
					
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

	/**
	 * A method that checks if the method is a valid move 
	 * @param type the CellType of the cell that the player is trying to move to
	 * @return Boolean value that represents if it is a valid move 
	 */
	private boolean validMove(CellType type) {
		// only invalid cell type is a wall
		if(type == CellType.WALL_CELL) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if the player is dead
	 * @return Boolean value that represents if the player is dead or not 
	 */
	public boolean isDead(){
		boolean a = false;
		CellType t = m.getCell(this.x, this.y).getCellType();
		//need to check if the cell is Fire or Water then we need to compare X,Y with every enemy 
		if(t == CellType.FIRE_CELL && !checkInventory(ItemType.FIRE_BOOTS, null)) {
			a = true;
		} else if(t == CellType.WATER_CELL && !checkInventory(ItemType.FLIPPERS, null)) {
			a = true;
		}
		ArrayList<Enemy> enemies = m.getEnemies();
		for(Enemy e : enemies) { //for every enemy look for player overlap 
			int enemyX = e.getXCoord();
			int enemyY = e.getYCoord();
			if(this.x == enemyY && this.y == enemyX) {
				a = true;
			}
		}
		return a;
	}
	/**
	 * Checks if the game is won 
	 * @return boolean value that represents if the game is won 
	 */
	public boolean gameWon() {
		CellType t = m.getCell(this.x, this.y).getCellType();
		if(t == CellType.GOAL_CELL) {
			return true;
		} else {
			return false;
		}
	}
	public Integer totalTokens() {
		Integer counter = 0;
		for (Item i : this.items) {
			ItemType itemType = i.getItemType();
			if(itemType == ItemType.TOKEN) {
				counter ++;
			} 
		}
		return counter;
	}
	public Integer totalBlue() {
		Integer counter = 0;
		for (Item i : this.items) {
			ItemType itemType = i.getItemType();
			if(itemType == ItemType.KEY) {
				if(i.getColour().equalsIgnoreCase("BLUE")) {
					counter ++;
				}
			} 
		}
		return counter;
	}
	public Integer totalGreen() {
		Integer counter = 0;
		for (Item i : this.items) {
			ItemType itemType = i.getItemType();
			if(itemType == ItemType.KEY) {
				if(i.getColour().equalsIgnoreCase("GREEN")) {
					counter ++;
				}
			} 
		}
		return counter;
	}
	public Integer totalYellow() {
		Integer counter = 0;
		for (Item i : this.items) {
			ItemType itemType = i.getItemType();
			if(itemType == ItemType.KEY) {
				if(i.getColour().equalsIgnoreCase("YELLOW")) {
					counter ++;
				}
			} 
		}
		return counter;
		
	}
	public Integer totalRed() {
		Integer counter = 0;
		for (Item i : this.items) {
			ItemType itemType = i.getItemType();
			if(itemType == ItemType.KEY) {
				if(i.getColour().equalsIgnoreCase("RED")) {
					counter ++;
				}
			} 
		}
		return counter;
	
}
}
	