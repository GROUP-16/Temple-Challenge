import javafx.util.Pair;

public class Teleporter extends Cell {
	
	private Pair<Integer,Integer> pairedPoint;
	
	public Teleporter(CellType cellType) {
		super(cellType);
	}
	
	public Pair<Integer, Integer> getPairedPoint() {
		return pairedPoint;
	}
	
	public int getPairX() {
		return pairedPoint.getKey();
	}
	
	public int getPairY() {
		return pairedPoint.getValue();
	}

	public void setPairedPoint(int x, int y) {
		Pair<Integer, Integer> pair = new Pair<Integer,Integer>(x,y);
		this.pairedPoint = pair;
	}

}
