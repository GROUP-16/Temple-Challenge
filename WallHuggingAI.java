package application;

public class WallHuggingAI extends Enemy{
	private String directionWall;
	
	public WallHuggingAI(int xCoord, int yCoord, int dy, int dx, Map map,String directionWall) {
		super(xCoord, yCoord, dy, dx, map);
		this.directionWall = directionWall;
	}
	
	public String getDirectionWall() {
		return directionWall;
	}

	public void setDirectionWall(String directionWall) {
		this.directionWall = directionWall;
	}


	public void move(int playerXCoord, int playerYCoord) {
		// states in direction and hugging wall
		// cry
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
