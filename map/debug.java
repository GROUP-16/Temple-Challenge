
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
		map.readFile("testFile.txt");
		//System.out.println(map.getCell(6, 8).getCellType());
		//System.out.println(map);
		Teleporter t = (Teleporter) map.getCell(6, 7);
		System.out.println(t.getPairX());
		System.out.println(t.getPairY());
		Item i = (Item) map.getCell(8, 7);
		System.out.println(i.getItemType());
		System.out.println(i.getColour());
	}

}
