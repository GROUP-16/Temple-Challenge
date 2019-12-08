package application;
/**
 * 
 * @author Alex Masood
 *This class holds the strategy to move the enemy as close to the player as possible by lowering the distance
 *between itself and the player as much as possible without making an illegal move
 */
public class DumbPlayerFinderAI extends Enemy{
	private EnemyType type = EnemyType.DUMBTARGETNG;
	public DumbPlayerFinderAI(int xCoord, int yCoord, int dy, int dx, Map map) {
		super(xCoord, yCoord, dy, dx, map);
	}
	public EnemyType getEnemyType() {
		return type;
	}
	/**
	 * @param int player x and y coordinates
	 * the largest of the distance between the player and enemy's x and y coordinate 
	 * will be decremented unless decrementing the coordinate causes an illegal move.
	 */
	public void move(int playerXCoord, int playerYCoord,Map map) {
		if (playerXCoord == getXCoord() && playerYCoord == getYCoord()) {
			setDy(0);
			setDx(0);
		}
		if (Math.abs(playerXCoord - getXCoord()) > Math.abs(playerYCoord - getYCoord())) {
			if ((playerXCoord == getXCoord())
					|| (checkCollision(getMap().getCell(getNextYCoord(), getNextXCoord()).getCellType()))) {
				setDx(0);
				setDy(0);
			} else if (playerXCoord > getXCoord()
					&& (!(checkCollision(getMap().getCell(getYCoord(), getXCoord() + 1).getCellType())))) {
				setDx(1);
				setDy(0);
			} else if ((playerXCoord < getXCoord())
					&& (!(checkCollision(getMap().getCell(getYCoord(), getXCoord() - 1).getCellType())))) {
				setDx(-1);
				setDy(0);
			}
			executeMove();
		} else {
			if ((playerYCoord == getYCoord())
					|| (checkCollision(getMap().getCell(getNextYCoord(), getNextXCoord()).getCellType()))) {
				setDx(0);
				setDy(0);
			} else if ((playerYCoord > getYCoord())
					&& (!(checkCollision(getMap().getCell(getYCoord() + 1, getXCoord() + 1).getCellType())))) {
				setDx(0);
				setDy(1);
			} else if ((playerYCoord < getYCoord())
					&& (!(checkCollision(getMap().getCell(getYCoord() - 1, getXCoord()).getCellType())))) {
				setDx(0);
				setDy(-1);
			}
			executeMove();
		}
	}


}