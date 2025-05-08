public class Assign4B {
    static int N;

    // Function to print the solution
    static void printSolution(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Check if placing a queen is safe
    static boolean isSafe(int[][] board, int row, int col) {
        // Check the column and diagonals for any queens
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
            if (col - (row - i) >= 0 && board[i][col - (row - i)] == 1) {
                return false;
            }
            if (col + (row - i) < N && board[i][col + (row - i)] == 1) {
                return false;
            }
        }
        return true;
    }

    // Recursive function for backtracking approach
    static boolean solveNQueens(int[][] board, int row) {
        if (row >= N) {
            return true;  // All queens are placed
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;  // Place the queen
                if (solveNQueens(board, row + 1)) {
                    return true;  // If placing queen leads to solution
                }
                board[row][col] = 0;  // Backtrack
            }
        }

        return false;  // No valid position found for this row
    }

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter the value of N: ");
        N = sc.nextInt();

        int[][] board = new int[N][N];
        if (solveNQueens(board, 0)) {
            printSolution(board);  // Print solution if found
        } else {
            System.out.println("Solution does not exist.");
        }

        sc.close();
    }
}
