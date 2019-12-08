
/**
 *
 * Profile2.java
 * @author Radoslav Nikolov
 * @version 1.0.0
 */
package application;

public class Profile2 {

    /**
     * The name of the profile.
     */
    private String name;

    /**
     * The highest level of the profile.
     */
    private int highestLevel;

    /**
     * Creates a profile object.
     *
     * @param name Profile's name.
     * @param highestLevel Profile's highest level.
     */
    public Profile2(String name, int highestLevel) {
        this.name = name;
        this.highestLevel = highestLevel;
    }

    /**
     * Set the highest level of the profile.
     *
     * @param nLevel Profile's highest level.
     */
    public void setHighestLevel(int nLevel) {

        this.highestLevel = nLevel;
    }

    /**
     *
     * @return The highest level of the profile.
     */
    public int getHighestLevel() {

        return this.highestLevel;
    }

    /**
     *
     * @return The name of the profile.
     */
    public String getName() {
        return this.name;
    }

    /**
     * The method returns a string suitable for printing.
     *
     * @return string to print out the profile's name and highest level.
     */
    public String toString() {
        String result = "";
        result += "The profile's name is: " + getName() + "\n";
        result += "The profile is at " + getHighestLevel() + " level." + "\n";
        return result;
    }

}
