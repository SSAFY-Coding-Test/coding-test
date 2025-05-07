import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    private static int N, D;
    static Node[] graph;
    static int[] minDist; // 최단 거리 배열

    static class Node implements Comparable<Node> {
        int start;
        int end;
        int distance;

        public Node (int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return this.distance - other.distance;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 지름길의 개수
        D = Integer.parseInt(st.nextToken()); // 고속도로의 길이

        minDist = new int[D + 1];
        for (int i = 0; i < D + 1; i++) {
            minDist[i] = i;
        }

        graph = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            graph[i] = new Node(from, to, distance);
        }
        dijkstra(0);
        System.out.println(minDist[D]);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, 0));

        minDist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int now = current.end; // 현재 위치

            for (Node node : graph) {
                if (node.start >= now) {
                    if (node.end > D) continue; // 고속도로 범위 벗어났을 경우

                    // 최솟값 갱신
                    // minDist[now] : 출발점에서 현재 위치까지의 최단 거리
                    // node.distance : 지름길의 거리
                    // node.start - now : 현재 위치에서 지름길의 시작점까지의 거리 (현재 위치가 지름길 시작점보다 뒤에 있을 경우)
                    if (minDist[node.end] > minDist[now] + node.distance + (node.start - now)) {
                        minDist[node.end] = minDist[now] + node.distance + (node.start - now);
                        pq.offer(new Node(now, node.end, minDist[node.end])); // 시작점, 도착점, 거리
                    }
                }
            }
            minDist[D] = Math.min(minDist[now] + D - now, minDist[D]);  // 현재 위치에서 D까지의 거리
        }
    }
}