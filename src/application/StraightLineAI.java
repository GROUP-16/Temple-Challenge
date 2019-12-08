package application;
/**
 * 
 * @author Alex Masood
 *This class holds the strategy to move the enemy in a straight line
 */

public class StraightLineAI extends Enemy{
	private EnemyType type =EnemyType.STRAIGHT;
	public StraightLineAI(int xCoord, int yCoord, int dy, int dx, Map map) {
		super(xCoord, yCoord, dy, dx, map);
		changeDirection();
	}
	
	public EnemyType getEnemyType() {
		return type;
	}
	/**
	 * multiplies the direction by -1 which will make it go in the opposite direction
	 */
	private void changeDirection() {
		setDy(getDy() * -1);
		setDx(getDx() * -1);
	}
	/**
	 * @param int x and y coordinate of the player
	 * this does a check on if the next move will cause a collision with something that isnt a floor tile
	 * if there is a collision it will change the direction
	 */
	public void move(int playerXCoord, int playerYCoord,Map map) {
		if (checkCollision(getMap().getCell(getNextYCoord(), getNextXCoord()).getCellType())) {
			changeDirection();

		}
		executeMove();
	}

}