// Node class for convienience
	public class MapNode implements Comparable<Object> {
		public MapNode parent;
		public int x, y;
		public double g;
		public double h;

		MapNode(MapNode parent, int xpos, int ypos, double g, double h) {
			this.parent = parent;
			this.x = xpos;
			this.y = ypos;
			this.g = g;
			this.h = h;
		}

		public int getX() {
			return x;
		}


		public int getY() {
			return y;
		}


		// Compare by f value (g + h)
		@Override
		public int compareTo(Object o) {
			MapNode that = (MapNode) o;
			return (int) ((this.g + this.h) - (that.g + that.h));
		}
	}