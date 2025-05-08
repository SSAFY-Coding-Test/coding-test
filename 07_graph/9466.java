import java.io.*;
import java.util.*;

public class Main {
    static int N, count;
    static int[] votes;
    static boolean[] visited, finished;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            votes = new int[N + 1];
            visited = new boolean[N + 1];
            finished = new boolean[N + 1];
            count = 0;

            StringTokenizer st= new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                votes[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++) {
                if (!finished[i]) solve(i);
            }
            sb.append(N - count).append("\n");
        }
        System.out.print(sb);
    }

    private static void solve(int cur) {
        visited[cur] = true;
        int next = votes[cur];

        if (!visited[next]) {
            solve(next);
        } else if (!finished[next]) {
            for (int temp = next; temp != cur; temp = votes[temp]) count++; // 싸이클 발견
            count++; // 마지막 노드 자신도 포함
        }
        finished[cur] = true;
    }
}