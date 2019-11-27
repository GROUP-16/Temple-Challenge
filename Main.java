import java.io.IOException;
import java.sql.Time;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a profile file to open:");
        String filename = input.next();

        Profile.readProfiles(filename);
        System.out.println(Profile.getProfiles());

        Profile newP =  new Profile("Andrew", 6);
        Profile.readProfiles(filename);
        System.out.println(Profile.getProfiles());

        Profile.deleteProfile("Rado");
        Profile.readProfiles(filename);
        System.out.println(Profile.getProfiles());
        input.close();



    }
}
