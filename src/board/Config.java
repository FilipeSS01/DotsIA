package board;

import java.util.Scanner;

public class Config {
    public static int[] getConfiguration() {
        String[] configString = Configuration();
        int[] config = new int[2];
        switch (configString[0]) {
            case "Hard":
                config[0] = 5;
                break;
            case "Medium":
                config[0] = 3;
                break;
            case "Easy":
                config[0] = 1;
                break;
        }
        switch (configString[1]) {
            case "Aluno":
                config[1] = 0;
                break;
            case "MinMax":
                config[1] = 1;
                break;
        }

        return config;
    }

    private static String[] Configuration() {
        Scanner scanner = new Scanner(System.in);
        String difficulty = "Hard";
        String heuristic = "MinMax";

        int choice;
        while (true) {
            clearScreen();
            System.out.println("Current Configuration:\nDifficulty - " + difficulty + "\nHeuristic - " + heuristic);
            System.out.println("\nGame Configuration Menu:");
            System.out.println("1. Difficulty");
            System.out.println("2. Heuristic");
            System.out.println("0. Finish Configuration\n");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            String aux;
            switch (choice) {
                case 1:
                    aux = difficultySubMenu(scanner);
                    difficulty = (aux == null) ? difficulty : aux;
                    break;
                case 2:
                    aux = heuristicSubMenu(scanner);
                    heuristic = (aux == null) ? heuristic : aux;
                    break;
                case 0:
                    break;
            }

            if (choice == 0) {
                break;
            }
        }
        clearScreen();
        System.out.println("Configuration:");
        System.out.println("Difficulty Level: " + difficulty);
        System.out.println("Heuristic: " + heuristic);

        String[] config = { difficulty, heuristic };
        return config;
    }

    private static String difficultySubMenu(Scanner scanner) {
        int choice;
        while (true) {
            clearScreen();
            System.out.println("Difficulty Level Menu:");
            System.out.println("1. Hard");
            System.out.println("2. Medium");
            System.out.println("3. Easy");
            System.out.println("0. Back to Main Menu");

            System.out.print("\nEnter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    return "Hard";
                case 2:
                    return "Medium";
                case 3:
                    return "Easy";
                case 0:
                    return null;
            }
        }
    }

    private static String heuristicSubMenu(Scanner scanner) {
        int choice;
        while (true) {
            clearScreen();
            System.out.println("Heuristic Menu:");
            System.out.println("1. MinMax");
            System.out.println("2. Aluno");
            System.out.println("0. Back to Main Menu");

            System.out.print("\nEnter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    return "MinMax";
                case 2:
                    return "Aluno";
                case 0:
                    return null;
            }
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
