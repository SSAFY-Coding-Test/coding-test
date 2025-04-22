import java.io.*;
import java.util.*;

public class Main {
    static int l, startX, startY, endX, endY;
    static int[][] directions = {
            {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2},
            {1, -2}, {2, -1}, {2, 1}, {1, 2}};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            l = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());
            solve();
        }
        System.out.println(sb.toString());
    }

    private static void solve() {
        boolean[][] visited = new boolean[l][l];
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        visited[startX][startY] = true;
        queue.add(new int[] {startX, startY, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int count = current[2];

            if (x == endX && y == endY) {
                sb.append(count).append("\n");
                return;
            }

            for (int[] d : directions) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (isValid(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny, count + 1});
                }
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < l && y >= 0 && y < l;
    }
}