import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int N, M;
    static ArrayList<Edge>[] cities;
    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cities = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            cities[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            cities[a].add(new Edge(b, c));
            cities[b].add(new Edge(a, c));
        }
        System.out.println(prim());
    }

    private static int prim() {
        int totalWeight = 0;
        int maxWeight = 0;
        visited = new boolean[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);

        pq.add(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int node = current.to;
            int weight = current.weight;

            if (visited[node]) continue;

            visited[node] = true;
            totalWeight += weight;
            maxWeight = Math.max(maxWeight, weight);

            for (Edge neighbor : cities[node]) {
                if (!visited[neighbor.to]) {
                    pq.add(neighbor);
                }
            }
        }
        return totalWeight - maxWeight;
    }
}