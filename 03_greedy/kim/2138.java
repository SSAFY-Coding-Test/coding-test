import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[] start;
    static int[] end;
    static int result = Integer.MAX_VALUE;

    // 전구 on/off 변경
    static void change(int[] state, int i) {
        state[i] ^= 1;
        if (i > 0) state[i - 1] ^= 1;
        if (i < N - 1) state[i + 1] ^= 1;
    }

    static void click(boolean press) {
        int[] state = start.clone();
        int count = 0;

        // 눌릴 경우
        if(press) {
            change(state, 0);
            count++;
        }

        for (int i = 1; i < N; i++) {
            // 이전 전구가 다르면 눌러야 함
            if (state[i - 1] != end[i - 1]) {
                change(state, i);
                count++;
            }
            // for문 중단 조건 (count가 더 큰 경우)
            if(result < count) return;
        }
        // 만들 수 없는 경우
        if (state[N - 1] != end[N - 1]) return;

        // 만들 수 있는 경우 (result에 저장)
        result = count;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        start = new int[N];
        end = new int[N];

        String sLine = br.readLine();
        String eLine = br.readLine();

        for(int i = 0; i < N; i++) {
            start[i] = sLine.charAt(i) - '0';
            end[i] = eLine.charAt(i) - '0';
        }

        // 첫번째 전구 스위칭 O
        click(true);
        // 첫번째 전구 스위칭 X
        click(false);

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }
}
