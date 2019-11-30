

public class Enemy {
	private int xCoord;
	private int yCoord;
	// dy and dx are to choose the direction the enemy is about to move in.
	// multiplying this by -1 will change the direction
	private int dy;
	private int dx;
	private int nextXCoord;
	private int nextYCoord;
	private Map map;
	
	public Enemy(int yCoord, int xCoord, int dy, int dx, Map map) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.dy = dy;
		this.dx = dx;
		this.map = map;
		//this.directionWall = directionWall;
		//this.mapCopy = map.mapCopy().mapCharArray();
	}
	public int getXCoord() {
		return xCoord;
	}
	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	public int getYCoord() {
		return yCoord;
	}
	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;
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
	public int getNextXCoord() {
		return nextXCoord;
	}
	public void setNextXCoord(int nextXCoord) {
		this.nextXCoord = nextXCoord;
	}
	public int getNextYCoord() {
		return nextYCoord;
	}
	public void setNextYCoord(int nextYCoord) {
		this.nextYCoord = nextYCoord;
	}
	public Map getMap() {
		return map;
	}
	
	
	
	
	
	public void updateNext() {
		nextXCoord = xCoord + dx;
		nextYCoord = yCoord + dy;
	}

	public void executeMove() {
		setXCoord(getXCoord() + getDx());
		setYCoord(getYCoord() + getDy());
		updateNext();
		// System.out.println("getnextyx " + getNextYCoord() + " " + getNextXCoord());
		// System.out.println("help "+ getYCoord()+ " " + getXCoord());
	}
	public boolean checkCollision(CellType nextCell) {
		if (nextCell.equals(CellType.FLOOR_CELL)) {
			return false;
		} else {
			// System.out.println("end my suffering");
			// System.out.println(nextCell);
			return true;
		}
	}





}