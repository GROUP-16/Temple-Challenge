
public class WallHuggingAI extends Enemy{
	private String directionWall;
	
	public WallHuggingAI(int yCoord, int xCoord, int dy, int dx, Map map,String directionWall) {
		super(yCoord, xCoord, dy, dx, map);
		this.directionWall = directionWall;
	}
	
	public String getDirectionWall() {
		return directionWall;
	}

	public void setDirectionWall(String directionWall) {
		this.directionWall = directionWall;
	}


	public void wallHuggerAI() {
		// states in direction and hugging wall
		// cry
		
		//System.out.println(firstMove);
		System.out.println(getYCoord() + " for y and x is " + getXCoord());
		System.out.println("ok");
		switch (getDirectionWall()) {
		case "upLeft":
			System.out.println("current upLeft");
			upLeft();
			break;
		case "leftDown":
			System.out.println("current leftDown");
			leftDown();
			break;
		case "downRight":
			System.out.println("current downRight");
			downRight();
			break;
		case "rightUp":
			System.out.println("current rightUp");
			rightUp();
			break;
		default:
			System.out.println("nothing");
		}
		System.out.println("was " + getYCoord() + " for y and x is " + getXCoord());
		executeMove();
		System.out.println("is now " + getYCoord() + " for y and x is " + getXCoord());

	}

	public void upLeft() {
		setDx(0);
		setDy(-1);
		// floor tile

		if (!(checkCollision(getMap().getCell(getYCoord() - 1, getXCoord()).getCellType()))) {
			// floor tile
			System.out.println("up floor tile");
			if (!(checkCollision(getMap().getCell(getYCoord() - 1, getXCoord() - 1).getCellType()))) {
				setDirectionWall("leftDown");
				System.out.println("upleft floor tile");
				System.out.println("was upLeft changing to leftDown");
			} else {
				System.out.println("upleft not floor tile");
				System.out.println("no change");
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

			// setDirectionWall("rightUp");
			System.out.println("up is not floor");
			System.out.println("was upLeft changing to rightUp");
		}
	}

	public void downRight() {
		setDx(0);
		setDy(1);
		// floor tile
		if (!(checkCollision(getMap().getCell(getYCoord() + 1, getXCoord()).getCellType()))) {
			// floor tile
			System.out.println("down floor tile");
			if (!(checkCollision(getMap().getCell(getYCoord() + 1, getXCoord() + 1).getCellType()))) {
				setDirectionWall("rightUp");
				System.out.println("down right floor tile");
				System.out.println("was downRight changing to rightUp");
			} else {
				System.out.println("downright not floor tile");
				System.out.println("no change");
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
			// setDirectionWall("leftDown");
			System.out.println("down is not a floor tile");
			System.out.println("was downRight changing to leftDown");
		}
	}

	public void leftDown() {
		setDx(-1);
		setDy(0);
		// floor tile
		if (!(checkCollision(getMap().getCell(getYCoord(), getXCoord() - 1).getCellType()))) {
			// floor tile
			System.out.println("left floor tile");
			if (!(checkCollision(getMap().getCell(getYCoord() + 1, getXCoord() - 1).getCellType()))) {
				setDirectionWall("downRight");
				System.out.println("leftdown floor tile");
				System.out.println("was leftDown changing to downRight");
			} else {
				System.out.println("leftdown is not a floor tile");
				System.out.println("no change");
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
			System.out.println("left is not a floor tile");
			// setDirectionWall("upLeft");
			System.out.println("was leftDown changing to upLeft");
		}
	}

	public void rightUp() {
		setDx(1);
		setDy(0);
		// floor tile
		if (!(checkCollision(getMap().getCell(getYCoord(), getXCoord() + 1).getCellType()))) {
			// floor tile
			System.out.println("right floor tile");
			if (!(checkCollision(getMap().getCell(getYCoord() - 1, getXCoord() + 1).getCellType()))) {
				setDirectionWall("upLeft");
				System.out.println("rightup floor tile");
				System.out.println("was rightUp changing to upLeft");
			} else {
				System.out.println("rightup is not floor tile");
				System.out.println("no change");
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
			// setDirectionWall("downRight");
			System.out.println("right is not floor tile");
			System.out.println("was rightUp changing to downRight");
		}
	}


}
