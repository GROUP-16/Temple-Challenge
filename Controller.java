import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.io.*;
import java.util.*;


public class Controller  {

    private Scene scene;

    public Controller() throws IOException {
        VBox screen = new VBox(100);
        screen.setAlignment(Pos.CENTER);
        Label title = new Label("Ranking Board");
        title.setId("title");
        HashMap<String, Integer> rankings = new HashMap<>();
        LinkedHashMap<String, Integer> sortedRank = new LinkedHashMap<>();
        File ranks = new File("Profiles.txt");
        FileReader ab = null;
        try {
            ab = new FileReader(ranks);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(ab);
        String line;
        try {
            while ((line = br.readLine()) != null) {
                String[] splt = line.split(" ");
                rankings.put(splt[0],Integer.parseInt(splt[1]));
            }
        } catch (IOException e) {
            //
        }
        rankings.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEachOrdered(x -> sortedRank.put(x.getKey(), x.getValue()));
        System.out.println(sortedRank);

        Set<Map.Entry<String, Integer>> mapSet = sortedRank.entrySet();
        Map.Entry<String, Integer> elementAt1 = (new ArrayList<Map.Entry<String, Integer>>(mapSet)).get(0);
        Map.Entry<String, Integer> elementAt2 = (new ArrayList<Map.Entry<String, Integer>>(mapSet)).get(1);
        Map.Entry<String, Integer> elementAt3 = (new ArrayList<Map.Entry<String, Integer>>(mapSet)).get(2);
        String one1 = elementAt1.toString().replace("=","   ");
        String one2 = elementAt2.toString().replace("=","   ");
        String one3 = elementAt3.toString().replace("=","   ");
        Label one = new Label(one1);
        Label two = new Label(one2);
        Label three = new Label(one3);
        screen.getChildren().addAll(title,one,two,three);
        scene = new Scene(screen, 1200, 700);
    }


    public Scene getScene() {
        return scene;
    }
}