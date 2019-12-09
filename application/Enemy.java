package application;

/**
 * Abstract parent class that is responsible for creating and moving
 * the different enemy subclasses 
 * @author Alex Masood
 *
 */
public abstract class Enemy {
	private int xCoord;
	private int yCoord;
	// dy and dx are to choose the direction the enemy is about to move in.
	// multiplying this by -1 will change the direction
	private int dy;
	private int dx;
	private int nextXCoord;
	private int nextYCoord;
	private Map map;
	
	/**
	 * Basic Enemy Constructor 
	 * @param xCoord Int representing the enemies starting x coordinate
	 * @param yCoord Int representing the enemies starting y coordinate
	 * @param dy Y coordinate that the enemy starts heading to 
	 * @param dx X coordinate that the enemy starts heading to 
	 * @param map A reference to the Map class that is storing the enemies 
	 */
	public Enemy(int xCoord, int yCoord, int dy, int dx, Map map) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.dy = dy;
		this.dx = dx;
		this.map = map;
	}
	/**
	 * Simple getter for the x Coordinate 
	 * @return int representing the enemies x coordinate
	 */
	public int getXCoord() {
		return this.xCoord;
	}
	/**
	 * Simple setter for the enemies current X coordinate
	 * @param xCoord X coordinate to be set
	 */
	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	/**
	 * Simple getter for the y Coordinate 
	 * @return int representing the y coordinate of the enemy 
	 */
	public int getYCoord() {
		return this.yCoord;
	}
	/**
	 * Simple setter for the Y coordinate
	 * @param yCoord int that is to be set as the enemy Y Coordinate 
	 */
	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	/**
	 * Simple Getter for y part of the cell that it is heading for 
	 * @return the int value of the Cell that the enemy is heading for 
	 */
	public int getDy() {
		return dy;
	}
	/**
	 * Simple Setter for the y part where the enemy is heading to 
	 * @param dy int value that is to be set in the dy
	 */
	public void setDy(int dy) {
		this.dy = dy;
	}
	/**
	 * Simple getter for the X value of the cell the enemy is heading for 
	 * @return Int value that is being returned 
	 */
	public int getDx() {
		return dx;
	}
	/**
	 * Simple setter for the X value that represents the cell the enemy is heading for 
	 * @param dx X coordinate that the enemy is heading for 
	 */
	public void setDx(int dx) {
		this.dx = dx;
	}
	/**
	 * Simple Getter for the X coordinate that the enemy is moving to
	 * @return Int value that is being returned 
	 */
	public int getNextXCoord() {
		return nextXCoord;
	}
	/**
	 * Simple setter for the X coordinate that the enemy is moving to 
	 * @param nextXCoord The value that is being set to 
	 */
	public void setNextXCoord(int nextXCoord) {
		this.nextXCoord = nextXCoord;
	}
	/**
	 * Simple getter for the Y coordinate that the enmy is moving to 
	 * @return value that is being returned 
	 */
	public int getNextYCoord() {
		return nextYCoord;
	}
	/**
	 * Simple setter for the Y coordinate of the cell 
	 * @param nextYCoord Int value that it is being set to
	 */
	public void setNextYCoord(int nextYCoord) {
		this.nextYCoord = nextYCoord;
	}
	/**
	 * Getter for the map that the enemy is being stored in
	 * @return a reference to the map object
	 */
	public Map getMap() {
		return map;
	}
	
	/**
	 * Abstract move method that moves different types of enemies 
	 * @param playerXCoord X coordinate that the player is currently in
	 * @param playerYCoord Y coordinate that the player is currently in
	 * @param map A reference to the map that the enemy and the player are currently in 
	 */
	public abstract void move(int playerXCoord, int playerYCoord,Map map);
	/**
	 * Abstract method that returns the type of Enemy, this is used in the profile saving 
	 * @return
	 */
	public abstract EnemyType getEnemyType();
	/**
	 * Method to update the next X and Y coordinates
	 */
	public void updateNext() {
		nextXCoord = xCoord + dx;
		nextYCoord = yCoord + dy;
	}
	/**
	 * Method to move the enemy to the designated cell
	 */
	public void executeMove() {
		setXCoord(getXCoord() + getDx());
		setYCoord(getYCoord() + getDy());
		updateNext();
	}
	/**
	 * Method to check if the enemy has a collision with a celltype it shouldnt 
	 * @param nextCell A reference to the Cell that the enemy is moving into 
	 * @return A boolean value that represents if there is a collision or not
	 */
	public boolean checkCollision(CellType nextCell) {
		if (nextCell.equals(CellType.FLOOR_CELL)) {
			return false;
		} else {
			return true;
		}
	}





}
