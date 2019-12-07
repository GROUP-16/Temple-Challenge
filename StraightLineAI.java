package application;


public class StraightLineAI extends Enemy{
	public StraightLineAI(int xCoord, int yCoord, int dy, int dx, Map map) {
		super(xCoord, yCoord, dy, dx, map);
		changeDirection();
	}
	private void changeDirection() {
		setDy(getDy() * -1);
		setDx(getDx() * -1);
	}
	public void move(int playerXCoord, int playerYCoord) {
		if (checkCollision(getMap().getCell(getNextYCoord(), getNextXCoord()).getCellType())) {
			changeDirection();

		}
		executeMove();
	}

}
