import java.util.*;

/**
 * Created by Shashank on 09-08-2016.
 */
public class Solution {
    static HashMap<Integer, LinkedList<Integer>> adjList;
    static int distance[][];
    static int d[];
    static boolean[] visited;
    LinkedList<Integer> sptSet;
    public Solution(int v, int e) {
        adjList = new HashMap<>();
        distance = new int[e+1][e+1];
        d = new int[v+1];
        visited = new boolean[v+1];
        for (int i=1;i<=v;i++) {
            adjList.put(i, new LinkedList<>());
        }
    }
    public void setEdge(int u, int v) {
        LinkedList<Integer> source = adjList.get(u);
        source.add(v);
        LinkedList<Integer> destination = adjList.get(v);
        destination.add(u);
    }
    public void dijkstra(int s) {
        sptSet = new LinkedList<>();
        Arrays.fill(d, Integer.MAX_VALUE);
        d[s] = 0;
        while (sptSet.size() != d.length-1) {
            int u = findMin();
            sptSet.add(u);
            for (int v : adjList.get(u)) {
                if (d[u] + distance[u][v] < d[v]) {
                    d[v] = distance[u][v] + d[u];
                }
            }
        }
    }
    public int findMin() {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i=1;i<d.length;i++) {
            if (!visited[i] && d[i] <= min) {
                min = d[i];
                index = i;
            }
        }
        visited[index] = true;
        return index;
    }
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            Solution solution = new Solution(n, m);
            for (int i=1;i<=m;i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int r = scanner.nextInt();
                solution.setEdge(x,y);
                distance[x][y] = r;
                distance[y][x] = r;
            }
            int s = scanner.nextInt();
            solution.dijkstra(s);
            for (int i=1;i<d.length;i++) {
                if (i != s) {
                    if (adjList.get(i).size() == 0) {
                        System.out.print("-1 ");
                    }
                    else {
                        System.out.print(d[i] + " ");
                    }
                }
            }
            System.out.println();
        }
    }
}
