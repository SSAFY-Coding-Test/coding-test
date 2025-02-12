import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N;
    private static int[] initialState;
    private static int[] goalState;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        N = Integer.parseInt(br.readLine());
        initialState = new int[N];
        goalState = new int[N];

        String line = br.readLine();
        for (int i = 0; i < N; i++) {
            initialState[i] = line.charAt(i) - '0';
        }
        line = br.readLine();
        for (int i = 0; i < N; i++) {
            goalState[i] = line.charAt(i) - '0';
        }

        // 스위치를 최소 몇 번 누르는지 알아내기
        // 경우 1. 첫 번쩨 전구를 누르지 않았을 경우
        int[] currentState = Arrays.copyOf(initialState, N);
        int count1 = switchLight(currentState, 0);

        // 경우 2. 첫 번쩨 전구를 눌렀을 경우
        currentState = Arrays.copyOf(initialState, N);
        currentState[0] = currentState[0] ^ 1;
        currentState[1] = currentState[1] ^ 1;
        int count2 = switchLight(currentState, 1);

        // 최솟값 출력 (불가능한 경우 -1 출력하기)
        int minCount = Math.min(count1, count2);
        System.out.println(minCount == Integer.MAX_VALUE ? -1 : minCount);
    }

    private static int switchLight(int[] currentState, int count) {
        for (int i = 1; i < N; i++) {
            // 세 개의 전구 상태 바꾸기 (마지막 전구는 두 개만 바꿈)
            if (currentState[i - 1] != goalState[i - 1]) {
                currentState[i - 1] = currentState[i - 1] ^ 1;
                currentState[i] = currentState[i] ^ 1;
                if (i < N - 1) {
                    currentState[i + 1] = currentState[i + 1] ^ 1;
                }
                count++;
            }
        }
        return isGoalReachable(currentState) ? count : Integer.MAX_VALUE;
    }

    private static boolean isGoalReachable(int[] currentState) {
        for (int i = 0; i < N; i++) {
            if (currentState[i] != goalState[i]) {
                return false;
            }
        }
        return true;
    }
}