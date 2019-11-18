
public class Item extends Cell {
	private ItemType itemType;
	private String colour;
	
	public Item (CellType cellType) {
		super(cellType);
	}
	
	public ItemType getItemType(){
		return this.itemType;
	}
	
	public String getColour() {
		return colour;
	}
	
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
	
}
