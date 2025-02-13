import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[] start;
    static int[] end;

    static void change(int[] state, int i) {
        state[i] ^= 1;
        if (i > 0) state[i - 1] ^= 1;
        if (i < N - 1) state[i + 1] ^= 1;
    }

    static int click(boolean press) {
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
        }
        return (state[N - 1] == end[N - 1]) ? count : Integer.MAX_VALUE;
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

        // 1번 스위치 X / O
        int result = Math.min(click(false), click(true));
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }
}
