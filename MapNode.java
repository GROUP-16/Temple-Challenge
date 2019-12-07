/**
 * @author rosettacode
 * https://rosettacode.org/wiki/A*_search_algorithm#Java
 * Copyright (c)  2019 Alex Masood.
  Permission is granted to copy, distribute and/or modify this document
  under the terms of the GNU Free Documentation License, Version 1.2
  or any later version published by the Free Software Foundation;
  with no Invariant Sections, no Front-Cover Texts, and no Back-Cover
  Texts.  A copy of the license is included in the section entitled "GNU
  Free Documentation License".
 * GNU Free Documentation License
   https://www.gnu.org/licenses/old-licenses/fdl-1.2.html
 */
package application;
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
		
		public String stringTo() {
			String string = "";
			string += "X: " + this.x;
			string += " Y: " + this.y;
			if(this.parent != null) {
				string += " parent X: " + this.parent.getX();
				string += " parent Y: " + this.parent.getY();
			}
			return string;
		}

		// Compare by f value (g + h)
		@Override
		public int compareTo(Object o) {
			MapNode that = (MapNode) o;
			return (int) ((this.g + this.h) - (that.g + that.h));
		}
	}