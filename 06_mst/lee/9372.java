import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static boolean[] visited;
	private static ArrayList<Integer>[] plane;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			plane = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				plane[i] = new ArrayList<Integer>();
			}
			
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				plane[a].add(b);
				plane[b].add(a);
			}
			
			visited = new boolean[N + 1];
			sb.append(dfs(1)).append("\n"); // 방법 1
			sb.append(bfs(1) - 1).append("\n"); // 방법 2
			sb.append((N - 1)).append("\n"); // 방법 3 (최소 신장 트리의 성질 : 간선의 개수 == 정점의 개수 - 1)
		}
		System.out.println(sb.toString());
	}

	private static int dfs(int start) {
		visited[start] = true;
		
		int count = 0;
		for (int destination : plane[start]) {
			if (!visited[destination]) {
				count += dfs(destination) + 1;
			}
		}
		return count;
	}

    private static int bfs(int start) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start] = true;

		int count = 0;
		while (!queue.isEmpty()) {
			count++;
			int current = queue.poll();
			
			for (int destination : plane[current]) {
				if (!visited[destination]) {
					visited[destination] = true;
					queue.add(destination);
				}
			}
		}
		return count;
	}
}
