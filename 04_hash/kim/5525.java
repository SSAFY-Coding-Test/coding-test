import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String S = br.readLine();

        int result = 0;

        int cnt = 0;
        char buf = S.charAt(0);
        for(int i = 0; i < M; i++) {
            char now = S.charAt(i);

            // IOI 패턴이 끝날 때
            if((buf == 'I' && now == 'I') || buf == 'O' && now == 'O') {
                if (cnt >= N*2 + 1)
                    result += Math.abs(N - ((cnt+1) / 2));
                cnt = 0;
            }

            // 새로운 IOI 시작
            if (now == 'I' && cnt == 0) cnt++;
                // IO or OI
            else if((buf == 'I' && now == 'O') || (buf == 'O' && now == 'I')) cnt++;
            buf = now;
        }

        if (cnt >= N*2 + 1)
            result += Math.abs(N - ((cnt+1) / 2));

        System.out.println(result);
    }
}
