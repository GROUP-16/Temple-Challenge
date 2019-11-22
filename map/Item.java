/**
 * Class that is responsible for all variables and operations that are specific to a Item cell
 * @author George Sims
 *
 */
public class Item extends Cell {
	private ItemType itemType;
	private String colour;
	
	/**
	 * Simple constructor that calls the super classes constructor 
	 * @param cellType Enumeration that represents the type of Cell
	 */
	public Item (CellType cellType) {
		super(cellType);
	}
	
	/**
	 * Simple getter for the Item Type
	 * @return Enumeration that represents the type of item
	 */
	public ItemType getItemType(){
		return this.itemType;
	}
	
	/**
	 * single setter for the items colour
	 * @return String that represents the items colour
	 */
	public String getColour() {
		return colour;
	}
	
	/**
	 * Simple setter that sets the item type
	 * @param itemType Enumeration that represents the item type
	 */
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	
	/**
	 * Simple setter for the Items colour
	 * @param colour String that represents the Items colour
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}
	
}
