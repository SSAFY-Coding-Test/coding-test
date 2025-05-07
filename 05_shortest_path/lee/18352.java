import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, K, X;
    private static ArrayList<Integer>[] road;
    private static int[] distance;
    private static boolean[] visited;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        road = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            road[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            road[u].add(v); // u에서 v로 가는 도로 추가
        }

        visited = new boolean[N + 1];
        distance = new int[N + 1];
        Arrays.fill(distance, -1); // 모든 거리를 -1로 초기화
        bfs(X);
    }

    private static void bfs(int start) {
        ArrayDeque<Integer> queue = new ArrayDeque();

        queue.add(start);
        visited[start] = true;
        distance[start] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : road[current]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    distance[next] = distance[current] + 1;
                }
            }
        }

        boolean found = false;
        for (int i = 1; i <= N; i++) {
            if (distance[i] == K) {
                System.out.println(i);
                found = true;
            }
        }

        if (!found) System.out.println(-1);
    }
}
