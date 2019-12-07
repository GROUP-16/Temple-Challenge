package application;
import java.util.ArrayList;

public class EnemyDebug {

	public static void main(String[] args) {
		System.out.println("top top");
		Map map = new Map();
		map.readFile("testFile.txt");
		ArrayList<Enemy> enemies = map.getEnemies();
		
		int x = 0;
		System.out.println("top");
		while (x<30) {
			
			
			
			System.out.println(map);
			
			for (Enemy  enemyX : enemies) {
			    enemyX.move(1,1);
			}
			
			x++;
		}
	}
}
