
/**
 *
 * GameState.java
 * @author Radoslav Nikolov
 * @version 1.0.0
 */

package application;

import java.util.ArrayList;

public class GameState {

    /**
     * The name of the profile.
     */
    private String name;

    /**
     * The current level of the profile.
     */
    private int currLevel;

    /**
     * The profile's time till now.
     */
    private String time;

    /**
     * The inventory of the profile.
     */
    private String items;

    /**
     * 
     */
    private String map;
    

    /**
     * Creates a gamestate object.
     *
     * @param name Profile's name.
     * @param currLevel Profile's current level.
     * @param time Player's current time.
     * @param inventory Player's inventory.
     */
    public GameState(String name, int currLevel, String time, String map, String items) {
        this.name = name;
        this.currLevel = currLevel;
        this.time = time;
        this.map = map;
        this.items = items;
    }

    public GameState() {

    }

	/**
     * Set the name of the profile.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the current level of the profile.
     *
     * @param currLevel
     */
    public void setMap(String map) {
    	this.map = map;
    }
    
    
    public void setCurrLevel(int currLevel) {
        this.currLevel = currLevel;
    }
    /**
     * Set the current time of the player.
     *
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

	public String getItems() {
		return items;
	}

	/**
	 * Simple setter for the player inventory 
	 * @param items is the ArrayList to be stored
	 */
	public void setItems(String items) {
		this.items = items;
	}

    /**
     *
     * @return The name of the profile.
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return The current level of the profile.
     */
    public int getCurrLevel() {
        return this.currLevel;
    }

    /**
     *
     * @return The current time of the profile.
     */
    public String getTime() {
        return this.time;
    }

    public String getMap() {
    	return this.map;
    }

    /**
     * The method returns a string suitable for printing.
     *
     * @return string to print out the gamestate of a profile.
     */
    public String toString() {
        String result = "";
        result += "The profile's name is: " + getName() + "\n";
        result += "The profile is at " + getCurrLevel() + " level." + "\n";
        result += "The profile's time is: " + getTime() + "\n";
        result += "The profile's inventory consists of: " + getItems() + "\n";
        return result;
    }
}
