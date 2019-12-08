
/**
 *
 * GameState.java
 * @author Radoslav Nikolov
 * @version 1.0.0
 */

package application;

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
    private String[] inventory = new String[15];

    /**
     * Counter for the number of items in the inventory.
     */
    private int count = 0;

    /**
     * Creates a gamestate object.
     *
     * @param name Profile's name.
     * @param currLevel Profile's current level.
     * @param time Player's current time.
     * @param inventory Player's inventory.
     */
    public GameState(String name, int currLevel, String time, String inventory) {
        this.name = name;
        this.currLevel = currLevel;
        this.time = time;
        this.inventory[count] = inventory;
        count++;
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

    /**
     * Set the items of the profile's inventory.
     *
     * @param inventory
     */
    public void setInventory(String inventory) {
        this.inventory[count] = inventory;
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

    /**
     *
     * @return The items in the inventory of the profile.
     */
    public String getInventory() {
        return this.inventory[count-1];
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
        result += "The profile's inventory consists of: " + getInventory() + "\n";
        return result;
    }
}
