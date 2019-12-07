package application;

public class DumbPlayerFinderAI extends Enemy{
	
	public DumbPlayerFinderAI(int xCoord, int yCoord, int dy, int dx, Map map) {
		super(xCoord, yCoord, dy, dx, map);
	}

	public void move(int playerXCoord, int playerYCoord) {
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
