package game;

public class Moves {
    public static int[] moveSeparator(int move) {
        int[] moves = new int[2];
        moves[0] = (move < 10) ? 0 : move / 10;
        moves[1] = (move < 10) ? move : move % 10;
        return moves;
    }

    public static boolean isValidMove(boolean[][] board, int row, int col) {
        if (isMoveOutOfBounds(board, row, col))
            return false;
        if (isCellOccupied(board, row, col) || isInvalidCellPosition(row, col))
            return false;
        return true;
    }

    private static boolean isMoveOutOfBounds(boolean[][] board, int row, int col) {
        int numRows = board.length;
        int numCols = board[0].length;
        return row < 0 || row >= numRows || col < 0 || col >= numCols;
    }

    public static boolean isCellOccupied(boolean[][] board, int row, int col) {
        return board[row][col];
    }

    private static boolean isInvalidCellPosition(int row, int col) {
        return (row % 2 == 0 && col % 2 == 0) || (row % 2 == 1 && col % 2 == 1);
    }
}
