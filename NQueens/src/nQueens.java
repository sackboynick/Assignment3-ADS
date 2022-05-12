public class nQueens {

    public static int[][] queensBoard(int n){
        int[][] queensBoard=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                queensBoard[i][j]=0;
            }
        }
        return queensBoard;
    }

    static void printSolution(int[][] board)
    {
        for (int[] ints : board) {
            for (int j = 0; j < board.length; j++)
                System.out.print(" " + ints[j]
                        + " ");
            System.out.println();
        }
    }

    static boolean validBoard(int[][] board, int row, int col)
    {
        int i, j;

        /* Left horizontal */
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        /* Upper left diagonal */
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        /* Lower left diagonal */
        for (i = row, j = col; j >= 0 && i < board.length; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    static boolean solveNQUtil(int[][] board, int col)
    {
        /* if each column has a queen, then the board is solved */
        if (col >= board.length)
            return true;

        /* Try to locate the queen in each row for this column */
        for (int i = 0; i < board.length; i++) {
            /* Check if the queen can be placed in
               board[i][col] */
            if (validBoard(board, i, col)) {
                /* Place the queen in board[i][col] */
                board[i][col] = 1;

                /* Recursive call when queen position is valid */
                if (solveNQUtil(board, col + 1))
                    return true;

                /* Valid move but solution not yet reached */
                board[i][col] = 0; // BACKTRACK
            }
        }

        /* Queen can not be placed in this column */
        return false;
    }

    public static void main(String[] args) {
        int [][] board=queensBoard(35);
        solveNQUtil(board,0);
        printSolution(board);
    }
}
