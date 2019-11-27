import java.util.ArrayList;

public class Player {
	private int x;
	private int y;
	private ArrayList<Item> items;
	
	
	public Player(int x, int y, ArrayList<Item> items) {
		this.x = x;
		this.y = y;
		this.items = items;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public ArrayList<Item> getItems() {
		return items;
	}


	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}



}
