package board;

import tree.Node;
import java.util.ArrayList;

public class Board {
    private boolean[][] board;
    private boolean player;
    private Node tree;
    private ArrayList<Integer> moves;

    public Board() {
        setBoard(new boolean[5][5]);
        setTree(new Node());
        setMoves(addAllMoves());
    }

    // Function to map all moves
    private ArrayList<Integer> addAllMoves() {
        ArrayList<Integer> moves = new ArrayList<Integer>();
        for (int i = 0; i < getBoard().length; i++) {
            for (int j = 0; j < getBoard().length; j++) {
                if (!(i % 2 == 0 && j % 2 == 0) && !(i % 2 == 1 && j % 2 == 1)) {
                    moves.add(Integer.valueOf(i + "" + j));
                }
            }
        }
        return moves;
    }

    // Function to print the board - finish flag if the game has finished
    public void printBoard(boolean finish) {
        System.out.println();
        for (int i = 0; i < getBoard().length; i++) {
            for (int j = 0; j < getBoard().length; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    System.out.print("\t*");
                } else if (i % 2 == 1 && j % 2 == 1) {
                    System.out.print("\t" + ((!finish) ? "" : ((getBoard()[i][j] == true) ? '1' : '0')));
                } else {
                    if (getBoard()[i][j] == true) {
                        System.out.print("\t" + ((j % 2) == 0 ? '|' : '-'));
                    } else {
                        System.out.print("\t" + Integer.valueOf(i + "" + j));
                    }
                }
            }
            System.out.println();
        }
    }

    // Finish game functions
    public static int checkScore(boolean[][] board) {
        int player = 0;
        int ia = 0;
        for (int i = 1; i < 5; i += 2) {
            for (int j = 1; j < 5; j += 2) {
                if (board[i][j] == true) {
                    player++;
                } else if (board[i][j] == false) {
                    ia++;
                }
            }
        }
        return player - ia;
    }

    public static void finish(boolean[][] board) {
        System.out.println("\nGame over!");
        if (Board.checkScore(board) > 0) {
            System.out.println("Player win!");
        } else if (Board.checkScore(board) < 0) {
            System.out.println("IA win!");
        } else {
            System.out.println("Drawn!");
        }
    }
    

    public void makeMove(int row, int col, boolean status){
        getBoard()[row][col] = status;
    }
    
    public void removeMove(int move) {
        getMoves().remove(getMoves().indexOf(move));
    }

    // Getters and Setters
    public boolean[][] getBoard() {
        return board;
    }

    public void setBoard(boolean[][] board) {
        this.board = board;
    }

    public boolean isPlayer() {
        return player;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }

    public Node getTree() {
        return tree;
    }

    public void setTree(Node tree) {
        this.tree = tree;
    }

    public ArrayList<Integer> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Integer> moves) {
        this.moves = moves;
    }
}
