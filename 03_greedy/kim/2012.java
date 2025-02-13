import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] person = new long[N];

        for(int n = 0; n < N; n++) {
            person[n] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(person);

        long answer = 0;
        for(int i = 1; i <= N; i++) {
            answer += Math.abs(i - person[i-1]);
        }

        System.out.println(answer);
    }
}
