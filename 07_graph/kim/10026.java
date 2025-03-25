import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static char[][] map;
    static boolean[][] visited;
    static int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int result = 0;
    static int resultColor = 0;

    static void dfs(int n, int m) {
        if(!visited[n][m]) return;

        visited[n][m] = false;

        for(int[] mv : move) {
            int nextn = n + mv[0];
            int nextm = m + mv[1];

            if (nextn < 0 || nextn >= N || nextm < 0 || nextm >= N) continue;

            if (map[n][m] != 'B' && map[nextn][nextm] != 'B') dfs(nextn, nextm);
            else if (map[n][m] == 'B' && map[nextn][nextm] == 'B') dfs(nextn, nextm);
        }
    }

    static void dfsColor(int n, int m) {
        if(visited[n][m]) return;

        visited[n][m] = true;

        for(int[] mv : move) {
            int nextn = n + mv[0];
            int nextm = m + mv[1];

            if (nextn < 0 || nextn >= N || nextm < 0 || nextm >= N) continue;
            if (map[n][m] != map[nextn][nextm]) continue;

            dfsColor(nextn, nextm);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j]) {
                    resultColor ++;
                    dfsColor(i, j);
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visited[i][j]) {
                    result ++;
                    dfs(i, j);
                }
            }
        }

        System.out.println(resultColor + " " + result);
    }
}
