import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int num, cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost; // 오름차순
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static int[] minDistance;
    static boolean[] visited;
    static ArrayList<Node>[] neighbors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        minDistance = new int[N + 1];
        visited = new boolean[N + 1];
        neighbors = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            neighbors[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            neighbors[from].add(new Node(to, cost));
            neighbors[to].add(new Node(from, cost));
        }

        Arrays.fill(minDistance, INF);
        dijkstra();
        System.out.println(minDistance[N]);
    }

    private static void dijkstra() {
        minDistance[1] = 0;
        PriorityQueue<Node> pQueue = new PriorityQueue<>();
        pQueue.offer(new Node(1, minDistance[1]));

        while (!pQueue.isEmpty()) {
            // step1 : 미방문 정점 중 출발지에서 가장 가까운 정점 찾기
            Node current = pQueue.poll();

            if (visited[current.num]) continue;

            // step2 : 선택된 정점을 경유해서 갈 수 있는 미방문 정점들의 최단 경로 비용 update
            for (Node next : neighbors[current.num]) {
                if (minDistance[next.num] > minDistance[current.num] + next.cost) {
                    minDistance[next.num] = minDistance[current.num] + next.cost;
                    pQueue.add(new Node(next.num, minDistance[next.num]));
                }
            }
        }
    }
}