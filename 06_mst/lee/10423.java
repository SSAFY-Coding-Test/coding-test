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

    static int N, M, K;
    static ArrayList<Edge>[] powerStations;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        powerStations = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            powerStations[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(st.nextToken());
            powerStations[0].add(new Edge(num, 0));
            powerStations[num].add(new Edge(0, 0));
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            powerStations[u].add(new Edge(v, w));
            powerStations[v].add(new Edge(u, w));
        }
        System.out.println(prim());
    }

    private static int prim() {
        int totalWeight = 0;
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

            for (Edge neighbor : powerStations[node]) {
                if (!visited[neighbor.to]) {
                    pq.add(neighbor);
                }
            }
        }
        return totalWeight;
    }
}