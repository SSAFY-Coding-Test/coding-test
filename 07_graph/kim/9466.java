import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static boolean[] visited;
    static boolean[] finished;
    static int count;

    public static void dfs(int node) {
        visited[node] = true;
        int next = arr[node];

        if (!visited[next])
            dfs(next);
        else if (!finished[next])
            countCycle(next, node);

        finished[node] = true;
    }

    public static void countCycle(int start, int node) {
        int cnt = 1;
        int cur = start;
        while (cur != node) {
            cnt++;
            cur = arr[cur];
        }
        count += cnt;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N + 1];
            visited = new boolean[N + 1];
            finished = new boolean[N + 1];
            count = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++) {
                if (!visited[i]) dfs(i);
            }

            sb.append(N - count).append("\n");
        }
        System.out.print(sb);
    }
}
