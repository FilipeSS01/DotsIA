package game;

import java.util.ArrayList;

import board.Board;
import board.Square;
import tree.Node;

public class MinMax {

    public static Node createTree(Board board, int move, boolean player, int depth) {
        Node root = new Node();

        root.setPlayer(player);
        root.setMove(move);
        root.setMinMax((player) ? Integer.MAX_VALUE : Integer.MIN_VALUE);

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

    public static void tree(boolean board[][], ArrayList<Integer> moves, boolean player, Node tree, int treeHeight, int depth) {
        if (treeHeight == depth) {
            tree.setMinMax(minMaxSheet(board));
            return;
        } else {
            for (int move : moves) {
                int[] coords = Moves.moveSeparator(move);
                if (board[coords[0]][coords[1]] == false && tree.hasChild(move) == null) {
                    board[coords[0]][coords[1]] = true;

                    Node node = new Node(player, move, (player) ? Integer.MAX_VALUE : Integer.MIN_VALUE);
                    tree.getChildren().add(node);

                    boolean tempPlayer = player;
                    if (!Square.isSquareComplete(board, coords[0], coords[1], player, false))
                        player = !player;

                    tree(board, moves, player, node, treeHeight + 1, depth);

                    Square.isSquareComplete(board, coords[0], coords[1], player, true); // posso tirar o goBack, preciso
                                                                                        // so passar false no plyer!
                    tree.setMinMax(getMinMaxChild(tree));
                    board[coords[0]][coords[1]] = false;
                    player = tempPlayer;
                }
            }
        }
    }

    private static int minMaxSheet(boolean[][] board) {
        int result = Board.checkScore(board);
        return (result < 0) ? -1 : (result > 0) ? 1 : 0;
    }

    private static int getMinMaxChild(Node node) {
        int value;
        if (!node.getPlayer()) {
            value = Integer.MIN_VALUE;
            for (Node child : node.getChildren()) {
                value = Math.max(value, child.getMinMax());
            }
        } else {
            value = Integer.MAX_VALUE;
            for (Node child : node.getChildren()) {
                value = Math.min(value, child.getMinMax());
            }
        }
        return value;
    }
}
