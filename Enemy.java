package application;


public abstract class Enemy {
	private int xCoord;
	private int yCoord;
	// dy and dx are to choose the direction the enemy is about to move in.
	// multiplying this by -1 will change the direction
	private int dy;
	private int dx;
	private int nextXCoord;
	private int nextYCoord;
	private Map map;
	
	public Enemy(int xCoord, int yCoord, int dy, int dx, Map map) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.dy = dy;
		this.dx = dx;
		this.map = map;
	}
	public int getXCoord() {
		return this.xCoord;
	}
	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	public int getYCoord() {
		return this.yCoord;
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
	
	
	public abstract void move(int playerXCoord, int playerYCoord);
		
	public abstract String getEnemyType();
	
	public void updateNext() {
		nextXCoord = xCoord + dx;
		nextYCoord = yCoord + dy;
	}

	public void executeMove() {
		setXCoord(getXCoord() + getDx());
		setYCoord(getYCoord() + getDy());
		updateNext();
	}
	public boolean checkCollision(CellType nextCell) {
		if (nextCell.equals(CellType.FLOOR_CELL)) {
			return false;
		} else {
			return true;
		}
	}





}