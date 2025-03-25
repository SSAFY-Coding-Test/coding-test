import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static Set<Integer>[] routes;
    static StringBuilder sb = new StringBuilder();

    static void bfs() {
        int[] path = new int[N+1];

        PriorityQueue<int[]> pq = new PriorityQueue<>((s, e) -> (s[1] - e[1]));
        pq.add(new int[] {1, 0});

        while(!pq.isEmpty()){
            int[] now = pq.poll();
            if (path[now[0]] != 0) continue;
            path[now[0]] = now[1];

            for(int r : routes[now[0]]) {
                pq.add(new int[] {r, now[1]+1});
            }
        }

        sb.append("0 ");
        for(int i = 2; i <= N; i++)
            sb.append(path[i] == 0 ? -1 : path[i]).append(" ");
        sb.append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        routes = new HashSet[N+1];

        for(int i = 0; i <= N; i++)
            routes[i] = new HashSet<>();

        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            routes[a].add(b);
            routes[b].add(a);
        }

        int R =  Integer.parseInt(br.readLine());

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            routes[a].add(b);
            routes[b].add(a);
            bfs();
        }

        System.out.print(sb);
    }
}
