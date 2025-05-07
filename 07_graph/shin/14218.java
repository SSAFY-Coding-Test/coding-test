import java.io.*;
import java.util.*;

public class 14218 {
	static int n,m,q;
	static ArrayList<Integer>[] graph;	// 각 배열 원소는 ArrayList<Integer>
	static int[] ans;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		init();

		for(int i=0 ; i<m ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		st = new StringTokenizer(br.readLine());
		q = Integer.parseInt(st.nextToken());
		for(int i=0 ; i<q ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// 두 도시 잇고
			graph[a].add(b);
			graph[b].add(a);

			// 수도에서부터 최소거리 갱신
			calcDist(1);

			// 출력  누적
			printAns();
		}

		System.out.println(sb);

	}

	static void init() {
		ans = new int[n+1];
		graph = new ArrayList[n+1];
		for(int i=0 ; i<=n ; i++) {
			graph[i] = new ArrayList<Integer>();
		}
	}

	// 이미 방문한 노드라도 더 짧은 경로로 도달했다면 갱신해야함!!!
	static void calcDist(int x) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x,0});
        Arrays.fill(ans, Integer.MAX_VALUE);

		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curX = cur[0];
			int cnt = cur[1];

			for(int newX : graph[curX]) {
				if(ans[newX] > cnt + 1) {
					ans[newX] = cnt + 1;
					q.offer(new int[]{newX, cnt + 1});
				}
			}

		}

	}

	static void printAns() {
		ans[1] = 0;
		for(int i=1 ; i<=n ; i++) {
			sb.append(ans[i] == Integer.MAX_VALUE ? -1 : ans[i]).append(" ");
		}
		sb.append("\n");
	}
}
