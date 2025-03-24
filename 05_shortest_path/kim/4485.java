import java.io.*;
import java.util.*;


public class Main {

    static int[][] move = {{1,0}, {0,1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int test = 1;

        while(true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            int[][] map = new int[N][N];
            int[][] result = new int[N][N];
            boolean[][] visited = new boolean[N][N];

            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j <N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    map[i][j] = num;
                    result[i][j] = num;
                }
            }

            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[] {0, 0});
            visited[0][0] = true;
            result[0][0] = map[0][0];

            while(!queue.isEmpty()) {
                int[] now = queue.poll();

                for(int[] mv : move) {
                    int nextn = now[0] + mv[0];
                    int nextm = now[1] + mv[1];

                    if(nextn < 0 || nextm < 0 || nextn >= N || nextm >= N) continue;
                    if(visited[nextn][nextm] && result[nextn][nextm] <= result[now[0]][now[1]] + map[nextn][nextm]) continue;

                    result[nextn][nextm] = result[now[0]][now[1]] + map[nextn][nextm];
                    visited[nextn][nextm] = true;
                    queue.add(new int[] {nextn, nextm});
                }
            }

            sb.append("Problem ").append(test++).append(": ").append(result[N-1][N-1]).append("\n");
        }

        System.out.println(sb);
    }
}
