public class Assign1 {

    static final int MAX = 100;  // max number of vertices
    static int[][] adj = new int[MAX][MAX];  // adjacency matrix
    static boolean[] visitedDFS = new boolean[MAX];
    static boolean[] visitedBFS = new boolean[MAX];
    static int[] queue = new int[MAX];  // for BFS
    static int front = 0, rear = 0;

    // Add edge in an undirected graph
    static void addEdge(int u, int v) {
        adj[u][v] = 1;
        adj[v][u] = 1;
    }

    // Recursive DFS
    static void dfs(int node, int n) {
        visitedDFS[node] = true;
        System.out.print(node + " ");

        for (int i = 0; i < n; i++) {
            if (adj[node][i] == 1 && !visitedDFS[i]) {
                dfs(i, n);
            }
        }
    }

    // Recursive BFS Helper
    static void bfsRecursive(int n) {
        if (front == rear) return;  // queue is empty

        int node = queue[front++];
        System.out.print(node + " ");

        for (int i = 0; i < n; i++) {
            if (adj[node][i] == 1 && !visitedBFS[i]) {
                visitedBFS[i] = true;
                queue[rear++] = i;
            }
        }

        bfsRecursive(n);  // recursive call
    }

    // BFS wrapper
    static void bfs(int start, int n) {
        front = 0;
        rear = 0;
        queue[rear++] = start;
        visitedBFS[start] = true;
        bfsRecursive(n);
    }

    public static void main(String[] args) {
        int n = 5; // number of nodes: 0, 1, 2, 3, 4

        // Create example graph
        addEdge(0, 1);
        addEdge(0, 3);
        addEdge(1, 2);
        addEdge(1, 4);

        System.out.println("Recursive DFS starting from node 0:");
        dfs(0, n);

        System.out.println("\nRecursive BFS starting from node 0:");
        bfs(0, n);
    }
}
