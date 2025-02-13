import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] convence = new int[N][2];
        int[] start = {0, 0};
        int result = 1;

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            convence[i][0] = Integer.parseInt(st.nextToken());
            convence[i][1] = Integer.parseInt(st.nextToken());

            if (start[0] == 0 || (start[0] != 0 && convence[i][0]+convence[i][1] < start[0]+start[1])) {
                start[0] = convence[i][0];
                start[1] = convence[i][1];
            }
        }

        Arrays.sort(convence, (s, e) -> {
            if(s[1] == e[1]) return s[0] - e[0];
            return s[1] - e[1];
        });


        int end = convence[0][1];
        for(int i = 1; i < N; i++) {
            if(end <= convence[i][0]) {
                end = convence[i][1];
                result++;
            }
        }

        System.out.println(result);
    }
}
