import java.util.ArrayList;

public class EnemyDebug {

	public static void main(String[] args) {
		Map map = new Map();
		map.readFile("testFile.txt");
		ArrayList<Enemy> enemies = map.getEnemies();
		Enemy E = enemies.get(0);
		//System.out.println(map);
		//System.out.println(map.getCell(0,1).getCellType());
		
		//int xCoord,int yCoord, int dy, int dx
		//Enemy E = new Enemy(1,1,1,0);
		
		//System.out.println(E.getNextXCoord());
		//System.out.println(E.getNextYCoord());
		//System.out.println(E.checkCollision(map.getCell(E.getNextXCoord(),E.getNextYCoord()).getCellType()));
		//System.out.println(map.getCell(E.getNextXCoord(),E.getNextYCoord()).getCellType());
		
		//System.out.println(E.getNextYCoord() + " " +E.getNextXCoord());
		////
		//E.setDy(1);
		//System.out.println(E.getDx() + " dx dy " + E.getDy());
		//System.out.println(E.getXCoord()+ "x y "+ E.getYCoord());
		//System.out.println(E.getNextXCoord()+ " next x next y " + E.getNextYCoord());
		//System.out.println(map.getCell(E.getNextXCoord(),E.getNextYCoord()).getCellType()+ " next cell");
		
		int x = 0;
		System.out.println("top");
		while (x<30) {
			//if (map.getCell(E.getNextYCoord(),E.getNextXCoord()).getCellType().equals(CellType.FLOOR_CELL)) {
			//	System.out.println("ok");
			//}
			/*
			System.out.println(E.getDx() + " dx dy " + E.getDy());
			System.out.println(E.getXCoord()+ "x y "+ E.getYCoord());
			System.out.println(E.getNextXCoord()+ " next x next y " + E.getNextYCoord());
			System.out.println(map.getCell(E.getNextXCoord(),E.getNextYCoord()).getCellType()+ " next cell");
			*/
			//E.move(1,1);
			//System.out.println(map);
			
			
			//if(E instanceof SmartAI) {
			//	E = new SmartAI(E.getYCoord(),E.getXCoord(), 0, 0, map);
			//}
			
			
			System.out.println(map);
			
			E.move(1,1);
			
			//E.mapFill();
			//E.simpleLineAI();
			//E.dumbPlayerFinderAI(1,1);
			//E.wallHuggerAI();
			//E = new Enemy(E.getYCoord(), E.getXCoord(),0,0,"upLeft",map);
			
			//System.out.println(map);
			
			
			
			
			//System.out.println(E.getNextYCoord());
			//System.out.println(E.getNextXCoord());
			//System.out.println(E.getxCoord() + " x coord");
			//System.out.println(E.getDx() + " dx and dy " +E.getDy());
			//System.out.println(E.getNextXCoord()+ " next X coord");
			//System.out.println(map.getCell(6,1).getCellType());
			//if (E.getNextXCoord() == 6) {
			//	System.out.println("next x = 6");
			//}
			//System.out.println(map.getCell(E.getNextXCoord(),E.getNextYCoord()).getCellType()+ " next cell");
			//
			x++;
		}
	}
}
