package rockpaperscissors;

import java.util.*;

public class Main {
    static String availableMoves = "rock,paper,scissors";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        System.out.println("Hello, " + name);
        System.out.println("Which symbols you'd like to use on this game? (e.g paper,snake,rock)");
        String userChosenMoves = scan.nextLine();
        availableMoves = userChosenMoves.isBlank() ? availableMoves : userChosenMoves;
        Player player = Player.getPlayer(name);
        System.out.println("Okay, let's start");
        boolean keepRunning = true;

        while (keepRunning) {
            String userOption = scan.nextLine();
            keepRunning = play(player, userOption);
        }
    }

    private static boolean play(Player player, String userOption) {
        if (userOption == null) {
            System.out.println("Invalid input");
            return true;
        }
        if ("!exit".equals(userOption)) {
            System.out.println("Bye!");
            return false;
        }
        if ("!rating".equals(userOption)) {
            System.out.println("Your rating: " + player.getRating());
            return true;
        }
        String[] possibleMovesArray = availableMoves.split(",");
        Map<Integer, String> possibleMoves = new HashMap<>();
        for (int i = 0; i < possibleMovesArray.length; i++) {
            possibleMoves.put(i, possibleMovesArray[i]);
        }

        boolean isInputValid = false;
        for (int key: possibleMoves.keySet()) {
            if (possibleMoves.get(key).equals(userOption)) {
                isInputValid = true;
            }
        }
        if (!isInputValid) {
            System.out.println("Invalid input");
            return true;
        }
        Random random = new Random();
        String computerOption = possibleMoves.get(random.nextInt(possibleMovesArray.length));

        player.addRating(handleResult(userOption, computerOption, possibleMovesArray));
        return true;
    }

    private static int handleResult(String userOption, String computerOption, String[] possibleMovesArray) {
        if (userOption.equals(computerOption)) {
            System.out.println("There is a draw (" + computerOption + ")");
            return 50;
        }
        // we just need to create a list (set) with the values that follow the chosen one and compare it
        Set<String> defeaters = new HashSet<>();
        int userOptionIndex = Arrays.asList(possibleMovesArray).indexOf(userOption);
        int defeaterIndex = userOptionIndex + 1;
        for (int i = 0; i < possibleMovesArray.length / 2; i++) {
            if (defeaterIndex >= possibleMovesArray.length) {
                defeaterIndex = 0;
            }
            defeaters.add(possibleMovesArray[defeaterIndex]);
            defeaterIndex++;
        }

        if (defeaters.contains(computerOption)) {
            System.out.println("Sorry, but the computer chose " + computerOption);
            return 0;
        }
        System.out.printf("Well done. The computer chose %s and failed%n", computerOption);
        return 100;
    }
}
