/**
 * @author rosettacode
 * @author Alex Masood
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
	private EnemyType type =EnemyType.SMART;

	public SmartAI(int xCoord, int yCoord, int dy, int dx, Map map) {
		super(xCoord, yCoord, dx, dx, map);
		this.open = new ArrayList<>();
		this.closed = new ArrayList<>();
		this.path = new ArrayList<>();
		this.now = new MapNode(null, xCoord, yCoord, 0, 0);
		this.mapCopy = map.getMap();
	}
	
	public EnemyType getEnemyType() {
		return type;
	}
	/**
	 * @param int player x and y coordinate
	 * an attempt to get a path from the enemy to the player is first done
	 * if no path can be found then the enemy walks in a random direction that is legal
	 */
	public void move(int playerXCoord, int playerYCoord,Map map1) {
		this.mapCopy = map1.getMap();
		findPathTo(playerXCoord, playerYCoord);

		if (this.path.isEmpty()) {
			List<String> randomMoveList = new ArrayList<String>();

			if (!(checkCollision(map1.getCell(getYCoord()+1, getXCoord()).getCellType()))) {
				randomMoveList.add("Down");
				
			}
			if (!(checkCollision(map1.getCell(getYCoord()-1, getXCoord()).getCellType()))) {
				randomMoveList.add("Up");
				
			}
			if (!(checkCollision(map1.getCell(getYCoord(), getXCoord()+1).getCellType()))) {
				randomMoveList.add("Right");
				
			}
			if (!(checkCollision(map1.getCell(getYCoord(), getXCoord()-1).getCellType()))) {
				randomMoveList.add("Left");
				
			}
			String place = randomMoveList.get((int)(Math.random() * ((randomMoveList.size() - 0))));
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
			if (!path.isEmpty()) {

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
				return null;
			}
			this.now = this.open.get(0); // get first node (lowest f score)
			this.open.remove(0); // remove it
			this.closed.add(this.now); // and add to the closed
			addNeigborsToOpenList();
		}
		this.path.add(0,this.now);
		while ((this.now.x != this.getXCoord() || this.now.y != this.getYCoord())) {
			this.now = this.now.parent;
			this.path.add(0, this.now);
		}
		return this.path;
	}

	/**
	 ** Looks in a given List<> for a node
	 **
	 ** @return (bool) NeightborInListFound
	 */
	private static boolean findNeighborInList(List<MapNode> array, MapNode node) {
		return array.stream().anyMatch((n) -> (n.x == node.x && n.y == node.y));
	}

	/**
	 ** Calulate distance between this.now and xend/yend
	 **
	 ** @return (int) distance
	 */
	private double distance(int dx, int dy) {
		return Math.abs(this.now.x + dx - this.xend) + Math.abs(this.now.y + dy - this.yend); // else return "Manhattan																						// distance"
	}

	private void addNeigborsToOpenList() {
		MapNode node;
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if (x != 0 && y != 0) {
					continue; // skip if diagonal movement is not allowed
				}
				node = new MapNode(this.now, this.now.x + x, this.now.y + y, this.now.g, this.distance(x, y));
				if ((x != 0 || y != 0) // not this.now

						&& this.now.x + x >= 0 && this.now.x + x < this.mapCopy[0].length // check mapCopy boundaries
						&& this.now.y + y >= 0 && this.now.y + y < this.mapCopy.length
						&& this.mapCopy[this.now.y + y][this.now.x + x].getCellType() == CellType.FLOOR_CELL // check if
																												// square
																												// is
																												// walkable
						&& !findNeighborInList(this.open, node) && !findNeighborInList(this.closed, node)) { // if not

					node.g = node.parent.g + 1.; // Horizontal/vertical cost = 1.0

					this.open.add(node);
				}
			}
		}
		Collections.sort(this.open);
	}
}