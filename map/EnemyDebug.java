import java.util.ArrayList;

public class EnemyDebug {

	public static void main(String[] args) {
		Map map = new Map();
		map.readFile("testFile.txt");
		ArrayList<Enemy> enemies = map.getEnemies();
		Enemy E = enemies.get(0);
		System.out.println(map);
		//System.out.println(map.getCell(0,1).getCellType());
		
		//int xCoord,int yCoord, int dy, int dx
		//Enemy E = new Enemy(1,1,1,0);
		
		System.out.println(E.getNextXCoord());
		System.out.println(E.getNextYCoord());
		System.out.println(E.checkCollision(map.getCell(E.getNextXCoord(),E.getNextYCoord()).getCellType()));
		System.out.println(map.getCell(E.getNextXCoord(),E.getNextYCoord()).getCellType());
		
		//System.out.println(E.getNextYCoord() + " " +E.getNextXCoord());
		////
		E.setDx(1);
		while (true) {
			//System.out.println(E.getyCoord() + " " + E.getxCoord());
			//System.out.println(map.getCell(E.getNextYCoord(),E.getNextXCoord()).getCellType());
			E.simpleLineAI(map.getCell(E.getNextYCoord(),E.getNextXCoord()).getCellType());
			System.out.println(map);
			////
		}
		
		//System.out.println(map.getCell(E.getNextYCoord(),E.getNextXCoord()).getCellType());
		//System.out.println(map.getCell(E.getNextYCoord(),E.getNextXCoord()).getCellType());
		//System.out.println(E.getNextYCoord() + " " +E.getNextXCoord());
		//System.out.println(E.getxCoord() + " " + E.getyCoord());
	}

}
