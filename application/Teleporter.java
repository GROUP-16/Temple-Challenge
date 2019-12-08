package application;
import javafx.util.Pair;

/**
 * Class that is responsible for all variables and operations that are specific for teleporter cells
 * @author George Sims
 *
 */
public class Teleporter extends Cell {
	
	private Pair<Integer,Integer> pairedPoint;
	
	/**
	 * Simple constructor that calls the super classes constructor
	 * @param cellType enumeration that represents the type of Cell
	 */
	public Teleporter(CellType cellType) {
		super(cellType);
	}
	
	/**
	 * Simple return method that returns the coordinate of the paired teleporter 
	 * @return Pair object that has the coordinate of the paired teleporter
	 */
	public Pair<Integer, Integer> getPairedPoint() {
		return pairedPoint;
	}
	
	/**
	 * Simple getter that returns the X coordinate of the paired teleporter
	 * @return X coordinate of the paired teleporter
	 */
	public int getPairX() {
		return pairedPoint.getKey();
	}
	
	/**
	 * Simple getter that returns the y coordinate of the paired teleporter
	 * @return Y coordinate of the paired teleporter
	 */
	public int getPairY() {
		return pairedPoint.getValue();
	}
	
	/**
	 * Simple setter that sets the paired point of the paired teleporter 
	 * @param x X coordinate for the paired teleporter
	 * @param y Y coordinate for the paired teleporter
	 */
	public void setPairedPoint(int x, int y) {
		Pair<Integer, Integer> pair = new Pair<Integer,Integer>(x,y);
		this.pairedPoint = pair;
	}

}
