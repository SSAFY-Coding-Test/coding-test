import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[][] map;
    static int[] start;
    static int[] end;

    static int[][] move = {{1,-2}, {-1,-2}, {2,-1}, {-2,-1}, {2,1}, {-2,1}, {1,2}, {-1,2}};

    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            if (now[0] == end[0] && now[1] == end[1]) break;

            for(int[] mv : move) {
                int nextn = now[0] + mv[0];
                int nextm = now[1] + mv[1];

                if (nextn < 0 || nextm < 0 || nextn >= N || nextm >= N) continue;
                if (map[nextn][nextm] != 0) continue;
                map[nextn][nextm] = map[now[0]][now[1]] + 1;
                queue.add(new int[] {nextn, nextm});
            }
        }
        System.out.println(map[end[0]][end[1]]);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t=0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            start = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            st = new StringTokenizer(br.readLine());
            end = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

            bfs();
        }
    }
}
