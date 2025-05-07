import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
	public int to; // 목적지 지역 번호
	public int l; // 해당 지역까지의 거리

	public Node (int to, int l) {
		this.to = to;
		this.l = l;
	}

	public int compareTo(Node n) {
		return this.l - n.l;
	}
}

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static int n, m, r;
    private static int maxItems = Integer.MIN_VALUE;
    private static int[] t; // 아이템 수를 담는 배열
    private static ArrayList<Node>[] graph;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        t = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            t[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, l));
            graph[b].add(new Node(a, l));
        }

        for (int fall = 1; fall <= n; fall++) {
            maxItems = Math.max(maxItems, dijkstra(fall));
        }
        System.out.println(maxItems);
    }

    private static int dijkstra(int start) {
        int[] distance = new int[n + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int from = current.to;
            int dist = current.l;

            if (distance[from] < dist) continue;

            for (Node neighbor : graph[from]) {
                int next = neighbor.to;
                int nextDist = distance[from] + neighbor.l;
                
                if (distance[next] > nextDist) {
                    distance[next] = nextDist;
                    queue.add(new Node(next, nextDist));
                }
            }
        }
        return countTotalItems(distance);
    }

    private static int countTotalItems(int[] distance) {
        int totalItems = 0;

        for (int i = 1; i <= n; i++) {
            if (distance[i] <= m) {
                totalItems += t[i];
            }
        }
        return totalItems;
    }
}