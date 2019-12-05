
public class DumbPlayerFinderAI extends Enemy{
	
	public DumbPlayerFinderAI(int xCoord, int yCoord, int dy, int dx, Map map) {
		super(xCoord, yCoord, dy, dx, map);
	}

	public void move(int playerXCoord, int playerYCoord) {
		// System.out.println("yeet45");
		System.out.println(playerXCoord + " " + playerYCoord + " player x and y");
		System.out.println(getXCoord() + " " + getYCoord() + "ai x and y");
		if (playerXCoord == getXCoord() && playerYCoord == getYCoord()) {
			System.out.println("done");
			setDy(0);
			setDx(0);
		}
		if (Math.abs(playerXCoord - getXCoord()) > Math.abs(playerYCoord - getYCoord())) {
			// System.out.println(Math.abs(playerXCoord - getXCoord())>Math.abs(playerYCoord
			// - getYCoord()));
			// System.out.println("player x and ai x: "+playerXCoord +" " + getXCoord());
			// System.out.println("player y and ai y: "+playerYCoord +" " + getYCoord());
			if ((playerXCoord == getXCoord())
					|| (checkCollision(getMap().getCell(getNextYCoord(), getNextXCoord()).getCellType()))) {
				System.out.println("1");
				setDx(0);
				setDy(0);
			} else if (playerXCoord > getXCoord()
					&& (!(checkCollision(getMap().getCell(getYCoord(), getXCoord() + 1).getCellType())))) {
				System.out.println("2");
				setDx(1);
				setDy(0);
			} else if ((playerXCoord < getXCoord())
					&& (!(checkCollision(getMap().getCell(getYCoord(), getXCoord() - 1).getCellType())))) {
				System.out.println("3");
				setDx(-1);
				setDy(0);
			}
			executeMove();
		} else {
			// System.out.println(Math.abs(playerXCoord - getXCoord())>Math.abs(playerYCoord
			// - getYCoord()));
			// System.out.println("player x and ai x: "+playerXCoord +" " + getXCoord());
			// System.out.println("player y and ai y: "+playerYCoord +" " + getYCoord());
			if ((playerYCoord == getYCoord())
					|| (checkCollision(getMap().getCell(getNextYCoord(), getNextXCoord()).getCellType()))) {
				System.out.println("4");
				setDx(0);
				setDy(0);
			} else if ((playerYCoord > getYCoord())
					&& (!(checkCollision(getMap().getCell(getYCoord() + 1, getXCoord() + 1).getCellType())))) {
				System.out.println("5");
				setDx(0);
				setDy(1);
			} else if ((playerYCoord < getYCoord())
					&& (!(checkCollision(getMap().getCell(getYCoord() - 1, getXCoord()).getCellType())))) {
				System.out.println("6");
				setDx(0);
				setDy(-1);
			}
			System.out.println("why all the suffering");
			executeMove();
		}
	}


}
