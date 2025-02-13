import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] money = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int n = 0; n < N; n++) {
                money[n] = Integer.parseInt(st.nextToken());
            }

            int max = money[N-1];
            long result = 0;
            for(int n = N-1; n >= 0; n--) {
                if(max >= money[n]) {
                    result += (max - money[n]);
                }
                else {
                    max = money[n];
                }
            }

            System.out.println(result);
        }
    }
}
