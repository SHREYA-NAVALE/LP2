import java.util.*;

// Edge class to represent a graph edge
class Edge {
    int src, dest, weight;

    Edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }
}

// Disjoint Set (Union-Find with path compression and union by rank)
class DisjointSet {
    int[] parent, rank;

    DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++)
            parent[i] = i;
    }

    int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]); // Path compression
        return parent[x];
    }

    void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        if (rank[rootX] < rank[rootY])
            parent[rootX] = rootY;
        else if (rank[rootX] > rank[rootY])
            parent[rootY] = rootX;
        else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}

// Main class for Kruskal's Algorithm
public class Kruskal {
    int V;
    List<Edge> edges = new ArrayList<>();

    Kruskal(int vertices) {
        V = vertices;
    }

    void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    void kruskalMST() {
        edges.sort(Comparator.comparingInt(e -> e.weight));
        DisjointSet ds = new DisjointSet(V);

        int cost = 0;
        System.out.println("Edges in Minimum Spanning Tree:");
        for (Edge edge : edges) {
            if (ds.find(edge.src) != ds.find(edge.dest)) {
                ds.union(edge.src, edge.dest);
                cost += edge.weight;
                System.out.println(edge.src + " - " + edge.dest + " = " + edge.weight);
            }
        }
        System.out.println("Total cost: " + cost);
    }

    public static void main(String[] args) {
        Kruskal g = new Kruskal(4);  
        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, 6);
        g.addEdge(0, 3, 5);
        g.addEdge(1, 3, 15);
        g.addEdge(2, 3, 4);

        g.kruskalMST();
    }
}
