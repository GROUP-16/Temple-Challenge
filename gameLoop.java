import java.util.ArrayList;
import java.util.Scanner;

public class gameLoop {

	public static void main(String[] args) {
		Map map = new Map();
		String filename = "testFile2.txt"; //file to read
		map.readFile(filename); //creates map, player and enemies
		Player player = map.getPlayer(); //gets the player object
		ArrayList<Enemy> enemies = map.getEnemies(); //gets the array of enemies 
		
		Scanner in = new Scanner(System.in);
		DirectionType d = null;
		System.out.println(map);
		while(!player.gameWon() && !player.isDead()) {
			String direction = in.nextLine();
			switch(direction) {
			case "w":
				d = DirectionType.up;
				break;
			case "s":
				d = DirectionType.down;
				break;
			case "a":
				d = DirectionType.left;
				break;
			case "d":
				d = DirectionType.right;
				break;
			}
			player.move(d);
			for(Enemy e : enemies) {
				e.move(player.getX(), player.getY());
			}
			System.out.println(map);
		}		

	}

}
