
public class Enemy {
	private int xCoord;
	private int yCoord;
	//dy and dx are to choose the direction the enemy is about to move in. multiplying this by -1 will change the direction
	private int dy;
	private int dx;
	private int nextXCoord;
	private int nextYCoord;
	private Map map;
	private String directionWall;
	private boolean firstMove = true;
	public Enemy(int xCoord,int yCoord, int dy, int dx, String directionWall, Map map ) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.dy = dy;
		this.dx = dx;
		this.map = map;
		this.directionWall = directionWall;
		updateNext();
	}
	public int getDy() {
		return dy;
	}
	public void setDy(int dy) {
		this.dy = dy;
	}
	public int getDx() {
		return dx;
	}
	public void setDx(int dx) {
		this.dx = dx;
	}
	public int getXCoord() {
		return xCoord;
	}
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	public int getYCoord() {
		return yCoord;
	}
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	public String getDirectionWall() {
		return directionWall;
	}
	public void setDirectionWall(String directionWall) {
		this.directionWall = directionWall;
	}
	public void updateNext() {
		nextXCoord = xCoord+dx;
		nextYCoord = yCoord+dy;
	}
	private void changeDirection() {
		setDy(getDy()*-1);
		setDx(getDx()*-1);
	}
	public void executeMove() {
		//System.out.println("yeet");
		//System.out.println("help "+ getYCoord()+ " " + getXCoord());
		setxCoord(getXCoord()+getDx());
		setyCoord(getYCoord()+getDy());
		updateNext();
		//System.out.println("getnextyx " + getNextYCoord() + " " + getNextXCoord());
		//System.out.println("help "+ getYCoord()+ " " + getXCoord());
	}
	//map.getCell(getNextXCoord(), getNextYCoord()).getCellType()
	public boolean checkCollision(CellType nextCell) {
		if (nextCell.equals(CellType.FLOOR_CELL)){
			return false;
		}
		else {
			//System.out.println("end my suffering");
			//System.out.println(nextCell);
			return true;
		}
	}
	
	public void simpleLineAI() {
		if (checkCollision(map.getCell(getNextXCoord(),getNextYCoord()).getCellType())) {
			if (checkCollision(map.getCell(getXCoord()+getDx()*-1,getYCoord()+getDy()*-1).getCellType())) {
				setDy(0);
				setDx(0);
			}
			changeDirection();
			
		}
		executeMove();
	}
	public void dumbPlayerFinderAI2(int playerXCoord, int playerYCoord) {
		//close x distance until even or cant move
		//close y distance
	}
	
	public void dumbPlayerFinderAI(int playerXCoord, int playerYCoord) {
		//System.out.println("yeet45");
		if (playerXCoord == getXCoord() && playerYCoord == getYCoord()) {
			System.out.println("done");
			setDy(0);
			setDx(0);
		}
		if (Math.abs(playerXCoord - getXCoord())>Math.abs(playerYCoord - getYCoord())) {
			//System.out.println(Math.abs(playerXCoord - getXCoord())>Math.abs(playerYCoord - getYCoord()));
			//System.out.println("player x and ai x: "+playerXCoord +" " + getXCoord());
			//System.out.println("player y and ai y: "+playerYCoord +" " + getYCoord());
			if ((playerXCoord == getXCoord())||(checkCollision(map.getCell(getNextXCoord(),getNextYCoord()).getCellType()))) {
				System.out.println("1");
				setDx(0);
				setDy(0);
			}
			else if (playerXCoord > getXCoord() && (!(checkCollision(map.getCell(getXCoord()+1,getYCoord()).getCellType())))) {
				System.out.println("2");
				setDx(1);
				setDy(0);
			}
			else if ((playerXCoord < getXCoord()) && (!(checkCollision(map.getCell(getXCoord()-1,getYCoord()).getCellType())))) {
				System.out.println("3");
				setDx(-1);
				setDy(0);
			}
			executeMove();
		}
		else {
			//System.out.println(Math.abs(playerXCoord - getXCoord())>Math.abs(playerYCoord - getYCoord()));
			//System.out.println("player x and ai x: "+playerXCoord +" " + getXCoord());
			//System.out.println("player y and ai y: "+playerYCoord +" " + getYCoord());
			if ((playerYCoord == getYCoord())||(checkCollision(map.getCell(getNextXCoord(),getNextYCoord()).getCellType()))) {
				System.out.println("4");
				setDx(0);
				setDy(0);
			}
			else if ((playerYCoord > getYCoord()) && (!(checkCollision(map.getCell(getXCoord(),getYCoord()+1).getCellType())))) {
				System.out.println("5");
				setDx(0);
				setDy(1);
			}
			else if ((playerYCoord < getYCoord()) && (!(checkCollision(map.getCell(getXCoord(),getYCoord()-1).getCellType())))) {
				System.out.println("6");
				setDx(0);
				setDy(-1);
			}
			executeMove();
		}
	}
	public void wallHuggerAI() {
		//states in direction and hugging wall
		//cry
		if (firstMove == true) {
			firstMove = false;
			int temp = xCoord;
			xCoord = yCoord;
			yCoord = temp;
			System.out.println("youch//////////////////////////////////////");
		}
		System.out.println(firstMove);
		System.out.println(getYCoord() + " for y and x is "+ getXCoord());
		System.out.println("ok");
		switch(getDirectionWall()){
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
		System.out.println("was "+getYCoord() + " for y and x is "+ getXCoord());
		executeMove();
		System.out.println("is now "+getYCoord() + " for y and x is "+ getXCoord());
		
		
	}
	public void upLeft() {
		setDx(0);
		setDy(-1);
		//floor tile
		
		if (!(checkCollision(map.getCell(getYCoord()-1,getXCoord()).getCellType()))) {
			//floor tile
			System.out.println("up floor tile");
			if(!(checkCollision(map.getCell(getYCoord()-1,getXCoord()-1).getCellType()))){
				setDirectionWall("leftDown");
				System.out.println("upleft floor tile");
				System.out.println("was upLeft changing to leftDown");
			}
			else {
				System.out.println("upleft not floor tile");
				System.out.println("no change");
			}
		}
		else {
			if (!(checkCollision(map.getCell(getYCoord(),getXCoord()+1).getCellType()))) {
				setDx(1);
				setDy(0);
			}
			else {
				setDx(0);
				setDy(0);
				setDirectionWall("downRight");
			}
			
			
			//setDirectionWall("rightUp");
			System.out.println("up is not floor");
			System.out.println("was upLeft changing to rightUp");
		}
	}
	
	public void downRight() {
		setDx(0);
		setDy(1);
		//floor tile
		if (!(checkCollision(map.getCell(getYCoord()+1,getXCoord()).getCellType()))) {
			//floor tile
			System.out.println("down floor tile");
			if(!(checkCollision(map.getCell(getYCoord()+1,getXCoord()+1).getCellType()))){
				setDirectionWall("rightUp");
				System.out.println("down right floor tile");
				System.out.println("was downRight changing to rightUp");
			}
			else {
				System.out.println("downright not floor tile");
				System.out.println("no change");
			}
		}
		else {
			if (!(checkCollision(map.getCell(getYCoord(),getXCoord()-1).getCellType()))) {
				setDx(-1);
				setDy(0);
			}
			else {
				setDirectionWall("upLeft");
				setDx(0);
				setDy(0);
			}
			//setDirectionWall("leftDown");
			System.out.println("down is not a floor tile");
			System.out.println("was downRight changing to leftDown");
		}
	}
	
	public void leftDown() {
		setDx(-1);
		setDy(0);
		//floor tile
		if (!(checkCollision(map.getCell(getYCoord(),getXCoord()-1).getCellType()))) {
			//floor tile
			System.out.println("left floor tile");
			if(!(checkCollision(map.getCell(getYCoord()+1,getXCoord()-1).getCellType()))){
				setDirectionWall("downRight");
				System.out.println("leftdown floor tile");
				System.out.println("was leftDown changing to downRight");
			}
			else {
				System.out.println("leftdown is not a floor tile");
				System.out.println("no change");
			}
		}
		else {
			if (!(checkCollision(map.getCell(getYCoord()-1,getXCoord()).getCellType()))) {
				setDx(0);
				setDy(-1);
			}
			else {
				setDx(0);
				setDy(0);
				setDirectionWall("rightUp");
			}
			System.out.println("left is not a floor tile");
			//setDirectionWall("upLeft");
			System.out.println("was leftDown changing to upLeft");
		}
	}
	public void rightUp() {
		setDx(1);
		setDy(0);
		//floor tile
		if (!(checkCollision(map.getCell(getYCoord(),getXCoord()+1).getCellType()))) {
			//floor tile
			System.out.println("right floor tile");
			if(!(checkCollision(map.getCell(getYCoord()-1,getXCoord()+1).getCellType()))){
				setDirectionWall("upLeft");
				System.out.println("rightup floor tile");
				System.out.println("was rightUp changing to upLeft");
			}
			else {
				System.out.println("rightup is not floor tile");
				System.out.println("no change");
			}
		}
		else {
			if (!(checkCollision(map.getCell(getYCoord()+1,getXCoord()).getCellType()))) {
				setDx(0);
				setDy(1);
			}
			else {
				setDx(0);
				setDy(0);
				setDirectionWall("leftDown");
			}
			//setDirectionWall("downRight");
			System.out.println("right is not floor tile");
			System.out.println("was rightUp changing to downRight");
		}
	}
	
	
	public int getNextXCoord() {
		return nextXCoord;
	}
	public int getNextYCoord() {
		return nextYCoord;
	}


}
