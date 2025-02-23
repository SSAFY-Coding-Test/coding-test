import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N, M;
    private static String S;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        S = br.readLine();

        int result = 0;  // 패턴 찾은 개수
        int start = 0;   // 문자열 탐색 시작 인덱스
        int O_count = 0; // 'O'의 개수 (문자열 Pn에는 O가 N개)
        while (start < M - 2) {
            if (S.charAt(start) == 'I' && S.charAt(start + 1) == 'O' && S.charAt(start + 2) == 'I') { // IOI 패턴 찾기
                start += 2; // IOI 패턴이라면 start + 2부터 다시 IOI 패턴 찾기
                O_count++;

                if (O_count == N) {
                    result++;
                    O_count--;
                }
            } else { // IOI 패턴이 아니라면 start + 1부터 IOI 패턴 찾기
                start += 1;
                O_count = 0;
            }
        }
        System.out.println(result);
    }
}