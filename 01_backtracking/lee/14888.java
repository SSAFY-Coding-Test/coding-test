import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 14888 {
    private static int N;
    private static List<Integer> result = new ArrayList<>();;
    private static int[] sequence;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 받기
        N = Integer.parseInt(br.readLine());
        sequence = new int[N];
        int[] operation = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operation[i] = Integer.parseInt(st.nextToken());
        }

        // 계산
        backtracking(sequence[0], 1, operation);
        System.out.println(Collections.max(result));
        System.out.println(Collections.min(result));
    }

    private static void backtracking(int currentResult, int count, int[] operation) {
        if (count == N) {
            result.add(currentResult);
            return;
        }

        for (int i = 3; i >= 0; i--) {
            if (operation[i] > 0) {
                operation[i] -= 1;
                int nextResult = currentResult;

                switch (i) {
                    case 0:
                        nextResult += sequence[count];
                        break;
                    case 1:
                        nextResult -= sequence[count];
                        break;
                    case 2:
                        nextResult *= sequence[count];
                        break;
                    case 3:
                        nextResult /= sequence[count];
                        break;
                }
                backtracking(nextResult, count + 1, operation);
                operation[i] += 1;
            }
        }
    }
}