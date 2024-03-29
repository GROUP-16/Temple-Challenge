package application;
/**
 * 
 * @author Alex Masood
 *This class holds the strategy to keep the enemy attached to a wall
 */
public class WallHuggingAI extends Enemy{
	private String directionWall;
	private EnemyType type =EnemyType.WALLHUGGER;
	
	public WallHuggingAI(int xCoord, int yCoord, int dy, int dx, Map map,String directionWall) {
		super(xCoord, yCoord, dy, dx, map);
		this.directionWall = directionWall;
	}
	
	public EnemyType getEnemyType() {
		return type;
	}
	
	public String getDirectionWall() {
		return directionWall;
	}

	public void setDirectionWall(String directionWall) {
		this.directionWall = directionWall;
	}
	
	/**
	 * @param x and y coordinate of the player (can be left as any integer)
	 * the main move method swaps between the direction and wall its attached to other combinations
	 */
	public void move(int playerXCoord, int playerYCoord) {
		switch (getDirectionWall()) {
		case "upLeft":
			upLeft();
			break;
		case "leftDown":
			leftDown();
			break;
		case "downRight":
			downRight();
			break;
		case "rightUp":
			rightUp();
			break;
		case "upRight":
			upRight();
			break;
		case "leftUp":
			leftUp();
			break;
		case "rightDown":
			rightDown();
			break;
		case "downLeft":
			downLeft();
			break;
		default:
		}
		executeMove();
	}
	
	/**
	 * checks if the next direction is a valid move and then checks if the next move has a wall
	 * that is the same as the one it was attached to.
	 * the method uses this info to determine what to swap the direction and wall to.
	 */
	public void upLeft() {
		setDx(0);
		setDy(-1);
		// floor tile

		if (!(checkCollision(getMap().getCell(getYCoord() - 1, getXCoord()).getCellType()))) {
			// floor tile
			if (!(checkCollision(getMap().getCell(getYCoord() - 1, getXCoord() - 1).getCellType()))) {
				setDirectionWall("leftDown");
			}
		} else {
			if (!(checkCollision(getMap().getCell(getYCoord(), getXCoord() + 1).getCellType()))) {
				setDx(1);
				setDy(0);
			} else {
				setDx(0);
				setDy(0);
				setDirectionWall("downRight");
			}
		}
	}
	/**
	 * checks if the next direction is a valid move and then checks if the next move has a wall
	 * that is the same as the one it was attached to.
	 * the method uses this info to determine what to swap the direction and wall to.
	 */
	public void downRight() {
		setDx(0);
		setDy(1);
		// floor tile
		if (!(checkCollision(getMap().getCell(getYCoord() + 1, getXCoord()).getCellType()))) {
			// floor tile
			if (!(checkCollision(getMap().getCell(getYCoord() + 1, getXCoord() + 1).getCellType()))) {
				setDirectionWall("rightUp");
			}
		} else {
			if (!(checkCollision(getMap().getCell(getYCoord(), getXCoord() - 1).getCellType()))) {
				setDx(-1);
				setDy(0);
			} else {
				setDirectionWall("upLeft");
				setDx(0);
				setDy(0);
			}
		}
	}
	/**
	 * checks if the next direction is a valid move and then checks if the next move has a wall
	 * that is the same as the one it was attached to.
	 * the method uses this info to determine what to swap the direction and wall to.
	 */
	public void leftDown() {
		setDx(-1);
		setDy(0);
		// floor tile
		if (!(checkCollision(getMap().getCell(getYCoord(), getXCoord() - 1).getCellType()))) {
			// floor tile
			if (!(checkCollision(getMap().getCell(getYCoord() + 1, getXCoord() - 1).getCellType()))) {
				setDirectionWall("downRight");
			}
		} else {
			if (!(checkCollision(getMap().getCell(getYCoord() - 1, getXCoord()).getCellType()))) {
				setDx(0);
				setDy(-1);
			} else {
				setDx(0);
				setDy(0);
				setDirectionWall("rightUp");
			}
		}
	}
	/**
	 * checks if the next direction is a valid move and then checks if the next move has a wall
	 * that is the same as the one it was attached to.
	 * the method uses this info to determine what to swap the direction and wall to.
	 */
	public void rightUp() {
		setDx(1);
		setDy(0);
		// floor tile
		if (!(checkCollision(getMap().getCell(getYCoord(), getXCoord() + 1).getCellType()))) {
			// floor tile
			if (!(checkCollision(getMap().getCell(getYCoord() - 1, getXCoord() + 1).getCellType()))) {
				setDirectionWall("upLeft");
			}
		} else {
			if (!(checkCollision(getMap().getCell(getYCoord() + 1, getXCoord()).getCellType()))) {
				setDx(0);
				setDy(1);
			} else {
				setDx(0);
				setDy(0);
				setDirectionWall("leftDown");
			}
		}
	}
	/**
	 * checks if the next direction is a valid move and then checks if the next move has a wall
	 * that is the same as the one it was attached to.
	 * the method uses this info to determine what to swap the direction and wall to.
	 */
	public void upRight() {
		setDx(0);
		setDy(-1);
		// floor tile
		if (!(checkCollision(getMap().getCell(getYCoord() - 1, getXCoord()).getCellType()))) {
			// floor tile
			if (!(checkCollision(getMap().getCell(getYCoord() - 1, getXCoord() + 1).getCellType()))) {
				setDirectionWall("rightDown");
			}
		} else {
			if (!(checkCollision(getMap().getCell(getYCoord(), getXCoord() - 1).getCellType()))) {
				setDx(-1);
				setDy(0);
			} else {
				setDx(0);
				setDy(0);
				setDirectionWall("downLeft");
			}
		}
	}
	/**
	 * checks if the next direction is a valid move and then checks if the next move has a wall
	 * that is the same as the one it was attached to.
	 * the method uses this info to determine what to swap the direction and wall to.
	 */
	public void downLeft() {
		setDx(0);
		setDy(1);
		// floor tile
		if (!(checkCollision(getMap().getCell(getYCoord() + 1, getXCoord()).getCellType()))) {
			// floor tile
			if (!(checkCollision(getMap().getCell(getYCoord() + 1, getXCoord() - 1).getCellType()))) {
				setDirectionWall("leftUp");
			}
		} else {
			if (!(checkCollision(getMap().getCell(getYCoord(), getXCoord() + 1).getCellType()))) {
				setDx(1);
				setDy(0);
			} else {
				setDirectionWall("upRight");
				setDx(0);
				setDy(0);
			}
		}
	}
	/**
	 * checks if the next direction is a valid move and then checks if the next move has a wall
	 * that is the same as the one it was attached to.
	 * the method uses this info to determine what to swap the direction and wall to.
	 */
	public void leftUp() {
		setDx(-1);
		setDy(0);
		// floor tile
		if (!(checkCollision(getMap().getCell(getYCoord(), getXCoord() - 1).getCellType()))) {
			// floor tile
			if (!(checkCollision(getMap().getCell(getYCoord() + 1, getXCoord() - 1).getCellType()))) {
				setDirectionWall("upRight");
			}
		} else {
			if (!(checkCollision(getMap().getCell(getYCoord() - 1, getXCoord()).getCellType()))) {
				setDx(0);
				setDy(-1);
			} else {
				setDx(0);
				setDy(0);
				setDirectionWall("downLeft");
			}
		}
	}
	/**
	 * checks if the next direction is a valid move and then checks if the next move has a wall
	 * that is the same as the one it was attached to.
	 * the method uses this info to determine what to swap the direction and wall to.
	 */
	public void rightDown() {
		setDx(1);
		setDy(0);
		// floor tile
		if (!(checkCollision(getMap().getCell(getYCoord(), getXCoord() + 1).getCellType()))) {
			// floor tile
			if (!(checkCollision(getMap().getCell(getYCoord() + 1, getXCoord() + 1).getCellType()))) {
				setDirectionWall("downLeft");
			}
		} else {
			if (!(checkCollision(getMap().getCell(getYCoord() + 1, getXCoord()).getCellType()))) {
				setDx(0);
				setDy(1);
			} else {
				setDx(0);
				setDy(0);
				setDirectionWall("leftUp");
			}
		}
	}
}
