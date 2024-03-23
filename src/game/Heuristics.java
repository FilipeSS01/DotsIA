package game;

import java.util.ArrayList;

import board.Board;
import board.Square;
import tree.Node;

public class Heuristics {
    public static Node createTree(Board board, int move, boolean player, int depth) {
        Node root = new Node();

        root.setPlayer(player);
        root.setMove(move);
        root.setMinMax(0);

        int[] coords = Moves.moveSeparator(move);
        board.removeMove(move);
        board.makeMove(coords[0], coords[1], true);
        if (!Square.isSquareComplete(board.getBoard(), coords[0], coords[1], player, false))
            player = !player;

        tree(board.getBoard(), board.getMoves(), player, root, 1, depth);

        board.getMoves().add(move);
        board.makeMove(coords[0], coords[1], false);
        return root;
    }

    public static void tree(boolean board[][], ArrayList<Integer> moves, boolean player, Node tree, int treeHeight,
            int depth) {
        if (treeHeight == depth) {
            tree.setMinMax(minMaxSheet(board));
            return;
        } else {
            for (int move : moves) {
                int[] coords = Moves.moveSeparator(move);
                if (board[coords[0]][coords[1]] == false && tree.hasChild(move) == null) {
                    board[coords[0]][coords[1]] = true;

                    Node node = new Node(player, move, 0);
                    tree.getChildren().add(node);

                    boolean tempPlayer = player;
                    if (!Square.isSquareComplete(board, coords[0], coords[1], player, false))
                        player = !player;

                    tree(board, moves, player, node, treeHeight + 1, depth);

                    Square.isSquareComplete(board, coords[0], coords[1], player, true);
                    tree.setMinMax(getMinMaxChild(tree));
                    board[coords[0]][coords[1]] = false;
                    player = tempPlayer;
                }
            }
        }
    }

    private static int minMaxSheet(boolean[][] board) {
        return Board.checkScore(board);
    }

    private static int getMinMaxChild(Node node) {
        int value = 0;
        for (Node child : node.getChildren()) {
            value = child.getMinMax() + value;
        }
        return value;
    }
}
