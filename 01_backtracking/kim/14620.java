import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int min_cost = Integer.MAX_VALUE;
    static int[][] flowers;
    static boolean[][] is_valid;

    static void backtracking(int flower, int cost) {
        // 중단조건
        if (flower == 3) {
            min_cost = Math.min(cost, min_cost);
            return;
        }

        // 다음 반복으로 넘어가는 조건
        for (int ii = 1; ii < N-1; ii++) {
            for(int jj = 1; jj < N-1; jj++) {
                // 범위 상 유효한 범위이고, 꽃이 펴지는 곳의 is_valid가 모두 false이면
                if((!is_valid[ii-1][jj]) && (!is_valid[ii+1][jj]) && (!is_valid[ii][jj-1]) && (!is_valid[ii][jj+1]) && (!is_valid[ii][jj])) {
                    is_valid[ii][jj] = is_valid[ii-1][jj] = is_valid[ii+1][jj] = is_valid[ii][jj-1] = is_valid[ii][jj+1] = true;
                    flower += 1;
                    cost += (flowers[ii][jj] + flowers[ii-1][jj] + flowers[ii+1][jj] + flowers[ii][jj-1] + flowers[ii][jj+1]);

                    if (cost < min_cost)
                        backtracking(flower, cost);

                    flower -= 1;
                    cost -= (flowers[ii][jj] + flowers[ii-1][jj] + flowers[ii+1][jj] + flowers[ii][jj-1] + flowers[ii][jj+1]);
                    is_valid[ii][jj] = is_valid[ii-1][jj] = is_valid[ii+1][jj] = is_valid[ii][jj-1] = is_valid[ii][jj+1] = false;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        flowers = new int[N][N];
        is_valid = new boolean[N][N];

        for(int i = 0; i <N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                flowers[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtracking(0, 0);

        System.out.println(min_cost);
    }
}
