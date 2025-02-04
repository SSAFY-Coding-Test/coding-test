import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static int result = 0;
    static int[][] B3lock = {{-1,0},{-1,1},{-1,2},{0,3},{1,2},{1,1},{1,0},{0,-1}};
    static int[][] B2lock = {{-1,-1},{-1,0},{-1,1},{-1,2},
                                {1,-1},{1,0},{1,1},{1,2}};


    static void playGame(int[] xy) {
        int max;
        int rowBlock;
        int colBlock;
        int[] round = {0, 1, 2, 4, 5, 6};

        // row - 2
        max = 0;
        if (xy[1] + 1 < M) {
            rowBlock = map[xy[0]][xy[1]] + map[xy[0]][xy[1] + 1];
            for (int r : round) {
                int buf_i1 = xy[0] + B2lock[r][0];
                int buf_j1 = xy[1] + B2lock[r][1];
                int buf_i2 = xy[0] + B2lock[r + 1][0];
                int buf_j2 = xy[1] + B2lock[r + 1][1];
                if (buf_i1 < 0 || buf_i1 >= N || buf_j1 < 0 || buf_j1 >= M) continue;
                if (buf_i2 < 0 || buf_i2 >= N || buf_j2 < 0 || buf_j2 >= M) continue;

                int buf_max = map[buf_i1][buf_j1] + map[buf_i2][buf_j2];
                max = Math.max(max, buf_max);
            }
            result = Math.max(result, rowBlock + max);
        }

        // col - 2
        max = 0;
        if (xy[0] + 1 < N) {
            colBlock = map[xy[0]][xy[1]] + map[xy[0] + 1][xy[1]];
            for (int r : round) {
                int buf_i1 = xy[0] + B2lock[r][1];
                int buf_j1 = xy[1] + B2lock[r][0];
                int buf_i2 = xy[0] + B2lock[r + 1][1];
                int buf_j2 = xy[1] + B2lock[r + 1][0];
                if (buf_i1 < 0 || buf_i1 >= N || buf_j1 < 0 || buf_j1 >= M) continue;
                if (buf_i2 < 0 || buf_i2 >= N || buf_j2 < 0 || buf_j2 >= M) continue;
                
                int buf_max = map[buf_i1][buf_j1] + map[buf_i2][buf_j2];
                max = Math.max(max, buf_max);
            }
            result = Math.max(result, colBlock + max);
        }

        // row - 3
        max = 0;
        if (xy[1] + 2 < M) {
            rowBlock = map[xy[0]][xy[1]] + map[xy[0]][xy[1] + 1] + map[xy[0]][xy[1] + 2];
            for (int[] m : B3lock) {
                int buf_i = xy[0] + m[0];
                int buf_j = xy[1] + m[1];
                if (buf_i < 0 || buf_i >= N || buf_j < 0 || buf_j >= M) continue;
                max = Math.max(max, map[buf_i][buf_j]);
            }
            result = Math.max(result, rowBlock + max);
        }

        // col - 3
        max = 0;
        if (xy[0] + 2 < N) {
            colBlock = map[xy[0]][xy[1]] + map[xy[0] + 1][xy[1]] + map[xy[0] + 2][xy[1]];
            for (int[] m : B3lock) {
                int buf_i = xy[0] + m[1];
                int buf_j = xy[1] + m[0];
                if (buf_i < 0 || buf_i >= N || buf_j < 0 || buf_j >= M) continue;
                max = Math.max(max, map[buf_i][buf_j]);
            }
            result = Math.max(result, colBlock + max);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j =0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for (int j =0; j < M; j++) {
                playGame(new int[]{i, j});
            }
        }
        System.out.println(result);
    }
}
