import java.util.Scanner;
import java.util.Random;
import board.*;
import game.*;
import tree.*;

public class Main {
    private static Board board;
    private static Player player;
    private static Node root;

    public static void main(String[] args) {
        int[] config = Config.getConfiguration();

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        setBoard(new Board());
        setPlayer(new Player());
        boolean hasTree = false;
        int move;

        int depth = 6 + config[0];
        boolean heuristicMinMax = (config[1] == 1) ? true : false;

        board.printBoard(false);
        while (!board.getMoves().isEmpty()) {
            System.out.println();
            System.out.printf("Player %s's turn\n", player.toString());
            System.out.print("Enter the number of move: ");

            if (!player.isPlayer()) {
                move = (hasTree) ? root.bestMove() : board.getMoves().get(random.nextInt(board.getMoves().size()));
                System.out.println(move);
            } else {
                move = getPlayerMove(scanner);
            }

            if (!hasTree && getPlayer().isPlayer() && depth == board.getMoves().size()) {
                createTree(move, depth, heuristicMinMax);
                hasTree = true;
            }

            move(move, hasTree);
            board.printBoard(false);
        }

        Board.finish(board.getBoard());
        board.printBoard(true);
        scanner.close();
    }

    private static void createTree(int move, int depth, boolean heuristicMinMax) {
        if (heuristicMinMax) {
            setRoot(MinMax.createTree(getBoard(), move, getPlayer().isPlayer(), depth));
        } else {
            setRoot(Heuristics.createTree(getBoard(), move, getPlayer().isPlayer(), depth));
        }
    }

    private static int getPlayerMove(Scanner scanner) {
        int move;
        while (true) {
            try {
                move = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return move;
    }

    private static void move(int move, boolean hasTree) {
        int[] coords = Moves.moveSeparator(move);
        if (Moves.isValidMove(getBoard().getBoard(), coords[0], coords[1])) {
            if (hasTree)
                root = changeRoot(root, move);
            getBoard().removeMove(move);
            getBoard().makeMove(coords[0], coords[1], true);
            if (!Square.isSquareComplete(getBoard().getBoard(), coords[0], coords[1], getPlayer().isPlayer(), false))
                getPlayer().invert();
        } else {
            System.out.println("Invalid move. Please try again.");
        }
    }

    private static Node changeRoot(Node root, int move) {
        for (Node node : root.getChildren()) {
            if (node.getMove() == move) {
                return node;
            }
        }
        return (move == root.getMove()) ? root : null;
    }

    // Getters and Setters
    public static Board getBoard() {
        return board;
    }

    public static void setBoard(Board board) {
        Main.board = board;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Main.player = player;
    }

    public static Node getRoot() {
        return root;
    }

    public static void setRoot(Node root) {
        Main.root = root;
    }
}
