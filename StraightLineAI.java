

public class StraightLineAI extends Enemy{
	public StraightLineAI(int xCoord, int yCoord, int dy, int dx, Map map) {
		super(xCoord, yCoord, dy, dx, map);
		changeDirection();
		// TODO Auto-generated constructor stub
	}
	private void changeDirection() {
		setDy(getDy() * -1);
		setDx(getDx() * -1);
	}
	public void move(int playerXCoord, int playerYCoord) {
		if (checkCollision(getMap().getCell(getNextYCoord(), getNextXCoord()).getCellType())) {
			if (checkCollision(getMap().getCell(getYCoord() + getDx() * -1, getXCoord() + getDy() * -1).getCellType())) {
				setDy(0);
				setDx(0);
			}
			changeDirection();

		}
		executeMove();
	}

}
