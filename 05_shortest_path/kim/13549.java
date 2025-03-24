import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int time;
        int now;

        Node(int time, int now) {
            this.time = time;
            this.now = now;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N >= K) {
            System.out.println(N - K);
            return;
        }

        int[] go = new int[140_001];
        for (int i = 0; i < go.length; i++) {
            go[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>((s, e) -> Integer.compare(s.time, e.time));
        queue.add(new Node(0, N));

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int nnode = now.now;
            if (nnode == K) {
                System.out.println(now.time);
                return;
            }

            int[] move = { nnode + 1, nnode - 1 };
            for (int next : move) {
                if (next < 0 || next > 140_000) continue;
                if (go[next] > now.time + 1 && now.time + 1 <= (K-N)) {
                    queue.add(new Node(now.time + 1, next));
                    go[next] = now.time + 1;
                }
            }

            if (nnode * 2 <= 0 || nnode * 2 > 140_000) continue;
            else {
                if (go[nnode * 2] > now.time && now.time <= (K-N)) {
                    queue.add(new Node(now.time, nnode * 2));
                    go[nnode * 2] = now.time;
                }
            }
        }
    }
}
