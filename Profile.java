
/**
 * Profile.java
 *
 * @author Radoslav Nikolov
 * @version 1.0.0
 */

package application;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Profile {

    /**
     * List of all the saves/gamestates.
     */
    private static ArrayList<GameState> saves;

    /**
     * List of all the profiles.
     */
    private static ArrayList<Profile2> profiles;

    /**
     * Method is taking a certain name and checks whether there is already profile with it.
     * If there is not a profile with such name we create the profile.
     *
     * @param name The name of the profile.
     * @throws IOException
     */
    public static void putProfile(String name) throws IOException {
        boolean exist = false;
        profiles = readProfiles("Profiles.txt");
        for (Profile2 elem : profiles) {
            if (name.equals(elem.getName())) {
                exist = true;
            }
        }

        if (exist == false) {
            Profile2 profile = new Profile2(name, 0);
            profiles.add(profile);
            FileWriter fileWriter1 = new FileWriter("Profiles.txt");

            for (Profile2 elem : profiles) {
                fileWriter1.write(elem.getName() + " " + elem.getHighestLevel() + "\n");
            }

            fileWriter1.close();

        }
    }

    /**
     * Loads the profile's data with the specified name as parameter.
     *
     * @param name The name of the profile.
     */
    public static boolean loadProfile(String name) {
        boolean exist = false;
        saves = GameReader.readGame("GameFile.txt");
        for (GameState elem : saves) {
            if (name.equals(elem.getName())) {
                System.out.println(elem.toString());
                System.out.println("Profile is loaded successfully!");
                exist = true;
            }
        }

        if (!exist) {
            System.out.println("Save with this name doesn't exist!");
        }

        return exist;

    }

    /**
     * Delete the profile and his saves with the specified name as parameter.
     *
     * @param name The name of the profile.
     * @throws IOException
     */
    public static void deleteProfile(String name) throws IOException {
        profiles = readProfiles("Profiles.txt");
        saves = GameReader.readGame("GameFile.txt");
        profiles.removeIf(item -> item.getName().equals(name));

        saves.removeIf(elem -> elem.getName().equals(name));

        FileWriter fileWriter2 = new FileWriter("Profiles.txt");
        for (Profile2 elem : profiles) {
            fileWriter2.write(elem.getName() + " " + elem.getHighestLevel() + "\n");
        }

        fileWriter2.close();

        FileWriter fileWriter3 = new FileWriter("GameFile.txt");
        for (GameState elem : saves) {
            fileWriter3.write(elem.getName() + "," + elem.getCurrLevel() + "," + elem.getTime() + "," + elem.getInventory() + "\n");
        }
        fileWriter3.close();
    }

    /**
     * Reads the data file used by the program and returns an ArrayList of all the profiles.
     *
     * @param in The scanner of the file.
     * @return The ArrayList with all the profiles.
     */
    private static ArrayList<Profile2> readProfiles(Scanner in) throws IOException {

        ArrayList<Profile2> profiles = new ArrayList<Profile2>();

        while (in.hasNextLine()) {

            Scanner newLine = new Scanner(in.nextLine());

            profiles.add(getProfile(newLine));

        }
        in.close();

        return profiles;

    }

    /**
     * Method to read the file and return an ArrayList of the profiles from this file. The
     * program should handle the file not found exception here and shut down the
     * program gracefully.
     *
     * @param filename The name of the file.
     * @return ArrayList of the profiles from the file.
     */
    public static ArrayList<Profile2> readProfiles(String filename) throws IOException {
        File profileFile = new File(filename);
        Scanner in = null;

        try {
            in = new Scanner(profileFile);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open " + filename);
            System.exit(0);
        }

        return Profile.readProfiles(in);
    }

    /**
     * Method to take the data from a line of a specific file
     * and create a profile with the data provided from the line.
     * In the end, it returns the profile.
     *
     * @param newLine The scanner of the file.
     * @return The profile.
     */
    public static Profile2 getProfile(Scanner newLine) {

        String name = newLine.next();
        int highestLevel = newLine.nextInt();

        Profile2 profile = new Profile2(name, highestLevel);

        return profile;
    }

    /**
     * Returns all the current profiles.
     *
     * @return All the profiles.
     * @throws IOException
     */
    public static String getProfiles() throws IOException {
        profiles = readProfiles("Profiles.txt");
        for (Profile2 item : profiles) {
            System.out.println(item.toString());
        }
        return "";
    }

    /**
     * Saves profile with all his attributes in the GameFile.txt.
     *
     * @param name Profile's name.
     * @param currLevel Profile's current level.
     * @param time Profile's time.
     * @param inventory Profile's inventory.
     * @throws IOException
     */
    public static void saveGame(String name, int currLevel, String time, String inventory) throws IOException {
        saves = GameReader.readGame("GameFile.txt");

        GameState gamestate = new GameState(name, currLevel, time, inventory);

        FileWriter fileWriter = new FileWriter("GameFile.txt");
        FileWriter fileWriter2 = new FileWriter("GameFile.txt", true);

        boolean exist = false;
        GameState remove = new GameState();

        for (GameState item : saves) {
            if (item.getName().equals(name)) {
                remove = item;
                exist = true;
            }
        }


        if (exist == false) {
            saves.add(gamestate);
            for (GameState elem : saves) {
                fileWriter.write(elem.getName() + "," + elem.getCurrLevel() + "," + elem.getTime() + "," + elem.getInventory() + "\n");
            }
        } else {
            saves.remove(remove);
            saves.add(gamestate);
            fileWriter2.write(gamestate.getName() + "," + gamestate.getCurrLevel() + "," + gamestate.getTime() + "," + gamestate.getInventory() + "\n");

        }
        fileWriter.close();
        fileWriter2.close();
    }
}


