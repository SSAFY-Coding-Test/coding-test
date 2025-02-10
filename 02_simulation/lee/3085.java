import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static int max_candy = 0;
    private static char[][] board;
    private static int[][] directions = {
        {-1, 0}, {1, 0},
        {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                eatCandy(i, j);
            }
        }
        System.out.println(max_candy);
    }

    private static void eatCandy(int currentX, int currentY) {
        for (int i = 0; i < 4; i++) {
            int nx = currentX + directions[i][0];
            int ny = currentY + directions[i][1];

            if (!isValid(nx, ny)) continue;
            if (isDifferent(currentX, currentY, nx, ny)) {
                swapCandy(currentX, currentY, nx, ny);
                findMaxCandyLength();
                swapCandy(nx, ny, currentX, currentY);
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static boolean isDifferent(int x1, int y1, int x2, int y2) {
        return board[x1][y1] != board[x2][y2];
    }

    private static void swapCandy(int x1, int y1, int x2, int y2) {
        char temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }

    private static void findMaxCandyLength() {
        // 최대 행의 길이
        for (int currentX = 0; currentX < N; currentX++) {
            int count = 1;
            for (int currentY = 0; currentY < N - 1; currentY++) {
                if (!isDifferent(currentX, currentY, currentX, currentY + 1)) {
                    count++;
                } else {
                    count = 1;
                }
                max_candy = Math.max(max_candy, count);
            }
        }

        // 최대 열의 길이
        for (int currentY = 0; currentY < N; currentY++) {
            int count = 1;
            for (int currentX = 0; currentX < N - 1; currentX++) {
                if (!isDifferent(currentX, currentY, currentX + 1, currentY)) {
                    count++;
                } else {
                    count = 1;
                }
                max_candy = Math.max(max_candy, count);
            }
        }
    }
}