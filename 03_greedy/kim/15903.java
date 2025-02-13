import java.io.*;
import java.util.*;

// 자료형 범위 주의!!

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> card = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++)
            card.add(Long.parseLong(st.nextToken()));


        for(int m = 0; m < M; m++) {
            long now = card.remove() + card.remove();
            card.add(now);
            card.add(now);
        }

        long sum = 0;
        while(!card.isEmpty()) sum += card.remove();

        System.out.println(sum);
    }
}
