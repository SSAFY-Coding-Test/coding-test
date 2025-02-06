import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int N = 0, S = 1, CLOCK = 1, REVERSE = -1;
    private static int K;
    private static int[] rotateInfo = new int[4];
    private static int[][] wheel = new int[4][8];
    private static int[][] rotation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                wheel[i][j] = line.charAt(j) - '0';
            }
        }

        K = Integer.parseInt(br.readLine());
        rotation = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            rotation[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rotation[i][1] = Integer.parseInt(st.nextToken());
        }

        simulateWheelRotation();
        System.out.println(calculateScore());
    }

    private static void simulateWheelRotation() {
        for (int i = 0; i < K; i++) {
            int num = rotation[i][0];
            int direction = rotation[i][1];

            initRotateInfo();
            rotateInfo[num] = direction;

            fillLeftRotateInfo(num);
            fillRightRotateInfo(num);
            rotateWheels();
        }
    }

    private static void initRotateInfo() {
        for (int i = 0; i < 4; i++) {
            rotateInfo[i] = 0;
        }
    }

    private static void fillLeftRotateInfo(int num) {
        for (int left = num - 1; left >= 0; left--) {
            if (wheel[left][2] != wheel[left + 1][6]) {
                rotateInfo[left] = rotateInfo[left + 1] * -1;
            } else {
                rotateInfo[left] = 0;
                break;
            }
        }
    }

    private static void fillRightRotateInfo(int num) {
        for (int right = num + 1; right <= 3; right++) {
            if (wheel[right - 1][2] != wheel[right][6]) {
                rotateInfo[right] = rotateInfo[right - 1] * -1;
            } else {
                rotateInfo[right] = 0;
                break;
            }
        }
    }

    private static void rotateWheels() {
        for (int num = 0; num < 4; num++) {
            if (rotateInfo[num] == CLOCK) {
                int temp = wheel[num][7];
                for (int i = 7; i > 0; i--) {
                    wheel[num][i] = wheel[num][i - 1];
                }
                wheel[num][0] = temp;
            } else if (rotateInfo[num] == REVERSE) {
                int temp = wheel[num][0];
                for (int i = 0; i < 7; i++) {
                    wheel[num][i] = wheel[num][i + 1];
                }
                wheel[num][7] = temp;
            }
        }
    }

    private static int calculateScore() {
        int score = 0;

        if (wheel[0][0] == S) score += 1;
        if (wheel[1][0] == S) score += 2;
        if (wheel[2][0] == S) score += 4;
        if (wheel[3][0] == S) score += 8;
        return score;
    }
}