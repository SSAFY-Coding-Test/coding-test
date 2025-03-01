import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[] items = new int[N];
        boolean[] visited = new boolean[N];
        int[][] map = new int[N+1][N+1];

        // 아이템 초기화
        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++)
            items[n] = Integer.parseInt(st.nextToken());

        // 맵 초기화
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i == j) continue;
                map[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if (w > M) continue;
            map[s][e] = w;
            map[e][s] = w;
        }

        for(int m = 1; m <= N; m++) {
            for(int s = 1; s <= N; s++) {
                for(int e =1; e <= N; e++) {
                    if (map[s][m] == Integer.MAX_VALUE || map[m][e] == Integer.MAX_VALUE) continue;
                    if (map[s][m] + map[m][e] > M) continue;

                    map[s][e] = Math.min(map[s][e], map[s][m] + map[m][e]);
                }
            }
        }

        int max = 0;
        for(int i = 1; i <= N; i++) {
            int buf = 0;
            Set<Integer> set = new HashSet<>();

            for(int j = 1; j <= N; j++) {
                if (map[i][j] != Integer.MAX_VALUE) set.add(j);
            }
            for(int s : set) buf += items[s-1];
            max = Math.max(max, buf);
        }

        System.out.println(max);
    }
}
