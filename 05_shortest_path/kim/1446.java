import java.io.*;
import java.util.*;


public class Main {
    static class Node{
        int s;
        int e;
        int w;
        Node(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[] dp = new int[10_001];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        Set<Integer> set = new HashSet<>();
        List<Node> list = new ArrayList<>();

        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (e > D) continue;

            list.add(new Node(s, e, w));

            set.add(s);
            set.add(e);
        }

        for(int i = 0; i < 10_000; i++) {
            dp[i + 1] = Math.min(dp[i+1], dp[i] + 1);

            for(Node now : list) {
                if(now.s == i)
                    dp[now.e] = Math.min(dp[i] + now.w, dp[now.e]);
            }
        }

        System.out.println(dp[D]);
    }
}
