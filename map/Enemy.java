
public class Enemy {
	private int xCoord;
	private int yCoord;
	//dy and dx are to choose the direction the enemy is about to move in. multiplying this by -1 will change the direction
	private int dy;
	private int dx;
	private int nextXCoord;
	private int nextYCoord;
	public Enemy(int xCoord,int yCoord, int dy, int dx ) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.dy = dy;
		this.dx = dx;
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
	public int getxCoord() {
		return xCoord;
	}
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	public int getyCoord() {
		return yCoord;
	}
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	public void updateNext() {
		nextXCoord = xCoord+dx;
		nextYCoord = yCoord+dy;
	}
	public void changeDirection() {
		setDy(getDy()*-1);
		setDx(getDx()*-1);
	}
	public void executeMove() {
		//System.out.println("yeet");
		//System.out.println("help "+ getyCoord()+ " " + getxCoord());
		setxCoord(getxCoord()+getDx());
		setyCoord(getyCoord()+getDy());
		updateNext();
		//System.out.println("getnextyx " + getNextYCoord() + " " + getNextXCoord());
		//System.out.println("help "+ getyCoord()+ " " + getxCoord());
	}
	//map.getCell(getNextXCoord(), getNextYCoord()).getCellType()
	public boolean checkCollision(CellType nextCell) {
		if (nextCell.equals(CellType.FLOOR_CELL)){
			return false;
		}
		else {
			return true;
		}
	}
	
	public void simpleLineAI(CellType nextCell) {
		if (checkCollision(nextCell)) {
			changeDirection();
		}
		executeMove();
	}
	//public int greaterMagnitudeCalc(int playerXCoord, int playerYCoord) {
	//	if () {
	//}
	
	public void dumbPlayerFinderAI(int playerXCoord, int playerYCoord, CellType nextCell) {
		
			
		
	}
	
	public int getNextXCoord() {
		return nextXCoord;
	}
	public int getNextYCoord() {
		return nextYCoord;
	}


}
