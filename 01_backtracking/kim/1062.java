import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int K;

    static String[] strs;
    static boolean[] learn = new boolean[26];  // 알파벳 학습 여부

    static int result_max = 0;

    static void backtracking(int idx, int count) {
        // 중단 조건 : 모든 k == now_k -> 읽을 수 있는 단어의 최대 개수를 출력
        if (count == K) {
            int readable = 0;
            // 리스트를 순회하며 읽을 수 있는 리스트의 개수를 센다.
            for (String str : strs) {
                boolean canRead = true;
                for (int i = 0; i < str.length(); i++) {
                    if (!learn[str.charAt(i) - 'a']) {
                        canRead = false;
                        break;
                    }
                }
                if (canRead) readable++;
            }
            result_max = Math.max(result_max, readable);
            return;
        }

        // 모든 알파벳에 대하여 순회(배움)
        for (int i = idx; i < 26; i++) {
            if (!learn[i]) {
                learn[i] = true;
                backtracking(i + 1, count + 1);
                learn[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K < 5) {
            System.out.println(0);
            return;
        }

        // a, n, t, i, c는 필수 글
        learn['a' - 'a'] = true;
        learn['n' - 'a'] = true;
        learn['t' - 'a'] = true;
        learn['i' - 'a'] = true;
        learn['c' - 'a'] = true;

        strs = new String[N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            // anta와 tica를 제외한 부분만 저장
            strs[i] = str.substring(4, str.length() - 4);
        }

        // 이미 5개를 배웠으므로 K-5개만 더 배우면 됨
        K -= 5;
        backtracking(0, 0);
        System.out.println(result_max);
    }
}