package application;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 * 
 * @author Radoslav
 */
public class Profile {
    private String name;
    private int highestLevel = 0;

    private static HashMap<String, Integer> mapProfiles = new HashMap<String, Integer>();
    /**
     * Profile constructor 
     * @param name The name of the profile that is being made
     * @param highestLevel The profiles highest level 
     * @throws IOException 
     */
    public Profile(String name, int highestLevel) throws IOException {
        this.name = name;
        setHighestLevel(highestLevel);
        if (mapProfiles.get(name) == null) {
            mapProfiles.put(name, highestLevel);

            FileWriter fileWriter1 = new FileWriter("Profiles.txt");

            for (String elem : mapProfiles.keySet()) {
                fileWriter1.write(elem + " " + mapProfiles.get(elem) + "\n");
            }

            fileWriter1.close();

        }

    }
    /**
     * Method to set the highest Level of the profile 
     * @param nLevel Integer that represents the highest level reached
     */
    public void setHighestLevel(int nLevel) {

        this.highestLevel = nLevel;
    }
    /**
     * Simple return of the highest Level
     * @return returns a integer that represents the profiles highest level
     */
    public int getHighestLevel() {

        return this.highestLevel;
    }

    public void loadProfile(String name) {
        System.out.println("Profile is loaded successfully!");
    }

    /**
     * Method to delete a profile 
     * @param name String representing the name of the profile that is to be deleted 
     * @throws IOException
     */
    public static void deleteProfile(String name) throws IOException {
        for (String name2 : mapProfiles.keySet()) {
            if (name.equals(name2)) {
                mapProfiles.remove(name);
            }
        }

        FileWriter fileWriter2 = new FileWriter("Profiles.txt");

        for (String elem : mapProfiles.keySet()) {
            fileWriter2.write(elem + " " + mapProfiles.get(elem) + "\n");
        }

        fileWriter2.close();
    }
    /**
     * Method to read profiles from the file system and to add them into the gui profile list 
     * @param in A scanner that has the name and the highest level reached by the profile 
     * @throws IOException
     */
    private static void readProfiles(Scanner in) throws IOException {

        while (in.hasNextLine()) {

            Scanner newLine = new Scanner(in.nextLine());
            String name = newLine.next();
            int highestLevel = newLine.nextInt();

            mapProfiles.put(name, highestLevel);

        }
        in.close();

    }
    /**
     * Method to open the specified file
     * @param filename A string that represents the filename
     * @throws IOException If the file is not found a FileNotFoundException is thrown and handled 
     */
    public static void readProfiles(String filename) throws IOException {
        File profileFile = new File(filename);
        Scanner in = null;

        try {
            in = new Scanner(profileFile);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open " + filename);
            System.exit(0);
        }

        Profile.readProfiles(in);
    }
    /**
     * 
     * @return
     */
    public static String getProfiles() {
        for (String i : mapProfiles.keySet()) {
            System.out.println("Username: " + i + " Highest Level: " + mapProfiles.get(i));
        }
        return "";
    }

            /*public void saveGame(){

            }*/
}


