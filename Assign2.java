import java.util.*;

public class Assign2 {

    static int g = 0; // global move count

    static void print(int[] puzzle) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) System.out.println();
            System.out.print((puzzle[i] == -1 ? "_" : puzzle[i]) + " ");
        }
        System.out.println("\n");
    }

    static void copy(int[] from, int[] to) {
        System.arraycopy(from, 0, to, 0, 9);
    }

    static void moveLeft(int[] state, int pos) {
        swap(state, pos, pos - 1);
    }

    static void moveRight(int[] state, int pos) {
        swap(state, pos, pos + 1);
    }

    static void moveUp(int[] state, int pos) {
        swap(state, pos, pos - 3);
    }

    static void moveDown(int[] state, int pos) {
        swap(state, pos, pos + 3);
    }

    static void swap(int[] state, int i, int j) {
        int temp = state[i];
        state[i] = state[j];
        state[j] = temp;
    }

    static int heuristic(int[] state, int[] goal) {
        int h = 0;
        for (int i = 0; i < 9; i++) {
            if (state[i] == -1) continue;
            for (int j = 0; j < 9; j++) {
                if (goal[j] == state[i]) {
                    h += Math.abs(j / 3 - i / 3) + Math.abs(j % 3 - i % 3);
                    break;
                }
            }
        }
        return h + g;
    }

    static void moveTile(int[] start, int[] goal) {
        int emptyAt = -1;
        for (int i = 0; i < 9; i++) {
            if (start[i] == -1) {
                emptyAt = i;
                break;
            }
        }

        int[] t1 = new int[9], t2 = new int[9], t3 = new int[9], t4 = new int[9];
        int f1 = Integer.MAX_VALUE, f2 = Integer.MAX_VALUE, f3 = Integer.MAX_VALUE, f4 = Integer.MAX_VALUE;

        copy(start, t1);
        copy(start, t2);
        copy(start, t3);
        copy(start, t4);

        int row = emptyAt / 3, col = emptyAt % 3;

        if (col > 0) {
            moveLeft(t1, emptyAt);
            f1 = heuristic(t1, goal);
        }
        if (col < 2) {
            moveRight(t2, emptyAt);
            f2 = heuristic(t2, goal);
        }
        if (row < 2) {
            moveDown(t3, emptyAt);
            f3 = heuristic(t3, goal);
        }
        if (row > 0) {
            moveUp(t4, emptyAt);
            f4 = heuristic(t4, goal);
        }

        if (f1 <= f2 && f1 <= f3 && f1 <= f4) moveLeft(start, emptyAt);
        else if (f2 <= f1 && f2 <= f3 && f2 <= f4) moveRight(start, emptyAt);
        else if (f3 <= f1 && f3 <= f2 && f3 <= f4) moveDown(start, emptyAt);
        else moveUp(start, emptyAt);
    }

    static void solveEight(int[] start, int[] goal) {
        g++;
        moveTile(start, goal);
        print(start);
        int f = heuristic(start, goal);
        if (f == g) {
            System.out.println("Solved in " + f + " moves");
            return;
        }
        solveEight(start, goal);
    }

    static boolean isSolvable(int[] start) {
        int inv = 0;
        for (int i = 0; i < 9; i++) {
            if (start[i] <= 0) continue;
            for (int j = i + 1; j < 9; j++) {
                if (start[j] <= 0) continue;
                if (start[i] > start[j]) inv++;
            }
        }
        return inv % 2 == 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] start = new int[9];
        int[] goal = new int[9];

        System.out.println("Enter the start state (-1 for blank):");
        for (int i = 0; i < 9; i++) start[i] = sc.nextInt();

        System.out.println("Enter the goal state (-1 for blank):");
        for (int i = 0; i < 9; i++) goal[i] = sc.nextInt();

        print(start);

        if (isSolvable(start)) {
            solveEight(start, goal);
        } else {
            System.out.println("\nImpossible to Solve");
        }

        sc.close();
    }
}