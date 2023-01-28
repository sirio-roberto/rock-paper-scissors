package rockpaperscissors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private String name;
    private Integer rating;

    public Player(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public void addRating(int rating) {
        this.rating += rating;
    }

    public static Player getPlayer(String userName) {
        String path = "c:\\temp\\rating.txt";
        Map<String, Integer> users = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(" ");
                String name = fields[0];
                int rating = Integer.parseInt(fields[1]);
                users.put(name, rating);
            }
        } catch (IOException e) {
            System.out.println("File does not exist");
        }
        return new Player(userName, users.getOrDefault(userName, 0));
    }
}
