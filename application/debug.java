package application;


import java.util.ArrayList;
import java.util.Scanner;

public class debug {

	public static void main(String[] args) {
		//Teleporter T = new Teleporter(CellType.TELEPORTER);
		//T.setPairedPoint(3,4);
		//System.out.println(T.getPairY());
		
		Map map = new Map();
		//map.createCell(CellType.ITEM_CELL, 0, 0);
		//Item item = (Item) map.getCell(0, 0);
		//item.setItemType(ItemType.KEY);
		//item.setColour("Blue");
		//System.out.println(item.getColour());
		//System.out.println(item.getItemType());
		//Item t = (Item) map.getCell(0, 0);
		//System.out.println(t.getColour());
		//.out.println(t.getItemType());
		map.readFile("testFile2.txt");
		//System.out.println(map.getCell(6, 8).getCellType());
		/**
		System.out.println(map);
		Teleporter t = (Teleporter) map.getCell(6, 7);
		System.out.println(t.getPairX());
		System.out.println(t.getPairY());
		Item i = (Item) map.getCell(8, 7);
		System.out.println(i.getItemType());
		System.out.println(i.getColour());
		Player p = map.getPlayer();
		ArrayList<Item> items = p.getItems();
		for (Item temp : items) {
			System.out.println(temp.getItemType());
		}
		Map m = map.mapCopy();
		m.clearMap();
		System.out.println("New");
		System.out.println(m);
		System.out.println("OG");
		System.out.println(map);
		**/
		Player p = map.getPlayer();

		Scanner in = new Scanner(System.in);
		DirectionType d = null;
		Door door = (Door) map.getCell(2, 5);
		System.out.println(door.getDoorType());
		System.out.println(door.getCondition());
		System.out.println(map);
		while(!p.gameWon() && !p.isDead()) {
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
			p.move(d);
			System.out.println(map);
			//System.out.println(p.checkInventory(ItemType.KEY, "RED"));
			//p.removeItem(ItemType.KEY, "RED");
			//System.out.println(p.checkInventory(ItemType.KEY, "RED"));
		}
		
	}

}
