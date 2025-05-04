import java.io.*;
import java.util.*;

public class 미세먼지_안녕_17144 {
    // -1 : 공기 청정기가 설치된 곳
    // 나머지 값 : 미세먼지의 양

    // 1초 동안 아래 적힌 일이 순서대로 일어난다
    // 1. 미세먼지가 확산됨
    //    확산(네 방향)은 미세먼지가 있는 모든 칸에서 동시에 일어남
    //    확산되는 양은 Ar,c/5, (r, c)에 남은 미세먼지의 양은 Ar,c - ⌊Ar,c/5⌋×(확산된 방향의 개수)
    // 2. 공기청정기가 작동함
    //    위쪽 공기청정기 -> 반시계방향으로 순환, 아래쪽 공기청정기 -> 시계방향으로 순환
    //    바람의 방향대로 미세먼지가 한 칸씩 이동

    static int R, C, T, airPosition = 0;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // T초가 지난 후 구사과 방에 남아있는 미세먼지의 양을 출력하기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) airPosition = i;
            }
        }
        solve();
        System.out.println(countDust());
    }

    private static void solve() {
        while (T-- > 0) {
            spread();
            cleanAir();
        }
    }

    private static void spread() {
        int[][] temp = new int[R][C];

        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                if (map[x][y] > 0) {
                    int amount = map[x][y] / 5;
                    int count = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if (!isValid(nx, ny) || map[nx][ny] == -1) continue;
                        temp[nx][ny] += amount;
                        count++;
                    }
                    temp[x][y] += map[x][y] - amount * count;
                } else if (map[x][y] == -1) {
                    temp[x][y] = -1;
                }
            }
        }
        map = temp;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    private static void cleanAir() {
        int top = airPosition - 1;
        int bottom = airPosition;

        for (int i = top - 1; i >= 0; i--) {
            map[i + 1][0] = map[i][0];
        }
        map[airPosition - 1][0] = -1;
        for (int i = 1; i < C; i++) {
            map[0][i - 1] = map[0][i];
        }
        for (int i = 1; i <= top; i++) {
            map[i - 1][C - 1] = map[i][C - 1];
        }
        for (int i = C - 2; i > 0; i--) {
            map[airPosition - 1][i + 1] = map[airPosition - 1][i];
        }
        map[top][1] = 0;

        for (int i = bottom + 1; i < R; i++) {
            map[i - 1][0] = map[i][0];
        }
        map[airPosition - 1][0] = -1;
        for (int i = 1; i < C; i++) {
            map[R - 1][i - 1] = map[R - 1][i];
        }
        for (int i = R - 2; i >= airPosition; i--) {
            map[i + 1][C - 1] = map[i][C - 1];
        }
        for (int i = C - 2; i >= 0; i--) {
            map[airPosition][i + 1] = map[airPosition][i];
        }
        map[bottom][1] = 0;

        // 공기청정기 위치 복원
        map[top][0] = -1;
        map[bottom][0] = -1;
    }

    private static int countDust() {
        int totalDust = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) totalDust += map[i][j];
            }
        }
        return totalDust;
    }
}