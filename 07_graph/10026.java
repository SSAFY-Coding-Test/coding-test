import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int count1 = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    solve1(i, j);
                    count1++;
                }
            }
        }

        int count2 = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    solve2(i, j);
                    count2++;
                }
            }
        }
        System.out.println(count1 + " " + count2);
    }

    private static void solve1(int startX, int startY) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        visited[startX][startY] = true;
        queue.add(new int[] {startX, startY, map[startX][startY]});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], color = current[2];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!isValid(nx, ny) || visited[nx][ny]) continue;
                if (map[nx][ny] == color) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny, color});
                }
            }
        }
    }

    private static void solve2(int startX, int startY) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        visited[startX][startY] = true;
        queue.add(new int[] {startX, startY, map[startX][startY] == 'R'? 'G' : map[startX][startY]});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], color = current[2];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!isValid(nx, ny) || visited[nx][ny]) continue;
                if (map[nx][ny] == color) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny, color});
                } else if (map[nx][ny] == 'R' && color == 'G') {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny, 'G'});
                }
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}