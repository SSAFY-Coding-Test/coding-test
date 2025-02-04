import java.io.*;
import java.util.*;

public class Main {
    static int[][] map = new int[9][9];

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void backtracking(int i, int j) {
        if (i == 9) { // 스도쿠를 다 채웠다면 출력하고 종료
            printResult();
            System.exit(0);
        }

        int next_i = (j == 8) ? i + 1 : i;
        int next_j = (j == 8) ? 0 : j + 1;

        if (map[i][j] == 0) { // 빈 칸인 경우 숫자를 넣어보기
            for (int n = 1; n <= 9; n++) {
                if (check_n_isAvail(i, j, n)) { // 숫자가 가능하면 채우기
                    map[i][j] = n;
                    backtracking(next_i, next_j);
                    map[i][j] = 0; // 백트래킹 (되돌리기)
                }
            }
            return; // 가능한 숫자가 없는 경우 리턴 (백트래킹)
        }

        // 빈 칸이 아니라면 다음 칸으로 이동
        backtracking(next_i, next_j);
    }

    private static boolean check_n_isAvail(int n_i, int n_j, int n) {
        // 가로, 세로 체크
        for (int i = 0; i < 9; i++) {
            if (map[n_i][i] == n || map[i][n_j] == n) return false;
        }

        // 3x3 박스 체크
        int start_i = (n_i / 3) * 3;
        int start_j = (n_j / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[start_i + i][start_j + j] == n) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtracking(0, 0);
    }
}
