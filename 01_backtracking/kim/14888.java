import java.io.*;
import java.util.*;


public class Main {
    static int N;

    static int[] nums;
    static int[] calc;

    static int max_result = Integer.MIN_VALUE;
    static int min_result = Integer.MAX_VALUE;

    //지금까지 계산 된 값 = now / 지금까지 사용된 숫자 개수 = cnt
    static void backtracking(int now, int cnt) {
        if (cnt == N -  1) {
            max_result = Math.max(max_result, now);
            min_result = Math.min(min_result, now);
            return;
        }
        for(int i = 0; i < 4; i++) {
            if(calc[i] > 0) {
                calc[i]--;
                if (i == 0) now += nums[cnt+1];
                else if(i==1) now -= nums[cnt+1];
                else if (i ==2) now *= nums[cnt+1];
                else {
                    if (now < 0) now = - (Math.abs(now) / nums[cnt+1]);
                    else now /= nums[cnt+1];
                }
                cnt++;

                backtracking(now, cnt);

                cnt--;
                if (i == 0) now -= nums[cnt+1];
                else if(i==1) now += nums[cnt+1];
                else if (i ==2) {
                    if (now < 0) now = - (Math.abs(now) / nums[cnt+1]);
                    else now /= nums[cnt+1];
                }
                else now *= nums[cnt+1];
                calc[i]++;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        calc = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++) {
            nums[n] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            calc[i] = Integer.parseInt(st.nextToken());
        }

        backtracking(nums[0], 0);
        System.out.println(max_result);
        System.out.println(min_result);
    }
}
