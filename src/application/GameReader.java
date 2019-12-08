/**
 *
 * GameReader.java
 * @author Radoslav Nikolov
 * @version 1.0.0
 */

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameReader {

    /**
     * Reads the data file used by the program and returns an ArrayList of all the saves.
     *
     * @param in The scanner of the file.
     * @return The ArrayList with all the saves.
     */
    private static ArrayList<GameState> readGame(Scanner in) {

        ArrayList<GameState> saves = new ArrayList<GameState>();

        while(in.hasNextLine()) {
            Scanner newLine = new Scanner(in.nextLine());
            newLine.useDelimiter(",|;");

            saves.add(getGameState(newLine));
        }
        in.close();
        return saves;
    }

    /**
     * Method to read the file and return an ArrayList of the saves from this file. The
     * program should handle the file not found exception here and shut down the
     * program gracefully.
     *
     * @param filename The name of the file.
     * @return ArrayList of the saves from the file.
     */
    public static ArrayList<GameState> readGame(String filename) {

        File gameFile = new File(filename);
        Scanner in = null;

        try {
            in = new Scanner(gameFile);
            in.useDelimiter(",|;");
        } catch (FileNotFoundException e) {
            System.out.print("Cannot open " + filename);
            System.exit(0);
        }

        return GameReader.readGame(in);
    }

    /**
     * Method to take the data from a line of a specific file
     * and create a save with the data provided from the line.
     * In the end, it returns the save/gamestate.
     *
     * @param newLine The scanner of the file.
     * @return The save/gamestate.
     */
    public static GameState getGameState(Scanner newLine) {

        String name = newLine.next();
        int currLevel = newLine.nextInt();
        String time = newLine.next();

        String inventory = "";
        while(newLine.hasNext()) {
            if (inventory == "") {
                inventory = newLine.next();
            } else {
                inventory += ";" + newLine.next();
            }
        }
        GameState gameState = new GameState(name, currLevel, time, inventory);

        return gameState;
    }
}
