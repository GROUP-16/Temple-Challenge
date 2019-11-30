import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmartAI extends Enemy{
	private char[][] mapCopy;
	private final List<MapNode> open;
	private final List<MapNode> closed;
	private final List<MapNode> path;
	private MapNode now;
	private int xend, yend;
	
	public SmartAI(int yCoord, int xCoord, int dy, int dx, Map map) {
		super(xCoord,yCoord, dx, dx, map);
		this.open = new ArrayList<>();
		this.closed = new ArrayList<>();
		this.path = new ArrayList<>();
		this.now = new MapNode(null, xCoord, yCoord, 0, 0);
	}
	
	public void smartAI(int playerXCoord, int playerYCoord) {
		
		// -1 = blocked
		// 0+ = additional movement cost
		//int[][] mapCopy = { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 100, 100, 100, 0, 0 },
			//	{ 0, 0, 0, 0, 0, 100, 0, 0 }, { 0, 0, 100, 0, 0, 100, 0, 0 }, { 0, 0, 100, 0, 0, 100, 0, 0 },
				//{ 0, 0, 100, 100, 100, 100, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, };
		List<MapNode> path = findPathTo(playerXCoord, playerYCoord);
		if (path != null) {
			path.forEach((n) -> {
				System.out.print("[" + n.x + ", " + n.y + "] ");
				this.mapCopy[n.y][n.x] = 'X';
			});
			//System.out.printf("\nTotal cost: %.02f\n", path.get(path.size() - 1).g);
			
			for (char[] mapCopy_row : this.mapCopy) {
				for (char mapCopy_entry : mapCopy_row) {
					switch (mapCopy_entry) {
					case ' ':
						//System.out.print("_");
						break;
					case 'X':
						//System.out.print("*");
						break;
					default:
						//System.out.print("#");
					}
				}
				//System.out.println();
			}
		}
		
		setXCoord(path.get(1).getX());
		setYCoord(path.get(1).getY());
		path.clear();
		open.clear();
		closed.clear();
	}

	public List<MapNode> findPathTo(int xend, int yend) {
		this.xend = xend;
		this.yend = yend;
		this.closed.add(this.now);
		addNeigborsToOpenList();
		while (this.now.x != this.xend || this.now.y != this.yend) {
			if (this.open.isEmpty()) { // Nothing to examine
				return null;
			}
			this.now = this.open.get(0); // get first node (lowest f score)
			this.open.remove(0); // remove it
			this.closed.add(this.now); // and add to the closed
			addNeigborsToOpenList();
		}
		this.path.add(0, this.now);
		System.out.println(this.now.x);
		System.out.println(getXCoord());
		System.out.println(this.now.y);
		System.out.println(getYCoord());
		while (this.now.x != getXCoord() || this.now.y != getYCoord()) {
			System.out.println("ok");
			this.now = this.now.parent;
			this.path.add(0, this.now);
		}
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
		char[][] mapCopy = getMap().mapCopy().mapCharArray();
		MapNode node;
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if (x != 0 && y != 0) {
					continue; // skip if diagonal movement is not allowed
				}
				node = new MapNode(this.now, this.now.x + x, this.now.y + y, this.now.g, this.distance(x, y));
				//System.out.println("throw me off this cliff");
				if ((x != 0 || y != 0) // not this.now
						
						&& this.now.x + x >= 0 && this.now.x + x < this.mapCopy[0].length // check mapCopy boundaries
						&& this.now.y + y >= 0 && this.now.y + y < this.mapCopy.length
						&& this.mapCopy[this.now.y + y][this.now.x + x] != '#' // check if square is walkable
						&& !findNeighborInList(this.open, node) && !findNeighborInList(this.closed, node)) { // if not
						//System.out.println("fuck me gently with a chainsaw");																						// already
																												// done
					node.g = node.parent.g + 1.; // Horizontal/vertical cost = 1.0
					node.g += mapCopy[this.now.y + y][this.now.x + x]; // add movement cost for this square

					// diagonal cost = sqrt(hor_cost² + vert_cost²)
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
	}


}
