import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<int[]>[] routes = new ArrayList[N+1];
        for(int i = 0; i <= N; i++) {
            routes[i] = new ArrayList<>();
        }

        for(int m = 0 ; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            routes[s].add(new int[] {e, c});
            routes[e].add(new int[] {s, c});
        }

        // dijkstra
        boolean[] shortPath = new boolean[N+1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((s, e) -> s[1] - e[1]);
        pq.add(new int[]{1, 0});
        shortPath[0] = true;

        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            if (now[0] == N) {
                System.out.println(now[1]);
                return;
            }
            if (shortPath[now[0]]) continue;
            shortPath[now[0]] = true;

            for(int[] next : routes[now[0]]) {
                pq.add(new int[]{next[0], now[1] + next[1]});
            }
        }
    }
}
