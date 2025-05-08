public class Assign3ShortestPath {

    static final int V = 5; // Number of vertices

    // Find the vertex with minimum distance value
    static int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        
        for (int v = 0; v < V; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        
        return minIndex;
    }

    // Dijkstra's algorithm
    static void dijkstra(int[][] graph, int src) {
        int[] dist = new int[V];       // Output array. dist[i] holds shortest distance from src to i
        boolean[] visited = new boolean[V]; // visited[i] will be true if vertex i is included in shortest path tree

        // Initialize distances to INFINITY and visited[] to false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        dist[src] = 0; // Distance to itself is always 0

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            // Update distance value of adjacent vertices of the picked vertex
            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 &&
                    dist[u] != Integer.MAX_VALUE &&
                    dist[u] + graph[u][v] < dist[v]) {
                    
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // Print shortest distances
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }

    public static void main(String[] args) {
        // Example graph: 2D array (adjacency matrix)
        int[][] graph = {
            {0, 10, 0, 30, 100},
            {10, 0, 50, 0, 0},
            {0, 50, 0, 20, 10},
            {30, 0, 20, 0, 60},
            {100, 0, 10, 60, 0}
        };

        dijkstra(graph, 0); // Start from source vertex 0
    }
}
