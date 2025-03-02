import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
	public int position;		// 현재 지점
	public int time;			// 소비 시간

	public Node (int position, int time) {
		this.position = position;
		this.time = time;
	}

	public int compareTo(Node n) {
		return this.time - n.time;
	}
}

public class Main {
	static final int MAX = 100_000;
	static final int INF = 100_000;
    private static int N, K;
    static int[] time = new int[MAX + 1]; // 최단 시간 배열
    private static int minTime = Integer.MAX_VALUE;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
		
        if (N >= K)
			minTime = N - K;
		else {
            Arrays.fill(time, INF);
			dijkstra();
			minTime = time[K];
		}
        System.out.println(minTime);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        pq.add(new Node(N, 0));
        time[N] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int cposition = current.position;
            int ctime = current.time;

			// 이미 더 빠르게 도달한 경로가 있을 경우 그 경로를 건너뛰기
			if (time[cposition] < ctime)
				continue;
        
            int newPosition1 = cposition * 2;
            if (isValid(newPosition1) && time[newPosition1] > ctime) {
                time[newPosition1] = ctime;
                pq.add(new Node(newPosition1, time[newPosition1]));
            }

            int newPosition2 = cposition + 1;
            if (isValid(newPosition2) && time[newPosition2] > ctime + 1) {
                time[newPosition2] = ctime + 1;
                pq.add(new Node(newPosition2, time[newPosition2]));
            }

            int newPosition3 = cposition - 1;
            if (isValid(newPosition3) && time[newPosition3] > ctime + 1) {
                time[newPosition3] = ctime + 1;
                pq.add(new Node(newPosition3, time[newPosition3]));
            }
        }
    }
    private static boolean isValid(int position) {
		return 0 <= position && position <= MAX;
	}
}
