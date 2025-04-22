import java.io.*;
import java.util.*;

public class Main {
    static int N, M, Q;
    static int[] result;
    static ArrayList<Integer>[] road;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        road = new ArrayList[N + 1];
        for (int i = 0;  i < N + 1; i++) {
            road[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            road[a].add(b);
            road[b].add(a);
        }

        result = new int[N + 1];
        Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            road[a].add(b);
            road[b].add(a);

            solve();
            printCount();
        }
        System.out.println(sb.toString());
    }

    private static void solve() {
        boolean[] visited = new boolean[N + 1];
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        visited[1] = true;
        queue.add(new int[] {1, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int start = current[0];
            int count = current[1];

            for (int neighbor : road[start]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    result[neighbor] = count + 1;
                    queue.add(new int[] {neighbor, count + 1});
                }
            }
        }
        for (int i = 1; i < N + 1; i++) {
            if (!visited[i]) {
                result[i] = -1;
            }
        }
    }

    private static void printCount() {
        for (int i = 1; i < N + 1; i++) {
            sb.append(result[i] + " ");
        }
        sb.append("\n");
    }
}