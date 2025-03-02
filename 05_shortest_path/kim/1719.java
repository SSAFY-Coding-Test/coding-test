import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static HashMap<Integer, List<Node>> routes;
    static StringBuilder sb = new StringBuilder();

    static class Node {
        int node;
        int dist;
        int route;

        Node(int node, int dist, int route) {
            this.node = node;
            this.dist = dist;
            this.route = route;
        }

        @Override
        public String toString() {
            return "{node} " + this.node + " " + this.dist + " " + this.route;
        }
    }

    public static void dijkstra(int startNode) {
        PriorityQueue<Node> queue = new PriorityQueue<>((s, e) -> s.dist- e.dist);

        int[] path = new int[N+1];
        path[startNode] = startNode;
        for(Node n : routes.getOrDefault(startNode, new ArrayList<>())) {
            queue.add(new Node(n.node, n.dist, n.route));
        }

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (path[now.node] != 0) continue;
            path[now.node] = now.route;
            for(Node n : routes.getOrDefault(now.node, new ArrayList<>())) {
                if (path[n.node] == 0) {
                    queue.add(new Node(n.node, now.dist + n.dist, now.route));
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            if (i == startNode) sb.append("- ");
            else sb.append(path[i]).append(" ");
        }
        sb.append("\n");

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        routes = new HashMap<>();

        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (!routes.containsKey(s))
                routes.put(s, new ArrayList<>());
            if (!routes.containsKey(e))
                routes.put(e, new ArrayList<>());

            routes.get(s).add(new Node(e, w, e));
            routes.get(e).add(new Node(s, w, s));
        }

        for(int i = 1; i <= N; i++) {
            dijkstra(i);
        }

        System.out.println(sb);
    }
}
