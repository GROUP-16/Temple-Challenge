package application;
import java.util.ArrayList;

public class EnemyDebug {

	public static void main(String[] args) {
		System.out.println("top top");
		Map map = new Map();
		map.readFile("Level4.txt");
		ArrayList<Enemy> enemies = map.getEnemies();
		
		int x = 0;
		System.out.println("top");
		while (x<30) {
			
			
			
			System.out.println(map);
			
			for (Enemy  enemyX : enemies) {
			    enemyX.move(1,1);
			    System.out.println(enemyX.getXCoord() +" is the x and "+enemyX.getYCoord()+" is the y");
			    System.out.println(enemyX.getEnemyType());
			}
			
			x++;
		}
	}
}
