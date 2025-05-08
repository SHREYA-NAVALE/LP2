public class Assign4A {
    static int N = 8; // You can change this to solve for other N values
    static int solutionCount = 0;

    static boolean[] column = new boolean[N];       // tracks if a column is occupied
    static boolean[] diag1 = new boolean[2 * N - 1]; // left diagonal (row - col + N - 1)
    static boolean[] diag2 = new boolean[2 * N - 1]; // right diagonal (row + col)
    static int[] board = new int[N]; // board[row] = column where queen is placed

    static void solve(int row) {
        if (row == N) {
            printSolution();
            solutionCount++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (!column[col] && !diag1[row - col + N - 1] && !diag2[row + col]) {
                board[row] = col;
                column[col] = diag1[row - col + N - 1] = diag2[row + col] = true;

                solve(row + 1);

                column[col] = diag1[row - col + N - 1] = diag2[row + col] = false;
            }
        }
    }

    static void printSolution() {
        System.out.println("Solution " + (solutionCount + 1) + ":");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((board[i] == j ? "Q " : ". "));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        solve(0);
        System.out.println("Total solutions: " + solutionCount);
    }
}
