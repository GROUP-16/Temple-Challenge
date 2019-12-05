import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmartAI extends Enemy {
	private Cell[][] mapCopy;
	private List<MapNode> open;
	private List<MapNode> closed;
	private List<MapNode> path;
	private MapNode now;
	private int xend, yend;

	public SmartAI(int xCoord, int yCoord, int dy, int dx, Map map) {
		super(xCoord, yCoord, dx, dx, map);
		this.open = new ArrayList<>();
		this.closed = new ArrayList<>();
		this.path = new ArrayList<>();
		this.now = new MapNode(null, xCoord, yCoord, 0, 0);
		this.mapCopy = map.getMap();
	}

	public void move(int playerXCoord, int playerYCoord) {
		findPathTo(playerXCoord, playerYCoord);

		// System.out.println(path.get(1).getX()+" "+path.get(1).getY());
		if (this.path.isEmpty()) {
			List<String> randomMoveList = new ArrayList<String>();

			if (!(checkCollision(getMap().getCell(getYCoord()+1, getXCoord()).getCellType()))) {
				randomMoveList.add("Down");
				
			}
			if (!(checkCollision(getMap().getCell(getYCoord()-1, getXCoord()).getCellType()))) {
				randomMoveList.add("Up");
				
			}
			if (!(checkCollision(getMap().getCell(getYCoord(), getXCoord()+1).getCellType()))) {
				randomMoveList.add("Right");
				
			}
			if (!(checkCollision(getMap().getCell(getYCoord(), getXCoord()-1).getCellType()))) {
				randomMoveList.add("Left");
				
			}
			String place = randomMoveList.get((int)(Math.random() * ((randomMoveList.size() - 0))));
			System.out.println(place);
			switch(place) {
			case "Down":
				this.setYCoord(getYCoord()+1);
				this.setXCoord(getXCoord());
				break;
			case "Up":
				this.setYCoord(getYCoord()-1);
				this.setXCoord(getXCoord());
				break;
			case "Right":
				this.setYCoord(getYCoord());
				this.setXCoord(getXCoord()+1);
				break;
			case "Left":
				this.setYCoord(getYCoord());
				this.setXCoord(getXCoord()-1);
				break;
			default:
				;
			}
		}

		if (!path.isEmpty()) {
			this.path.remove(0);
			//System.out.println(this.path.toString());
			if (!path.isEmpty()) {
				//System.out.println(this.path.get(0).getY() +" "+this.path.get(0).getX());

				this.setXCoord(this.path.get(0).getX());
				this.setYCoord(this.path.get(0).getY());
			}
		}
		this.path.clear();
		this.open.clear();
		this.closed.clear();
	}

	public List<MapNode> findPathTo(int xend, int yend) {
		this.xend = xend;
		this.yend = yend;
		this.closed.add(this.now);
		addNeigborsToOpenList();
		while (this.now.x != this.xend || this.now.y != this.yend) {
			if (this.open.isEmpty()) { // Nothing to examine
				//System.out.println("test");
				return null;
			}
			this.now = this.open.get(0); // get first node (lowest f score)
			this.open.remove(0); // remove it
			this.closed.add(this.now); // and add to the closed
			addNeigborsToOpenList();
			// System.out.println("help");
		}
		this.path.add(0, this.now);
		// System.out.println(this.now.x);
		// System.out.println(this.getXCoord());
		// System.out.println(this.now.y);
		// System.out.println(this.getYCoord());
		//boolean check = true;
		while ((this.now.x != this.getXCoord() || this.now.y != this.getYCoord())) {// && check) {
			/*
			System.out.println(this.now.x);
			System.out.println(this.getXCoord());
			System.out.println(this.now.y);
			System.out.println(this.getYCoord());

			System.out.println(this.path);
			*/
			this.now = this.now.parent;
			this.path.add(0, this.now);
			/*
			if (this.now.x == this.getXCoord() || this.now.y == this.getYCoord()) {
				check = false;
			} else {
				this.now = this.now.parent;
				this.path.add(0, this.now);
			}
			*/
			/*
			 * System.out.println("after "+this.now.x);
			 * System.out.println("after "+this.getXCoord());
			 * System.out.println("after "+this.now.y);
			 * System.out.println("after "+this.getYCoord()); System.out.println(this.path);
			 */
		}
		// System.out.println(this.path);
		// System.out.println("help");
		return this.path;
	}

	/*
	 ** Looks in a given List<> for a node
	 **
	 ** @return (bool) NeightborInListFound
	 */
	private static boolean findNeighborInList(List<MapNode> array, MapNode node) {
		return array.stream().anyMatch((n) -> (n.x == node.x && n.y == node.y));
	}

	/*
	 ** Calulate distance between this.now and xend/yend
	 **
	 ** @return (int) distance
	 */
	private double distance(int dx, int dy) {
		return Math.abs(this.now.x + dx - this.xend) + Math.abs(this.now.y + dy - this.yend); // else return "Manhattan
																								// distance"
	}

	private void addNeigborsToOpenList() {
		// System.out.println(mapCopy[1]);
		MapNode node;
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if (x != 0 && y != 0) {
					continue; // skip if diagonal movement is not allowed
				}
				node = new MapNode(this.now, this.now.x + x, this.now.y + y, this.now.g, this.distance(x, y));
				//System.out.println("throw me off this cliff" +(this.now.y + y) +" y x "+ (this.now.x + x));
				if ((x != 0 || y != 0) // not this.now

						&& this.now.x + x >= 0 && this.now.x + x < this.mapCopy[0].length // check mapCopy boundaries
						&& this.now.y + y >= 0 && this.now.y + y < this.mapCopy.length
						&& this.mapCopy[this.now.y + y][this.now.x + x].getCellType() == CellType.FLOOR_CELL // check if
																												// square
																												// is
																												// walkable
						&& !findNeighborInList(this.open, node) && !findNeighborInList(this.closed, node)) { // if not
					//System.out.println(this.now.stringTo()); // already
					// done
					node.g = node.parent.g + 1.; // Horizontal/vertical cost = 1.0

					// diagonal cost = sqrt(hor_cost + vert_cost)
					// in this example the cost would be 12.2 instead of 11
					/*
					 * if (diag && x != 0 && y != 0) { node.g += .4; // Diagonal movement cost = 1.4
					 * }
					 */
					this.open.add(node);
				}
			}
		}
		Collections.sort(this.open);
	}/*
		 * public static void main(String[] args) { char[][] map = {
		 * {'#','#','#','#','#'}, {'#',' ',' ',' ','#'}, {'#',' ','#',' ','#'}, {'#','
		 * ','#',' ','#'}, {'#',' ','#','E','#'}, {'#','#','#','#','#'}, }; //SmartAI E
		 * = new SmartAI(4,3,0,0,map); }
		 */

}
