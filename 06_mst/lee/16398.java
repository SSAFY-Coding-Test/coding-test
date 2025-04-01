import java.io.*;
import java.util.*;

public class Main {
	
	static class Edge {
		int to;
		int weight;
		
		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	private static int N;
	private static int[][] planet;
	private static boolean[] inMST;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		N = Integer.parseInt(br.readLine());
		planet = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				planet[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(prim(1));
	}

	
	private static long prim(int start) {
		long totalWeight = 0;
		inMST = new boolean[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight)); 
		
        pq.add(new Edge(1, 0));
		
		while (!pq.isEmpty()) {
			Edge current = pq.poll();
            int node = current.to;
            int weight = current.weight;
            
            if (inMST[node]) continue;
            
            inMST[node] = true;
            totalWeight += weight;
			
			for (int i = 1; i <= N; i++) {
				if (!inMST[i]) {
					pq.add(new Edge(i, planet[node][i]));
				}
			}
		}
        return totalWeight;
	}
}
