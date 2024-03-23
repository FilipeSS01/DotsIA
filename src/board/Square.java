package board;

public class Square {
    public static boolean isSquareComplete(boolean[][] board, int row, int col, boolean player, boolean goBack) {
        boolean statusSquare = false;
        if (row % 2 == 0 && col % 2 == 1) {
            if (row == 4) {
                statusSquare = lowerHorizontal(board, row, col);
                if (statusSquare)
                    board[row - 1][col] = (goBack) ? false : player;
            } else if (row == 2) {
                statusSquare = topHorizontal(board, row, col) || (lowerHorizontal(board, row, col));
                if (statusSquare) {
                    if (topHorizontal(board, row, col))
                        board[row + 1][col] = (goBack) ? false : player;
                    if (lowerHorizontal(board, row, col))
                        board[row - 1][col] = (goBack) ? false : player;
                }
            } else {
                statusSquare = topHorizontal(board, row, col);
                if (statusSquare)
                    board[row + 1][col] = (goBack) ? false : player;
            }
        } else if (row % 2 == 1 && col % 2 == 0) {
            if (col == 4) {
                statusSquare = verticalRight(board, row, col);
                if (statusSquare)
                    board[row][col - 1] = (goBack) ? false : player;
            } else if (col == 2) {
                statusSquare = (verticalLeft(board, row, col)) || (verticalRight(board, row, col));
                if (statusSquare) {
                    if (verticalLeft(board, row, col))
                        board[row][col + 1] = (goBack) ? false : player;
                    if (verticalRight(board, row, col))
                        board[row][col - 1] = (goBack) ? false : player;
                }
            } else {
                statusSquare = verticalLeft(board, row, col);
                if (statusSquare)
                    board[row][col + 1] = (goBack) ? false : player;
            }
        }
        return statusSquare;
    }

    public static boolean lowerHorizontal(boolean[][] board, int row, int col) {
        return board[row][col] && board[row - 1][col - 1] && board[row - 1][col + 1] && board[row - 2][col];
    }

    public static boolean topHorizontal(boolean[][] board, int row, int col) {
        return board[row][col] && board[row + 1][col - 1] && board[row + 1][col + 1] && board[row + 2][col];
    }

    public static boolean verticalRight(boolean[][] board, int row, int col) {
        return board[row][col] && board[row + 1][col - 1] && board[row][col - 2] && board[row - 1][col - 1];
    }

    public static boolean verticalLeft(boolean[][] board, int row, int col) {
        return board[row][col] && board[row + 1][col + 1] && board[row][col + 2] && board[row - 1][col + 1];
    }

    public static void boardResetScore(boolean[][] board) {
        board[1][1] = false;
        board[1][3] = false;
        board[3][1] = false;
        board[3][3] = false;
    }
}
